package com.proxiad.extranet.core.repository.feedback;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.feedback.Goal;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Goal} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class GoalDaoHibernate extends GenericHibernateDao<Goal, Long> implements GoalDao {}
