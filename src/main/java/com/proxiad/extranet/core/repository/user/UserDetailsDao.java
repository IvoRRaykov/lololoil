package com.proxiad.extranet.core.repository.user;

import com.proxiad.extranet.core.model.user.UserDetails;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Repository access interface for {@link UserDetails} entity
 * 
 * @author Mihail Merkov
 */
public interface UserDetailsDao extends CrudDao<UserDetails, Long> {}
