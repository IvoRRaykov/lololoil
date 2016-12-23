package com.proxiad.extranet.core.service.security;

/**
 * Security service
 * 
 * @author Emil Doychev (e.doychev@proxiad.com)
 * @author Mihail Merkov
 */
public interface SecurityService {

	/**
	 * Find the currently logged in user. If no user is found - returns null.
	 * @return {@link CurrentUser}
	 */
	public CurrentUser getLoggedInUser();
}
