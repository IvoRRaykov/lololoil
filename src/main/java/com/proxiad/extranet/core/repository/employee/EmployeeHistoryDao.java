package com.proxiad.extranet.core.repository.employee;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.employee.EmployeeHistory;
import com.proxiad.extranet.core.repository.audit.AuditDao;

/**
 * Repository access interface for {@link EmployeeHistory} entity
 * 
 * @author Mihail Merkov
 */
public interface EmployeeHistoryDao extends AuditDao<EmployeeHistory, Employee> {}