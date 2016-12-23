package com.proxiad.extranet.core.model.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.proxiad.extranet.core.model.user.User;

/**
 * Role entity keeps the roles for different {@link User}s in the system
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "ROLE")
public class Role implements Serializable {
	
	private static final long serialVersionUID = 4393822503937472881L;

	@Id
	@Column(name = "ID_ROLE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
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
	 * Get the name of the role
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the role
	 * @param name {@link String}
	 */
	public void setName(final String name) {
		this.name = name;
	}
	
	/**
	 * Get the description of the role
	 * @return {@link String}
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the description of the role
	 * @param description String
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
}
