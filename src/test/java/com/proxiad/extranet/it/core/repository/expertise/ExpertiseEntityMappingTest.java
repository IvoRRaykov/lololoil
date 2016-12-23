package com.proxiad.extranet.it.core.repository.expertise;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proxiad.extranet.core.model.expertise.Expertise;
import com.proxiad.extranet.core.repository.expertise.ExpertiseDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ExpertiseEntityMappingTest {
	
	@Autowired
	private ExpertiseDao expertiseDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testExpertiseCrud() {
		Expertise expertise = new Expertise();
		expertise.setDescription("Teamwork");
		
		Long id = expertiseDao.save(expertise);
		Assert.assertNotNull(id);
		
		expertise = null;
		
		expertise = expertiseDao.get(id);
		Assert.assertNotNull(expertise);
		Assert.assertEquals("Teamwork", expertise.getDescription());
		
		expertiseDao.delete(expertise);
		Assert.assertNull(expertiseDao.get(id));
	}
}
