package com.proxiad.extranet.core.model.language;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxiad.extranet.core.model.employee.Employee;

/**
 * This entity represents a composite id for the relation {@link EmployeeLanguage} 
 * 
 * @author Mihail Merkov
 */
@JsonIgnoreProperties({"employee"})
@Embeddable
public class EmployeeLanguageId implements Serializable {
	
	private static final long serialVersionUID = -7653828509811435482L;
	
	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private Language language;

	/**
	 * Get the {@link Employee} behind this relationship
	 * @return {@link Employee}
	 */
	public Employee getEmployee() {
		return employee;
	}
	
	/**
	 * Set the {@link Employee} behind this relationship
	 * @param employee {@link Employee}
	 */
	public void setEmployee(final Employee employee) {
		this.employee = employee;
	}
	
	/**
	 * Get the {@link Language} behind this relationship
	 * @return {@link Language}
	 */
	public Language getLanguage() {
		return language;
	}
	
	/**
	 * Set the {@link Language} behind this relationship
	 * @param expertise {@link Language} 
	 */
	public void setLanguage(final Language language) {
		this.language = language;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
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
		EmployeeLanguageId other = (EmployeeLanguageId) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		return true;
	}
}
