package com.proxiad.extranet.it.core.repository.interest;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proxiad.extranet.core.model.interest.Interest;
import com.proxiad.extranet.core.repository.interest.InterestDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InterestEntityMappingTest {
	
	@Autowired
	private InterestDao interestDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testInterestCrud() {
		Interest interest = new Interest();
		interest.setTitle("Programming");
		
		Long id = interestDao.save(interest);
		Assert.assertNotNull(id);
		
		interest = null;
		
		interest = interestDao.get(id);
		Assert.assertNotNull(interest);
		Assert.assertEquals("Programming", interest.getTitle());
		
		interestDao.delete(interest);
		Assert.assertNull(interestDao.get(id));
	}
}
