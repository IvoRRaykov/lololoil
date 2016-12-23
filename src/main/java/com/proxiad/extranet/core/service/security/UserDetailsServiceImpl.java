package com.proxiad.extranet.core.service.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proxiad.extranet.core.model.security.Role;
import com.proxiad.extranet.core.model.user.User;
import com.proxiad.extranet.core.repository.user.UserDao;

/**
 * Custom UserDetailsService implementation 
 * 
 * @author Emil Doychev (e.doychev@proxiad.com)
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	/*-------------------------------------------------- REPOSITORIES ------------------------------------------------*/
	@Autowired
	private UserDao userDao;
	
	/*------------------------------------------------------ API -----------------------------------------------------*/
	/**
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(final String username) {
		
		User user = userDao.getByLogin(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return new CurrentUser(
				user.getCredential().getLogin(), 
				user.getCredential().getPassword(),
				user.getFirstName(),
				user.getLastName(),
				user.getId(),
				user.isActive(), 
				grantedAuthorities);
	}

}
