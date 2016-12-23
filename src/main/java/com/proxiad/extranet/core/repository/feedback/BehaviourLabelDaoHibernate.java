package com.proxiad.extranet.core.repository.feedback;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.feedback.BehaviourLabel;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link BehaviourLabel} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class BehaviourLabelDaoHibernate extends GenericHibernateDao<BehaviourLabel, Long> implements BehaviourLabelDao {}
