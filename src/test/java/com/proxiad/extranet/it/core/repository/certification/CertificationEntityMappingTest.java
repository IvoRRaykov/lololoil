package com.proxiad.extranet.it.core.repository.certification;

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
import com.proxiad.extranet.core.repository.certification.CertificationDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CertificationEntityMappingTest {
	
	@Autowired
	private CertificationDao certificationDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testCertificationCrud() {
		Certification certification = new Certification();
		certification.setName("Java EE1234");
		
		Long id = certificationDao.save(certification);
		Assert.assertNotNull(id);
		
		certification = null;
		
		certification = certificationDao.get(id);
		Assert.assertNotNull(certification);
		Assert.assertEquals("Java EE1234", certification.getName());
		
		certificationDao.delete(certification);
		Assert.assertNull(certificationDao.get(id));
	}
}
