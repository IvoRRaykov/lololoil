package com.proxiad.extranet.core.service.employee;

import java.util.List;
import java.util.Set;

import com.proxiad.extranet.core.model.certification.Certification;
import com.proxiad.extranet.core.model.education.Education;
import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.employee.beans.EmployeeDTO;
import com.proxiad.extranet.core.model.expertise.Expertise;
import com.proxiad.extranet.core.model.interest.Interest;
import com.proxiad.extranet.core.model.language.EmployeeLanguage;
import com.proxiad.extranet.core.model.language.Language;
import com.proxiad.extranet.core.model.skill.Skill;


/**
 * Employee service will manage {@link Employee} entity and its primary data
 * 
 * @author Mihail Merkov
 */
public interface EmployeeService {
	
	/**
	 * List all active {@link Employee}s only with main data
	 * @return {@link List} from {@link EmployeeDTO}
	 */
	List<EmployeeDTO> listActiveEmployeesWithMainDataOnly();

	/**
	 * Get profile picture of specific {@link Employee} by its <code>id</code>
	 * @param employeeId id of an employee
	 * @return byte presentation of the image
     */
	byte[] getProfilePictureByEmployeeId(final Long employeeId);
	
	/**
	 * Get an {@link Employee} by its <code>id</code>
	 * @param employeeId
	 * @return {@link EmployeeDTO} or null
	 */
	EmployeeDTO getEmployeeById(final Long employeeId);
	
	/**
	 * List all {@link Skill}s which belongs to specific {@link Employee}
	 * @param employeeId
	 * @return {@link Set} from {@link Skill}
	 */
	Set<Skill> listSkillsWhichBelongsToEmployee(final Long employeeId);
	
	/**
	 * List all {@link Interest}s which belongs to specific {@link Employee}
	 * @param employeeId
	 * @return {@link Set} from {@link Interest}
	 */
	Set<Interest> listInterestsWhichBelongsToEmployee(final Long employeeId);
	
	/**
	 * List all {@link Expertise}s which belongs to specific {@link Employee}
	 * @param employeeId
	 * @return {@link Set} from {@link Expertise}
	 */
	Set<Expertise> listExpertisesWhichBelongsToEmployee(final Long employeeId);
	
	/**
	 * List all {@link Language}s, that specific {@link Employee} knows
	 * @param idemployeeId the id of specific {@link Employee}
	 * @return {@link Set} from {@link EmployeeLanguage}
	 */
	Set<EmployeeLanguage> listLanguagesWhoEmployeeKnows(final Long employeeId);
	
	/**
	 * List all {@link Education}s of an {@link Employee}
	 * @param employeeId the id of specific {@link Employee}
	 * @return {@link Set} from {@link Education}
	 */
	Set<Education> listEducationsOfEmployee(final Long employeeId);
	
	/**
	 * List all {@link Certification}s which belongs to specific {@link Employee}
	 * @param employeeId
	 * @return {@link Set} from {@link Certification}
	 */
	Set<Certification> listCertificationsWhichBelongsToEmployee(final Long employeeId);
}
