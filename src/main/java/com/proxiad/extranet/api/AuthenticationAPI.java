package com.proxiad.extranet.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proxiad.extranet.core.service.security.CurrentUser;
import com.proxiad.extranet.core.service.security.SecurityService;

/**
 * REST API for authentication
 *
 * @author Mihail Merkov
 */
@RestController
public class AuthenticationAPI {
	
	/*---------------------------------------------------- SERVICES --------------------------------------------------*/
	@Autowired
	private SecurityService securityService;

	/*------------------------------------------------------ API -----------------------------------------------------*/
	
	/**
	 * Get authenticated user if exists, <code>null</code> otherwise
	 */
	@GetMapping(value="/user", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CurrentUser> user() {
		CurrentUser currentUser = securityService.getLoggedInUser();
		if (currentUser == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(securityService.getLoggedInUser(), HttpStatus.OK);
    }
	
	/**
	 * Logout the authenticated user
	 */
	@GetMapping(value="/logout", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> logout(final HttpServletRequest request, final HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}
