package com.proxiad.extranet.core.service.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Represents the currently logged in user.
 * 
 * @author Emil Doychev (e.doychev@proxiad.com)
 *
 */
public class CurrentUser extends User {

	private static final long serialVersionUID = -2973806243779833959L;
	
	private String 	firstName;
	private String 	lastName;
	private Long	userId;
	
	/**
	 * The constructor 
	 * 
	 * @param username The login of the {@User}
	 * @param password The {@User}'s password. It has to be encoded with BCrypt.
	 * @param firstName The {@User}'s first name.
	 * @param lastName The {@User}'s last name.
	 * @param userid The primary key vale of the corresponding {@User}'s entity.  
	 * @param enabled True if the user is active. Otherwise - false.  
	 * @param authorities Collection of all roles assigned to the user.
	 */
	public CurrentUser(
			String username, 
			String password,
			String firstName,
			String lastName,
			Long userId,
			boolean enabled, 
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, true, true, true, authorities);
		
		this.firstName 	= firstName;
		this.lastName 	= lastName;
		this.userId		= userId;
	}

	/**
	 * Get the first name of the user
	 * @return {@link String}
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Get the last name of the user
	 * @return {@link String}
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Get the id of the corresponding {@link User}
	 * @return {@link Long}
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		
		return result;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		CurrentUser other = (CurrentUser)obj;
		
		if (getUsername() == null) {
			if (other.getUsername() != null)
				return false;
		} else if (!getUsername().equals(other.getUsername())) 
			return false;
		
		if (getPassword() == null) {
			if (other.getPassword() != null)
				return false;
		} else if (!getPassword().equals(other.getPassword())) 
			return false;
		
		if (isEnabled() != other.isEnabled())
			return false;

		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName)) 
			return false;
		
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName)) 
			return false;

		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId)) 
			return false;

		return true;
	}
}
