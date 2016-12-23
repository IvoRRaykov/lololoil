package com.proxiad.extranet.core.model.project;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.math.NumberUtils;

import com.proxiad.extranet.core.model.client.Client;
import com.proxiad.extranet.core.model.employee.Employee;

/**
 * Project entity keeps information about the project which some {@link Employee} works
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "PROJECT")
public class Project implements Serializable {

	private static final long serialVersionUID = 3825084165564984824L;

	@Id
	@Column(name = "ID_PROJECT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENT", nullable = false)
	private Client client;
	
	@Column(name = "BUSINESS_DOMAIN")
	private String businessDomain;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.project", cascade = CascadeType.ALL)
	private Set<EmployeeProject> employeeProjects = new HashSet<EmployeeProject>(NumberUtils.INTEGER_ZERO);
	
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
	 * Get the name of the project
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the project
	 * @return {@link String}
	 */
	public void setName(final String name) {
		this.name = name;
	}
	
	/**
	 * Get description of the project
	 * @return {@link String}
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set description of the project
	 * @param description {@link String}
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	
	/**
	 * Get the {@link Client} for which is this project
	 * @return {@link Client}
	 */
	public Client getClient() {
		return client;
	}
	
	/**
	 * Set the {@link Client} for which is this project
	 * @param client {@link Client}
	 */
	public void setClient(final Client client) {
		this.client = client;
	}
	
	/**
	 * Get the business domain of the project
	 * @return {@link String}
	 */
	public String getBusinessDomain() {
		return businessDomain;
	}
	
	/**
	 * Set the business domain of the project
	 * @param businessDomain
	 */
	public void setBusinessDomain(final String businessDomain) {
		this.businessDomain = businessDomain;
	}
	
	/**
	 * Get a {@link Set} from {@link Employee} worked on this project
	 * @return {@link Set} from {@link Employee} 
	 */
	public Set<EmployeeProject> getEmployeeProjects() {
		return employeeProjects;
	}
	
	/**
	 * Set a {@link Set} from {@link Employee} worked on this project
	 * @param employeeProjects {@link Set} from {@link Employee} 
	 */
	public void setEmployeeProjects(final Set<EmployeeProject> employeeProjects) {
		this.employeeProjects = employeeProjects;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businessDomain == null) ? 0 : businessDomain.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Project other = (Project) obj;
		if (businessDomain == null) {
			if (other.businessDomain != null)
				return false;
		} else if (!businessDomain.equals(other.businessDomain))
			return false;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
