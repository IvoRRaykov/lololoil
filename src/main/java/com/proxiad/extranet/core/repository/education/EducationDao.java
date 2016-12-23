package com.proxiad.extranet.core.repository.education;

import java.util.Set;

import com.proxiad.extranet.core.model.education.Education;
import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Repository access interface for {@link Education} entity
 * 
 * @author Mihail Merkov
 */
public interface EducationDao extends CrudDao<Education, Long> {
	
	/**
	 * List all {@link Education}s of an {@link Employee}
	 * @param employeeId the id of specific {@link Employee}
	 * @return {@link Set} from {@link Education}
	 */
	Set<Education> listEducationsOfEmployee(final Long employeeId);
}
