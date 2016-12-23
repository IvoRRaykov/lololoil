package com.proxiad.extranet.core.service.employee;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.employee.beans.EmployeeDTO;

/**
 * Factory for {@link Employee} entity
 * 
 * @author Mihail Merkov
 */
public interface EmployeeFactory {
	
	/**
	 * Create a basic instance of an {@link Employee} without all non required relations
	 * @param employee {@link Employee}
	 * @return {@link EmployeeDTO}
	 */
	EmployeeDTO createBasicEmployeeDataFrom(final Employee employee);
}
