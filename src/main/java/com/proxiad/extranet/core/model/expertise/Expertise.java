package com.proxiad.extranet.core.model.expertise;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.math.NumberUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxiad.extranet.core.model.employee.Employee;

/**
 * Expertise entity keeps information about the specific expertise which belongs to some {@link Employee}</br>
 * Example: <code>Database modelling | Algorithms and abstract data structures</code>
 *  
 * @author Mihail Merkov
 */
@JsonIgnoreProperties({"employeeExpertises"})
@Entity
@Table(name = "EXPERTISE")
public class Expertise implements Serializable {
	
	private static final long serialVersionUID = -6378484510617480404L;

	@Id
	@Column(name = "ID_EXPERTISE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.expertise", cascade = CascadeType.ALL)
	private Set<EmployeeExpertise> employeeExpertises = new HashSet<EmployeeExpertise>(NumberUtils.INTEGER_ZERO);
	
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
	 * Get the description
	 * @return {@link String}
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the description
	 * @param description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	
	/**
	 * Get the {@link Set} from {@link EmployeeExpertise} employees of given expertise
	 * @return {@link Set} from {@link EmployeeExpertise}
	 */
	public Set<EmployeeExpertise> getEmployeeExpertises() {
		return employeeExpertises;
	}
	
	/**
	 * Set the {@link Set} from {@link EmployeeExpertise} employees of given expertise
	 * @param employeeExpertises
	 */
	public void setEmployeeExpertises(final Set<EmployeeExpertise> employeeExpertises) {
		this.employeeExpertises = employeeExpertises;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Expertise other = (Expertise) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
