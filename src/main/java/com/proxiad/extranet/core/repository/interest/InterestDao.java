package com.proxiad.extranet.core.repository.interest;

import java.util.Set;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.interest.Interest;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Repository access interface for {@link Interest} entity
 * 
 * @author Mihail Merkov
 */
public interface InterestDao extends CrudDao<Interest, Long> {
	
	/**
	 * List all {@link Interest}s which belongs to specific {@link Employee}
	 * @param employeeId
	 * @return {@link Set} from {@link Interest}
	 */
	Set<Interest> listInterestsWhichBelongsToEmployee(final Long employeeId);
}
