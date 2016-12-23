package com.proxiad.extranet.core.model.user;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.math.NumberUtils;

import com.proxiad.extranet.core.model.security.Credential;
import com.proxiad.extranet.core.model.security.Role;


/**
 * User entity represents the basic data for each user into the system 
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
	private static final long serialVersionUID = -500058020892903251L;
	
	@Id
	@Column(name = "ID_USER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "PROFILE_PICTURE_PATH")
	private String pathToProfilePicture;
	
	@Column(name = "SEX")
	private char sex;
	
	@Column(name = "ACTIVE", nullable = false)
	private boolean active;
	
	@Column(name = "CREATED_ON")
	private Date createdOn;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="CREATED_BY")
	private User createdBy;	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USER_DETAILS", nullable = false)
	private UserDetails details;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CREDENTIAL", nullable = false)
	private Credential credential;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE", 
			joinColumns = {
				@JoinColumn(name = "ID_USER", nullable = false, updatable = false) },
				inverseJoinColumns = { @JoinColumn(name = "ID_ROLE", nullable = false, updatable = false) 
			})
	private Set<Role> roles = new HashSet<Role>(NumberUtils.INTEGER_ZERO);
	
	
	/**
	 * Get the id of the entity
	 * @return {@link Long}
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Set the id of the entity
	 * @param id {@link Long}
	 */
	public void setId(final Long id) {
		this.id = id;
	}
	
	/**
	 * Get the first name
	 * @return {@link String}
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Set the first name
	 * @param firstName {@link String}
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Get the last name
	 * @return {@link String}
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Set the last name
	 * @param lastName {@link String}
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Get path to the profile picture
	 * @return {@link String}
	 */
	public String getPathToProfilePicture() {
		return pathToProfilePicture;
	}
	
	/**
	 * Set path to the profile picture
	 * @param pathToProfilePicture
	 */
	public void setPathToProfilePicture(final String pathToProfilePicture) {
		this.pathToProfilePicture = pathToProfilePicture;
	}

	/**
	 * Get a sex
	 * @return {@link String}
	 */
	public char getSex() {
		return sex;
	}
	
	/**
	 * Set a sex
	 * @param sex
	 */
	public void setSex(char sex) {
		this.sex = sex;
	}
	
	/**
	 * Flag which indicate whether the user is active or not
	 * @return boolean
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Set a flag which indicate whether the user is active or not
	 * @param active boolean
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Get a date of creation of this user
	 * @return {@link Date}
	 */
	public Date getCreatedOn() {
		return createdOn;
	}
	
	/**
	 * Set a date of creation of this user
	 * @return {@link Date}
	 */
	public void setCreatedOn(final Date createdOn) {
		this.createdOn = createdOn;
	}
	
	/**
	 * Get the {@link User} who creates this one
	 * @return {@link User}
	 */
	public User getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * Get the {@link User} who creates this one
	 * @param createdBy {@link User}
	 */
	public void setCreatedBy(final User createdBy) {
		this.createdBy = createdBy;
	}
	
	/**
	 * Get the {@link UserDetails}
	 * @return {@link UserDetails}
	 */
	public UserDetails getDetails() {
		return details;
	}
	
	/**
	 * Set the {@link UserDetails}
	 * @param details {@link UserDetails}
	 */
	public void setDetails(final UserDetails details) {
		this.details = details;
	}
	
	/**
	 * Get the {@link Credential} for the user
	 * @return {@link Credential}
	 */
	public Credential getCredential() {
		return credential;
	}
	
	/**
	 * Set the {@link Credential} for the user
	 * @param credential
	 */
	public void setCredential(final Credential credential) {
		this.credential = credential;
	}
	
	/**
	 * Get a {@link Set} from {@link Role}s which belongs to this user
	 * @return {@link Set} from {@link Role}
	 */
	public Set<Role> getRoles() {
		return roles;
	}
	
	/**
	 * Set a {@link Set} from {@link Role}s which belongs to this user
	 * @param roles
	 */
	public void setRoles(final Set<Role> roles) {
		this.roles = roles;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((pathToProfilePicture == null) ? 0 : pathToProfilePicture.hashCode());
		result = prime * result + sex;
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
		User other = (User) obj;
		if (active != other.active)
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (pathToProfilePicture == null) {
			if (other.pathToProfilePicture != null)
				return false;
		} else if (!pathToProfilePicture.equals(other.pathToProfilePicture))
			return false;
		if (sex != other.sex)
			return false;
		return true;
	}
}
