package com.proxiad.extranet.core.repository.feedback;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.feedback.Feedback;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Feedback} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class FeedbackDaoHibernate extends GenericHibernateDao<Feedback, Long> implements FeedbackDao {}
