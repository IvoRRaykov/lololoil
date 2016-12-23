package com.proxiad.extranet.core.repository.education;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.education.School;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link School} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class SchoolGenericDao extends GenericHibernateDao<School, Long> implements SchoolDao {}
