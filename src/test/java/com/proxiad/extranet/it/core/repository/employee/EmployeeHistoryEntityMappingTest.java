package com.proxiad.extranet.it.core.repository.employee;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.employee.EmployeeHistory;
import com.proxiad.extranet.core.repository.employee.EmployeeHistoryDao;
import com.proxiad.extranet.it.helpers.SimpleEmployeeManagerHelper;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeHistoryEntityMappingTest {
	
	@Autowired
	private EmployeeHistoryDao employeeHistoryDao;
	
	@Autowired
	private SimpleEmployeeManagerHelper employeeManagerHelper;	

	@Transactional
	@Rollback(false)
	@Test
	public void testEmployeeCrud() {
		EmployeeHistory employeeAudit = new EmployeeHistory();
		Employee employee = employeeManagerHelper.createEmployee();
		employeeAudit.setModifiedBy(employee);
		employeeAudit.setModifiedOn(new Date());
		employeeAudit.setNameOfField("employee.position");
		employeeAudit.setOldValue("Java Junior");
		employeeAudit.setNewValue("Java Senior Software Engineer");
		employeeAudit.setTarget(employee);
		
		Long id = employeeHistoryDao.save(employeeAudit);
		Assert.assertNotNull(id);
		
		employeeAudit = null;
		
		employeeAudit = employeeHistoryDao.get(id);
		Assert.assertNotNull(employeeAudit);
		Assert.assertNotNull(employeeAudit.getModifiedBy());
		Assert.assertNotNull(employeeAudit.getTarget());
		Assert.assertEquals("Java Junior", employeeAudit.getOldValue());
		Assert.assertEquals("employee.position", employeeAudit.getNameOfField());
		
		employeeHistoryDao.delete(employeeAudit);
		Assert.assertNull(employeeHistoryDao.get(id));
		employeeManagerHelper.cleanupEmployee(employee);
	}
}
