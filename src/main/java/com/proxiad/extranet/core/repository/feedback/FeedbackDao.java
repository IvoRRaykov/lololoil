package com.proxiad.extranet.core.repository.feedback;

import com.proxiad.extranet.core.model.feedback.Feedback;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Repository access interface for {@link Feedback} entity
 * 
 * @author Mihail Merkov
 */
public interface FeedbackDao extends CrudDao<Feedback, Long> {}
