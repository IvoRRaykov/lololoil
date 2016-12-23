package com.proxiad.extranet.core.model.param;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This entity will manage the parameters as entity 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "PARAM")
public class Parameter implements Serializable {
	
	private static final long serialVersionUID = -7559958315162905151L;
	
	@Id
	@Column(name = "ID_PARAM")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "PARAM_NAME")
	private String name;
	
	@Column(name = "PARAM_VALUE")
	private String value;
	
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
	 * Get the name of the parameter
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the parameter name
	 * @param name {@link String}
	 */
	public void setName(final String name) {
		this.name = name;
	}
	
	/**
	 * Get the value of parameter
	 * @return
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Set the value of the parameter
	 * @param value {@link String}
	 */
	public void setValue(final String value) {
		this.value = value;
	}
	
	/**
	 * Get the description of parameter
	 * @return {@link String}
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the parameter description
	 * @param description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
}
