package com.proxiad.extranet.it.core.repository.interest;

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
import com.proxiad.extranet.core.model.interest.EmployeeInterest;
import com.proxiad.extranet.core.model.interest.Interest;
import com.proxiad.extranet.core.repository.employee.EmployeeDao;
import com.proxiad.extranet.core.repository.interest.InterestDao;
import com.proxiad.extranet.it.helpers.SimpleEmployeeManagerHelper;

import suit.RepositoryMappingAcceptanceTestSuite;


@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeInterestRelationshipMappingTest {
	
	private final static int ONE_ELEMENT = 1;
	
	@Autowired
	private SimpleEmployeeManagerHelper employeeManagerHelper;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private InterestDao interestDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testEmployeeInterestRelashionship() {
		Employee employee = employeeManagerHelper.createEmployee();
		Interest interest = createInterest();
		Long employeeId = employee.getId();
		Long interestId = interest.getId();
		
		EmployeeInterest employeeInterest = new EmployeeInterest();
		employeeInterest.setEmployee(employee);
		employeeInterest.setInterest(interest);
		employeeInterest.setDate(new Date());
		
		employee.getEmployeeInterests().add(employeeInterest);
		interest.getEmployeeInterests().add(employeeInterest);
		employeeDao.update(employee);
		interestDao.update(interest);
		employee = null;
		interest = null;
		
		employee = employeeDao.get(employeeId);
		interest = interestDao.get(interestId);
		
		Assert.assertNotNull(employee.getEmployeeInterests());
		Assert.assertEquals(ONE_ELEMENT, employee.getEmployeeInterests().size());
		
		Assert.assertNotNull(interest.getEmployeeInterests());
		Assert.assertEquals(ONE_ELEMENT, interest.getEmployeeInterests().size());
		
		Assert.assertEquals(employee.getEmployeeInterests().iterator().next(), interest.getEmployeeInterests().iterator().next());

		cleanup(employee, interest);
	}
	
	private void cleanup(final Employee employee, final Interest interest) {
		employeeManagerHelper.cleanupEmployee(employee);
		interestDao.delete(interest);
	}
	
	private Interest createInterest() {
		Interest interest = new Interest();
		interest.setTitle("Programming-Mongaming");
		
		interestDao.save(interest);
		return interest;
	}
}
