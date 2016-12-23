package com.proxiad.extranet.core.service.employee;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxiad.extranet.core.model.certification.Certification;
import com.proxiad.extranet.core.model.education.Education;
import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.employee.beans.EmployeeDTO;
import com.proxiad.extranet.core.model.expertise.Expertise;
import com.proxiad.extranet.core.model.interest.Interest;
import com.proxiad.extranet.core.model.language.EmployeeLanguage;
import com.proxiad.extranet.core.model.param.ParamName;
import com.proxiad.extranet.core.model.skill.Skill;
import com.proxiad.extranet.core.repository.certification.CertificationDao;
import com.proxiad.extranet.core.repository.education.EducationDao;
import com.proxiad.extranet.core.repository.employee.EmployeeDao;
import com.proxiad.extranet.core.repository.expertise.ExpertiseDao;
import com.proxiad.extranet.core.repository.interest.InterestDao;
import com.proxiad.extranet.core.repository.language.LanguageDao;
import com.proxiad.extranet.core.repository.skill.SkillDao;
import com.proxiad.extranet.core.service.param.ParameterService;

/**
 * Employee service will manage {@link Employee} entity and its primary data
 * 
 * @author Mihail Merkov
 */
@Transactional(value = Transactional.TxType.SUPPORTS)
@Service
public class EmployeeServiceImpl implements EmployeeService {
	/*--------------------------------------------------- CONSTANTS --------------------------------------------------*/
	private static final String DEFAULT_PROFILE_PIC_RESOURCE_PATH = "static/assets/images/defalt-profile-picture.jpg";

	/*-------------------------------------------------- REPOSITORIES ------------------------------------------------*/
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private SkillDao skillDao;
	
	@Autowired
	private InterestDao interestDao;
	
	@Autowired
	private ExpertiseDao expertiseDao;
	
	@Autowired
	private LanguageDao languageDao;
	
	@Autowired
	private EducationDao educationDao;
	
	@Autowired
	private CertificationDao certificationDao;
	
	/*--------------------------------------------------- FACTORIES --------------------------------------------------*/
	@Autowired
	private EmployeeFactory employeeFactory;

	/*---------------------------------------------------- SERVICES --------------------------------------------------*/
	@Autowired
	private ParameterService parameterService;

	/*------------------------------------------------------ API -----------------------------------------------------*/
	/**
	 * @see com.proxiad.extranet.core.service.employee.EmployeeService#listActiveEmployeesWithMainDataOnly()
	 */
	@Override
	public List<EmployeeDTO> listActiveEmployeesWithMainDataOnly() {
		return employeeDao.listActiveEmployeesWithMainDataOnly();
	}

	/**
	 * @see com.proxiad.extranet.core.service.employee.EmployeeService#getProfilePictureByEmployeeId(Long)
	 */
	@Override
	public byte[] getProfilePictureByEmployeeId(final Long employeeId) {
		Employee employee = employeeDao.get(employeeId);
		BufferedInputStream bufferedInputStream = null;
		byte[] profilePictureRaw = null;
		byte[] defaultPictureRaw = null;
		try {
			bufferedInputStream = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream(DEFAULT_PROFILE_PIC_RESOURCE_PATH));
			defaultPictureRaw = IOUtils.toByteArray(bufferedInputStream);
		} catch (IOException e) {
			// never will happen
		} finally {
			IOUtils.closeQuietly(bufferedInputStream);
		}

		if (employee == null || StringUtils.isBlank(employee.getPathToProfilePicture())) {
			return defaultPictureRaw;
		}

		File globalImagesFolder = new File(parameterService.getParamAsString(ParamName.IMAGES_SOURCE_PATH));
		File baseProfilePictureFolder = new File(globalImagesFolder, parameterService.getParamAsString(ParamName.BASE_PROFILE_IMAGES_FOLDER_NAME));
		if (!baseProfilePictureFolder.exists() || !baseProfilePictureFolder.isDirectory()) {
			return defaultPictureRaw;
		}

		File profilePicture = new File(baseProfilePictureFolder, employee.getPathToProfilePicture());
		if (!profilePicture.exists() || !profilePicture.isFile()) {
			return defaultPictureRaw;
		}


		try {
			bufferedInputStream = new BufferedInputStream(new FileInputStream(profilePicture));
			profilePictureRaw = IOUtils.toByteArray(bufferedInputStream);
		} catch (IOException e) {
			return defaultPictureRaw;
		} finally {
			IOUtils.closeQuietly(bufferedInputStream);
		}

		return profilePictureRaw;
	}

	
	/**
	 * @see com.proxiad.extranet.core.service.employee.EmployeeService#getEmployeeById(java.lang.Long)
	 */
	@Override
	public EmployeeDTO getEmployeeById(final Long employeeId) {
		Employee employee = employeeDao.get(employeeId);
		if (employee == null) {
			return null;
		}
		
		return employeeFactory.createBasicEmployeeDataFrom(employee); 
	}

	/**
	 * @see com.proxiad.extranet.core.service.employee.EmployeeService#listSkillsWhichBelongsToEmployee(java.lang.Long)
	 */
	@Override
	public Set<Skill> listSkillsWhichBelongsToEmployee(final Long employeeId) {
		return skillDao.listSkillsWhichBelongsToEmployee(employeeId);
	}

	/**
	 * @see com.proxiad.extranet.core.service.employee.EmployeeService#listInterestsWhichBelongsToEmployee(java.lang.Long)
	 */
	@Override
	public Set<Interest> listInterestsWhichBelongsToEmployee(final Long employeeId) {
		return interestDao.listInterestsWhichBelongsToEmployee(employeeId);
	}

	/**
	 * @see com.proxiad.extranet.core.service.employee.EmployeeService#listExpertisesWhichBelongsToEmployee(java.lang.Long)
	 */
	@Override
	public Set<Expertise> listExpertisesWhichBelongsToEmployee(final Long employeeId) {
		return expertiseDao.listExpertisesWhichBelongsToEmployee(employeeId);
	}

	/**
	 * @see com.proxiad.extranet.core.service.employee.EmployeeService#listLanguagesWhoEmployeeKnows(java.lang.Long)
	 */
	@Override
	public Set<EmployeeLanguage> listLanguagesWhoEmployeeKnows(final Long employeeId) {
		return languageDao.listLanguagesWhoEmployeeKnows(employeeId);
	}

	/**
	 * @see com.proxiad.extranet.core.service.employee.EmployeeService#listEducationsOfEmployee(java.lang.Long)
	 */
	@Override
	public Set<Education> listEducationsOfEmployee(final Long employeeId) {
		return educationDao.listEducationsOfEmployee(employeeId);
	}

	/**
	 * @see com.proxiad.extranet.core.service.employee.EmployeeService#listCertificationsWhichBelongsToEmployee(java.lang.Long)
	 */
	@Override
	public Set<Certification> listCertificationsWhichBelongsToEmployee(final Long employeeId) {
		return certificationDao.listCertificationsWhichBelongsToEmployee(employeeId);
	}
}
