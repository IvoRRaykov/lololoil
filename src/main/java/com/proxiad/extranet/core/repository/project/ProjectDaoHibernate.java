package com.proxiad.extranet.core.repository.project;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.project.Project;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Project} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class ProjectDaoHibernate extends GenericHibernateDao<Project, Long> implements ProjectDao {}
