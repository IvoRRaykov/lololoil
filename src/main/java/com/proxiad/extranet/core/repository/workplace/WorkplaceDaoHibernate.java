package com.proxiad.extranet.core.repository.workplace;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.workplace.Workplace;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Workplace} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class WorkplaceDaoHibernate extends GenericHibernateDao<Workplace, Long> implements WorkplaceDao {}
