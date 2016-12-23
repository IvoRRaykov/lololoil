package com.proxiad.extranet.it.core.repository.feedback;

import java.util.Calendar;
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
import com.proxiad.extranet.core.model.feedback.BehaviourLabel;
import com.proxiad.extranet.core.model.feedback.Competency;
import com.proxiad.extranet.core.model.feedback.Feedback;
import com.proxiad.extranet.core.repository.feedback.BehaviourLabelDao;
import com.proxiad.extranet.core.repository.feedback.CompetencyDao;
import com.proxiad.extranet.core.repository.feedback.FeedbackDao;
import com.proxiad.extranet.it.helpers.SimpleEmployeeManagerHelper;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CompetencyEntityMappingTest {
	
	@Autowired
	private BehaviourLabelDao behaviourLabelDao;
	
	@Autowired
	private CompetencyDao competencyDao;
	
	@Autowired
	private SimpleEmployeeManagerHelper employeeManagerHelper;
	
	@Autowired
	private FeedbackDao feedbackDao;


	@Transactional
	@Rollback(false)
	@Test
	public void testCompetencyCrud() {
		Competency competency = new Competency();
		BehaviourLabel behaviourLabel = createBehaviourLabel();
		Feedback feedback = createFeedback();
		competency.setBehaviourLabel(behaviourLabel);
		competency.setSelfAppraisalRating(5);
		competency.setManagerRating(4);
		competency.setSelfAppraisalComment("I do my best");
		competency.setManagerComment("It's not enough");
		competency.setFeedback(feedback);
		
		Long competencyId = competencyDao.save(competency);
		Long behaviourLabelId = behaviourLabel.getId();
		Assert.assertNotNull(competencyId);
		
		behaviourLabel = null;
		competency = null;
		
		competency = competencyDao.get(competencyId);
		
		Assert.assertNotNull(competency);
		Assert.assertNotNull(competency.getBehaviourLabel());
		Assert.assertEquals(behaviourLabelId, competency.getBehaviourLabel().getId());
		Assert.assertEquals(competencyId, competency.getId());
		Assert.assertEquals(4, competency.getManagerRating());
		Assert.assertEquals("I do my best", competency.getSelfAppraisalComment());
		
		competencyDao.delete(competency);
		behaviourLabelDao.delete(competency.getBehaviourLabel());
		cleanupFeedback(feedback);
		Assert.assertNull(behaviourLabelDao.get(behaviourLabelId));
		Assert.assertNull(competencyDao.get(competencyId));
	}
	
	public Feedback createFeedback() {
		Employee employee = employeeManagerHelper.createEmployee();
		Feedback feedback = new Feedback();
		feedback.setManager(employee);
		feedback.setTargetEmployee(employee);
		feedback.setGeneralImpressions("Super slab e");
		feedback.setStartDate(new Date());
		Calendar oneMonthLater = Calendar.getInstance();
		oneMonthLater.setTime(feedback.getStartDate());
		oneMonthLater.add(Calendar.MONTH, 1);
		feedback.setEndDate(oneMonthLater.getTime());
		feedbackDao.save(feedback);
		
		return feedback;
	}
	
	private BehaviourLabel createBehaviourLabel() {
		BehaviourLabel behaviourLabel = new BehaviourLabel();
		behaviourLabel.setLabel("Some Label");
		behaviourLabel.setDefaultLabel(true);
		behaviourLabelDao.save(behaviourLabel);
		return behaviourLabel;
	}
	
	private void cleanupFeedback(final Feedback feedback) {
		Employee employee = feedback.getManager();

		feedbackDao.delete(feedback);
		employeeManagerHelper.cleanupEmployee(employee);
	}
}
