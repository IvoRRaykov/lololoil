package com.proxiad.extranet.it.core.repository.workplace;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proxiad.extranet.core.model.workplace.Office;
import com.proxiad.extranet.core.repository.workplace.OfficeDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OfficeEntityMappingTest {
	
	@Autowired
	private OfficeDao officeDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testOfficeCrud() {
		Office office = new Office();
		office.setCity("Plovdiv");
		office.setPostCode("4000");
		
		Long id = officeDao.save(office);
		Assert.assertNotNull(id);
		
		office = null;
		
		office = officeDao.get(id);
		Assert.assertNotNull(office);
		Assert.assertEquals("Plovdiv", office.getCity());
		
		officeDao.delete(office);
		Assert.assertNull(officeDao.get(id));
	}
}
