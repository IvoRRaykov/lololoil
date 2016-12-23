package com.proxiad.extranet.core.repository.user;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.user.UserDetails;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link UserDetails} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class UserDetailsDaoHibernate extends GenericHibernateDao<UserDetails, Long> implements UserDetailsDao {}
