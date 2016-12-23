package com.proxiad.extranet.core.repository.workplace;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.workplace.Office;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Office} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class OfficeDaoHibernate extends GenericHibernateDao<Office, Long> implements OfficeDao {}
