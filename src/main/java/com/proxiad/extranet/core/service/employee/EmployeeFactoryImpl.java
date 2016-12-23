package com.proxiad.extranet.core.service.employee;

import org.springframework.stereotype.Component;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.employee.beans.EmployeeDTO;

/**
 * Factory implementation for {@link Employee} entity
 * 
 * @author Mihail Merkov
 */
@Component
public class EmployeeFactoryImpl implements EmployeeFactory {

	/**
	 * @see com.proxiad.extranet.core.service.employee.EmployeeFactory#createBasicEmployeeDataFrom(com.proxiad.extranet.core.model.employee.Employee)
	 */
	@Override
	public EmployeeDTO createBasicEmployeeDataFrom(final Employee employee) {
		return new EmployeeDTO(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getPathToProfilePicture(), 
				employee.getSex(), employee.isActive(), employee.getCreatedOn(), employee.getCreatedBy() != null ? employee.getCreatedBy().getId() : null, 
				employee.getDetails(), employee.getDateOfJoining(), employee.getDateOfLeaving(), employee.getWorkplace(), employee.getPosition());
	}
}
