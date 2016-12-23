package com.proxiad.extranet.core.model.interest;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.proxiad.extranet.core.model.employee.Employee;

/**
 * This entity represents a composite id for the relation {@link EmployeeInterest} 
 * 
 * @author Mihail Merkov
 */
@Embeddable
public class EmployeeInterestId implements Serializable {

	private static final long serialVersionUID = -5881631867455342037L;
	
	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private Interest interest;

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
	 * Get the {@link Interest} behind this relationship
	 * @return {@link Interest}
	 */
	public Interest getInterest() {
		return interest;
	}
	
	/**
	 * Set the {@link Interest} behind this relationship
	 * @param expertise {@link Interest} 
	 */
	public void setInterest(final Interest interest) {
		this.interest = interest;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((interest == null) ? 0 : interest.hashCode());
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
		EmployeeInterestId other = (EmployeeInterestId) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (interest == null) {
			if (other.interest != null)
				return false;
		} else if (!interest.equals(other.interest))
			return false;
		return true;
	}	
}
