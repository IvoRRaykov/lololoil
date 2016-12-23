package com.proxiad.extranet.it.core.repository.education;

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

import com.proxiad.extranet.core.model.education.Education;
import com.proxiad.extranet.core.model.education.School;
import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.repository.education.EducationDao;
import com.proxiad.extranet.core.repository.education.SchoolDao;
import com.proxiad.extranet.it.helpers.SimpleEmployeeManagerHelper;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EducationEntityMappingTest {
	
	@Autowired
	private EducationDao educationDao;
	
	@Autowired
	private SchoolDao schoolDao;
	
	@Autowired
	private SimpleEmployeeManagerHelper employeeManagerHelper;

	@Transactional
	@Rollback(false)
	@Test
	public void testEducationCrud() {
		Education education = new Education();
		School school = createSchool();
		Employee employee = employeeManagerHelper.createEmployee();
		
		education.setDegree("Master");
		education.setDescription("Computer Systems and Technologies");
		education.setStartDate(new Date());
		education.setFieldOfStudy("Computer Engineering");
		education.setSchool(school);
		education.setEmployee(employee);
		
		employee.getEducations().add(education);		
		Long id = educationDao.save(education);
		Assert.assertNotNull(id);
		
		education = null;
		
		education = educationDao.get(id);
		Assert.assertNotNull(education);
		Assert.assertEquals("Computer Systems and Technologies", education.getDescription());
		Assert.assertEquals("Master", education.getDegree());
		Assert.assertEquals(education.getEmployee().getId(), employee.getId());
		
		educationDao.delete(education);
		schoolDao.delete(school);
		employeeManagerHelper.cleanupEmployee(employee);
		Assert.assertNull(educationDao.get(id));
	}
	
	private School createSchool() {
		School school = new School();
		school.setName("Technical University Sofia");
		schoolDao.save(school);
		return school;
	}
}
