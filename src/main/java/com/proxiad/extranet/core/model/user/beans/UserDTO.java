package com.proxiad.extranet.core.model.user.beans;

import java.util.Date;

import com.proxiad.extranet.core.model.user.User;
import com.proxiad.extranet.core.model.user.UserDetails;

/**
 * Data transfer object for {@link User} entity
 * 
 * @author Mihail Merkov
 */
public class UserDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String pathToProfilePicture;
	private char sex;
	private boolean active;
	private Date createdOn;
	private Long createdById;
	private UserDetails details;
	
	public UserDTO() {};
	
	public UserDTO(final Long id, final String firstName, final String lastName,
			final String pathToProfilePicture, final char sex, final boolean active,
			final Date createdOn, final Long createdById, final UserDetails details) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pathToProfilePicture = pathToProfilePicture;
		this.sex = sex;
		this.active = active;
		this.createdOn = createdOn;
		this.createdById = createdById;
		this.details = details;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPathToProfilePicture() {
		return pathToProfilePicture;
	}
	
	public void setPathToProfilePicture(String pathToProfilePicture) {
		this.pathToProfilePicture = pathToProfilePicture;
	}
	
	public char getSex() {
		return sex;
	}
	
	public void setSex(char sex) {
		this.sex = sex;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public Long getCreatedById() {
		return createdById;
	}
	
	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}

	public UserDetails getDetails() {
		return details;
	}

	public void setDetails(UserDetails details) {
		this.details = details;
	}
}
