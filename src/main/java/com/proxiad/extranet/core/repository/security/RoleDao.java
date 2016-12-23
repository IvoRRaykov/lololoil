package com.proxiad.extranet.core.repository.security;

import com.proxiad.extranet.core.model.security.Role;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Repository access interface for {@link Role} entity
 * 
 * @author Mihail Merkov
 */
public interface RoleDao extends CrudDao<Role, Long> {}
