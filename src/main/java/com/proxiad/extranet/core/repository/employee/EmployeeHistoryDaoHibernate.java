package com.proxiad.extranet.core.repository.employee;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.employee.EmployeeHistory;
import com.proxiad.extranet.core.repository.audit.AuditDaoHibernate;

/**
 * Repository access implementation for {@link EmployeeHistory} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class EmployeeHistoryDaoHibernate extends AuditDaoHibernate<EmployeeHistory, Employee> implements EmployeeHistoryDao {}
