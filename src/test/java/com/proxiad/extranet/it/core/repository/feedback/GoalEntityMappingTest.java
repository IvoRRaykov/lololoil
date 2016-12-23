package com.proxiad.extranet.it.core.repository.feedback;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proxiad.extranet.core.model.feedback.Goal;
import com.proxiad.extranet.core.repository.feedback.GoalDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GoalEntityMappingTest {
	
	@Autowired
	private GoalDao goalDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testGoalCrud() {
		Goal goal = new Goal();
		goal.setTitle("To be more productive");
		
		Long id = goalDao.save(goal);
		Assert.assertNotNull(id);
		
		goal = null;
		
		goal = goalDao.get(id);
		Assert.assertNotNull(goal);
		Assert.assertEquals("To be more productive", goal.getTitle());
		
		goalDao.delete(goal);
		Assert.assertNull(goalDao.get(id));
	}
}
