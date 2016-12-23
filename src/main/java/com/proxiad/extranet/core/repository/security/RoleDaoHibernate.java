package com.proxiad.extranet.core.repository.security;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.security.Role;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Role} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class RoleDaoHibernate extends GenericHibernateDao<Role, Long> implements RoleDao {}
