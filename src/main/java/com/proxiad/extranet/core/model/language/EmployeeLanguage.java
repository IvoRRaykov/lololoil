package com.proxiad.extranet.core.model.language;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.expertise.Expertise;

/**
 * EmployeeLanguage entity keeps relation between an {@link Employee} and the level which it has to some {@link Language}
 * 
 * @author Mihail Merkov
 */
@JsonIgnoreProperties({"employee", "language", "pk.employee"})
@Entity
@Table(name = "EMPLOYEE_LANGUAGE")
@AssociationOverrides({
		@AssociationOverride(name = "pk.employee", joinColumns = @JoinColumn(name = "ID_EMPLOYEE")), 
		@AssociationOverride(name = "pk.language", joinColumns = @JoinColumn(name = "ID_LANGUAGE")) })
public class EmployeeLanguage implements Serializable {
	
	private static final long serialVersionUID = -26320769540044400L;

	@EmbeddedId
	private EmployeeLanguageId pk = new EmployeeLanguageId(); 
	
	@Column(name = "LEVEL", nullable = false)
	private String level;
	
	/**
	 * Get primary key for this relation 
	 * @return {@link EmployeeLanguageId}
	 */
	public EmployeeLanguageId getPk() {
		return pk;
	}
	
	/**
	 * Set primary key for this relation 
	 * @param pk {@link EmployeeLanguageId}
	 */
	public void setPk(final EmployeeLanguageId pk) {
		this.pk = pk;
	}
	
	/**
	 * Get the level
	 * @return {@link String}
	 */
	public String getLevel() {
		return level;
	}
	
	/**
	 * Set the level
	 * @param level {@link String}
	 */
	public void setLevel(final String level) {
		this.level = level;
	}
	
	/**
	 * Get the {@link Employee} behind this relationship
	 * @return {@link Employee}
	 */
	@Transient
	public Employee getEmployee() {
		return this.pk.getEmployee();
	}
	
	/**
	 * Set the {@link Expertise} behind this relationship
	 * @param expertise {@link Expertise} 
	 */
	public void setEmployee(Employee employee) {
		this.pk.setEmployee(employee);
	}
	
	/**
	 * Get the {@link Language} behind this relationship
	 * @return {@link Language}
	 */
	@Transient
	public Language getLanguage() {
		return this.pk.getLanguage();
	}
	
	/**
	 * Get the {@link Language} behind this relationship
	 * @param expertise {@link Language} 
	 */
	public void setLanguage(Language language) {
		this.pk.setLanguage(language);
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		EmployeeLanguage other = (EmployeeLanguage) obj;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}
}
