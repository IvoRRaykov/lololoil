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

import com.proxiad.extranet.core.model.feedback.BehaviourLabel;
import com.proxiad.extranet.core.repository.feedback.BehaviourLabelDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BehaviourLabelEntityMappingTest {
	
	@Autowired
	private BehaviourLabelDao behaviourLabelDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testBehaviourLabelCrud() {
		BehaviourLabel behaviourLabel = new BehaviourLabel();
		behaviourLabel.setLabel("Some Label");
		behaviourLabel.setDefaultLabel(true);
		
		Long id = behaviourLabelDao.save(behaviourLabel);
		Assert.assertNotNull(id);
		
		behaviourLabel = null;
		
		behaviourLabel = behaviourLabelDao.get(id);
		Assert.assertNotNull(behaviourLabel);
		Assert.assertEquals("Some Label", behaviourLabel.getLabel());
		
		behaviourLabelDao.delete(behaviourLabel);
		Assert.assertNull(behaviourLabelDao.get(id));
	}
}
