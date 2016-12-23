package com.proxiad.extranet.it.core.repository.expertise;

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
import com.proxiad.extranet.core.model.expertise.EmployeeExpertise;
import com.proxiad.extranet.core.model.expertise.Expertise;
import com.proxiad.extranet.core.repository.employee.EmployeeDao;
import com.proxiad.extranet.core.repository.expertise.ExpertiseDao;
import com.proxiad.extranet.it.helpers.SimpleEmployeeManagerHelper;

import suit.RepositoryMappingAcceptanceTestSuite;


@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeExpertiseRelationshipMappingTest {
	
	private final static int ONE_ELEMENT = 1;
	
	@Autowired
	private SimpleEmployeeManagerHelper employeeManagerHelper;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ExpertiseDao expertiseDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testEmployeeExpertiseRelashionship() {
		Employee employee = employeeManagerHelper.createEmployee();
		Expertise expertise = createExpertise();
		Long employeeId = employee.getId();
		Long expertiseId = expertise.getId();
		
		EmployeeExpertise employeeExpertise = new EmployeeExpertise();
		employeeExpertise.setEmployee(employee);
		employeeExpertise.setExpertise(expertise);
		employeeExpertise.setDate(new Date());
		
		employee.getEmployeeExpertises().add(employeeExpertise);
		expertise.getEmployeeExpertises().add(employeeExpertise);
		employeeDao.update(employee);
		expertiseDao.update(expertise);
		employee = null;
		expertise = null;
		
		employee = employeeDao.get(employeeId);
		expertise = expertiseDao.get(expertiseId);
		
		Assert.assertNotNull(employee.getEmployeeExpertises());
		Assert.assertEquals(ONE_ELEMENT, employee.getEmployeeExpertises().size());
		
		Assert.assertNotNull(expertise.getEmployeeExpertises());
		Assert.assertEquals(ONE_ELEMENT, expertise.getEmployeeExpertises().size());
		
		Assert.assertEquals(employee.getEmployeeExpertises().iterator().next(), expertise.getEmployeeExpertises().iterator().next());

		cleanup(employee, expertise);
	}
	
	private void cleanup(final Employee employee, final Expertise expertise) {
		employeeManagerHelper.cleanupEmployee(employee);
		expertiseDao.delete(expertise);
	}
	
	private Expertise createExpertise() {
		Expertise expertise = new Expertise();
		expertise.setDescription("Teamwork");
		expertiseDao.save(expertise);
		return expertise;
	}
}
