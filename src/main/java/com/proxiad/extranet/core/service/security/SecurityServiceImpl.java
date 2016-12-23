package com.proxiad.extranet.core.service.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Security service
 * @author Emil Doychev (e.doychev@proxiad.com)
 * @author Mihail Merkov
 */
@Service
public class SecurityServiceImpl implements SecurityService {

	/**
	 * @see com.proxiad.trombinoscope.core.service.security.SecurityService#getLoggedInUser()
	 */
	@Override
	public CurrentUser getLoggedInUser() {
		Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
		if (currentUser instanceof CurrentUser) {
            return (CurrentUser)currentUser;
        }

        return null;
    }
}
