package com.proxiad.extranet.core.repository.expertise;

import java.util.Set;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.expertise.Expertise;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Repository access interface for {@link Expertise} entity
 * 
 * @author Mihail Merkov
 */
public interface ExpertiseDao extends CrudDao<Expertise, Long> {
	
	/**
	 * List all {@link Expertise}s which belongs to specific {@link Employee}
	 * @param employeeId
	 * @return {@link Set} from {@link Expertise}
	 */
	Set<Expertise> listExpertisesWhichBelongsToEmployee(final Long employeeId);
}
