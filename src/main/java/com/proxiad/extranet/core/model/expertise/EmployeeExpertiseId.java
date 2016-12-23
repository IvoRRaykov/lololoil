package com.proxiad.extranet.core.model.expertise;

import java.io.Serializable;

import javax.persistence.ManyToOne;

import com.proxiad.extranet.core.model.employee.Employee;

/**
 * This entity represents a composite id for the relation {@link EmployeeExpertise} 
 * 
 * @author Mihail Merkov
 */
public class EmployeeExpertiseId implements Serializable {

	private static final long serialVersionUID = -4910847279530120812L;

	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private Expertise expertise;

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
	 * Get the {@link Expertise} behind this relationship
	 * @return {@link Expertise}
	 */
	public Expertise getExpertise() {
		return expertise;
	}
	
	/**
	 * Set the {@link Expertise} behind this relationship
	 * @param expertise {@link Expertise} 
	 */
	public void setExpertise(final Expertise expertise) {
		this.expertise = expertise;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((expertise == null) ? 0 : expertise.hashCode());
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
		EmployeeExpertiseId other = (EmployeeExpertiseId) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (expertise == null) {
			if (other.expertise != null)
				return false;
		} else if (!expertise.equals(other.expertise))
			return false;
		return true;
	}
}
