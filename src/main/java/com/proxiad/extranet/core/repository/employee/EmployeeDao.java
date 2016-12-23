package com.proxiad.extranet.core.repository.employee;

import java.util.List;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.employee.beans.EmployeeDTO;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Repository access interface for {@link Employee} entity
 * 
 * @author Mihail Merkov
 */
public interface EmployeeDao extends CrudDao<Employee, Long> {
	
	/**
	 * List all active {@link Employee}s only with main data
	 * @return {@link List} from {@link EmployeeDTO}
	 */
	List<EmployeeDTO> listActiveEmployeesWithMainDataOnly();
}
