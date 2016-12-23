package com.proxiad.extranet.it.core.repository.feedback;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

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
import com.proxiad.extranet.core.model.feedback.Goal;
import com.proxiad.extranet.core.repository.feedback.BehaviourLabelDao;
import com.proxiad.extranet.core.repository.feedback.CompetencyDao;
import com.proxiad.extranet.core.repository.feedback.FeedbackDao;
import com.proxiad.extranet.core.repository.feedback.GoalDao;
import com.proxiad.extranet.it.helpers.SimpleEmployeeManagerHelper;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FeedbackEntityMappingTest {
	
	private static final int ONE_ELEMENT = 1;
	
	@Autowired
	private SimpleEmployeeManagerHelper employeeManagerHelper;

	@Autowired
	private CompetencyDao competencyDao;
	
	@Autowired
	private GoalDao goalDao;
	
	@Autowired
	private BehaviourLabelDao behaviourLabelDao;
	
	@Autowired
	private FeedbackDao feedbackDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testFeedbackCrud() {
		Employee employee = employeeManagerHelper.createEmployee();
		Goal goal = createGoal();
		Feedback feedback = new Feedback();
		feedback.setManager(employee);
		feedback.setTargetEmployee(employee);
		feedback.setGeneralImpressions("Super slab e");
		feedback.setStartDate(new Date());
		Calendar oneMonthLater = Calendar.getInstance();
		oneMonthLater.setTime(feedback.getStartDate());
		oneMonthLater.add(Calendar.MONTH, 1);
		feedback.setEndDate(oneMonthLater.getTime());
		feedback.setGoals(new HashSet<Goal>());
		feedback.getGoals().add(goal);
		
		Long feedbackId = feedbackDao.save(feedback);
		Assert.assertNotNull(feedbackId);
		feedback = null;
		feedback = feedbackDao.get(feedbackId);
		Assert.assertNotNull(feedback);
		Assert.assertNotNull(feedback.getManager());
		Assert.assertNotNull(feedback.getTargetEmployee());
		Assert.assertNotNull(feedback.getGoals());
		Assert.assertEquals(ONE_ELEMENT, feedback.getGoals().size());
		Assert.assertEquals("Super slab e", feedback.getGeneralImpressions());
		
		Competency competency = createCompetency(feedback);
		feedback.setCompetencies(new HashSet<Competency>());
		feedback.getCompetencies().add(competency);
		
		feedbackDao.update(feedback);
		feedback = null;
		feedback = feedbackDao.get(feedbackId);
		
		Assert.assertNotNull(feedback.getCompetencies());
		Assert.assertEquals(ONE_ELEMENT, feedback.getCompetencies().size());
		BehaviourLabel behaviourLabel = competency.getBehaviourLabel();
		competencyDao.delete(competency);
		feedbackDao.delete(feedback);
		Assert.assertNull(feedbackDao.get(feedbackId));
		cleanup(employee, goal, behaviourLabel);
	}
	
	private Goal createGoal() {
		Goal goal = new Goal();
		goal.setTitle("To be more productive");
		goalDao.save(goal);
		
		return goal;
	}
	
	private BehaviourLabel createBehaviourLabel() {
		BehaviourLabel behaviourLabel = new BehaviourLabel();
		behaviourLabel.setLabel("Some Label");
		behaviourLabel.setDefaultLabel(true);
		behaviourLabelDao.save(behaviourLabel);
		return behaviourLabel;
	}
	
	private Competency createCompetency(final Feedback feedback) {
		Competency competency = new Competency();
		BehaviourLabel behaviourLabel = createBehaviourLabel();
		competency.setBehaviourLabel(behaviourLabel);
		competency.setSelfAppraisalRating(5);
		competency.setManagerRating(4);
		competency.setSelfAppraisalComment("I do my best");
		competency.setManagerComment("It's not enough");
		competency.setFeedback(feedback);
		competencyDao.save(competency);
		
		return competency;
	}
	
	private void cleanup(final Employee employee, final Goal goal, final BehaviourLabel behaviourLabel) {
		goalDao.delete(goal);
		employeeManagerHelper.cleanupEmployee(employee);
		behaviourLabelDao.delete(behaviourLabel);
	}
}
