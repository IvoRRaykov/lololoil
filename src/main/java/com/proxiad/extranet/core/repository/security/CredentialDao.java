package com.proxiad.extranet.core.repository.security;

import com.proxiad.extranet.core.model.security.Credential;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Repository access interface for {@link Credential} entity
 * 
 * @author Mihail Merkov
 */
public interface CredentialDao extends CrudDao<Credential, Long> {}
