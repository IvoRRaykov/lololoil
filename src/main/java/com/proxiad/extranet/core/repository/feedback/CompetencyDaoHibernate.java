package com.proxiad.extranet.core.repository.feedback;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.feedback.Competency;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Competency} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class CompetencyDaoHibernate extends GenericHibernateDao<Competency, Long> implements CompetencyDao {}
