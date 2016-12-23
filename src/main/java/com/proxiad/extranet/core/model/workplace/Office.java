package com.proxiad.extranet.core.model.workplace;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Office entity keeps information about the city where is deployed the office
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "OFFICE")
public class Office implements Serializable {
	
	private static final long serialVersionUID = 4048489091124506548L;

	@Id
	@Column(name = "ID_OFFICE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "POST_CODE")
	private String postCode;
	
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
	 * Get the city where is deployed this office
	 * @return {@link String}
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Set the city where is deployed this office
	 * @param city {@link String}
	 */
	public void setCity(final String city) {
		this.city = city;
	}
	
	/**
	 * Get the post code where is deployed this office
	 * @return {@link String}
	 */
	public String getPostCode() {
		return postCode;
	}
	
	/**
	 * Set the post code where is deployed this office
	 * @param postCode {@link String}
	 */
	public void setPostCode(final String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
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
		Office other = (Office) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (postCode == null) {
			if (other.postCode != null)
				return false;
		} else if (!postCode.equals(other.postCode))
			return false;
		return true;
	}	
}
