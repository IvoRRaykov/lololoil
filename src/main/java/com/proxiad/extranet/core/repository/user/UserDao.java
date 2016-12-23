package com.proxiad.extranet.core.repository.user;

import com.proxiad.extranet.core.model.user.User;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Repository access interface for {@link User} entity
 * 
 * @author Mihail Merkov
 */
public interface UserDao extends CrudDao<User, Long> {
	
	/**
	 * Find {@link User} by its related {@Credential} login.
	 * 
	 * @param login The unique user login
	 * @return The {@link User} if any found. Otherwise null. 
	 */
	User getByLogin(final String login);
}
