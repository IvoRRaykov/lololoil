package com.proxiad.extranet.it.core.repository.education;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proxiad.extranet.core.model.education.School;
import com.proxiad.extranet.core.repository.education.SchoolDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SchoolEntityMappingTest {
	
	@Autowired
	private SchoolDao schoolDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testSchoolCrud() {
		School school = new School();
		school.setName("Technical University Sofia");
		
		Long id = schoolDao.save(school);
		Assert.assertNotNull(id);
		
		school = null;
		
		school = schoolDao.get(id);
		Assert.assertNotNull(school);
		Assert.assertEquals("Technical University Sofia", school.getName());
		
		schoolDao.delete(school);
		Assert.assertNull(schoolDao.get(id));
	}
}
