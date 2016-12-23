package com.proxiad.extranet.it.core.repository.certification;

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

import com.proxiad.extranet.core.model.certification.Certification;
import com.proxiad.extranet.core.model.certification.EmployeeCertification;
import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.repository.certification.CertificationDao;
import com.proxiad.extranet.core.repository.employee.EmployeeDao;
import com.proxiad.extranet.it.helpers.SimpleEmployeeManagerHelper;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CertificationEmployeeRelationshipMappingTest {
	
	private static final int ONE_RECORD = 1;
	
	@Autowired
	private CertificationDao certificationDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private SimpleEmployeeManagerHelper employeeManagerHelper;

	@Transactional
	@Rollback(false)
	@Test
	public void testCertificationEmployeeMapping() {
		Certification certification = createCertification();
		Employee employee = employeeManagerHelper.createEmployee();
		Long certificationId = certification.getId();
		Long employeeId = employee.getId();
		
		EmployeeCertification employeeCertification = new EmployeeCertification();
		employeeCertification.setCertification(certification);
		employeeCertification.setEmployee(employee);
		employeeCertification.setDate(new Date());
		
		employee.getEmployeeCertifications().add(employeeCertification);
		certification.getEmployeeCertifications().add(employeeCertification);
		employeeDao.update(employee);
		certificationDao.update(certification);
		employee = null;
		certification = null;
		
		employee = employeeDao.get(employeeId);
		certification = certificationDao.get(certificationId);
		
		Assert.assertEquals(ONE_RECORD, employee.getEmployeeCertifications().size());
		Assert.assertEquals(ONE_RECORD, certification.getEmployeeCertifications().size());
		
		certificationDao.delete(certification);
		employeeManagerHelper.cleanupEmployee(employee);
	}
	
	private Certification createCertification() {
		Certification certification = new Certification();
		certification.setName("Java EE1234");
		
		certificationDao.save(certification);
		return certification;
	}
}
