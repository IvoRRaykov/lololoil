package com.proxiad.extranet.core.model.certification;

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
 * Certification entity will serve to keep information about the certifications
 * acquired to specific {@link Employee}
 * 
 * @author Mihail Merkov
 */
@JsonIgnoreProperties({"employeeCertifications"})
@Entity
@Table(name = "CERTIFICATION")
public class Certification implements Serializable {
	
	private static final long serialVersionUID = -7497855987857504221L;

	@Id
	@Column(name = "ID_CERTIFICATION")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.certification", cascade = CascadeType.ALL)
	private Set<EmployeeCertification> employeeCertifications = new HashSet<EmployeeCertification>(NumberUtils.INTEGER_ZERO);
	
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
	 * Get the name of the acquired certificate
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the acquired certificate
	 * @param name {@link String}
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Get a {@link Set} from {@link EmployeeCertification}
	 */
	public Set<EmployeeCertification> getEmployeeCertifications() {
		return employeeCertifications;
	}

	/**
	 * Set a collection from {@link EmployeeCertification}
	 * @param employeeCertifications a {@link Set} from {@link EmployeeCertification}
	 */
	public void setEmployeeCertifications(final Set<EmployeeCertification> employeeCertifications) {
		this.employeeCertifications = employeeCertifications;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Certification other = (Certification) obj;
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