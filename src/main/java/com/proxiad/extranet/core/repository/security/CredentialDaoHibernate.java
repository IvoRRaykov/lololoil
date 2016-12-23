package com.proxiad.extranet.core.repository.security;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.security.Credential;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Credential} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class CredentialDaoHibernate extends GenericHibernateDao<Credential, Long> implements CredentialDao {}
