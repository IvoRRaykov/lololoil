package com.proxiad.extranet.core.model.certification;

import java.io.Serializable;

import javax.persistence.ManyToOne;

import com.proxiad.extranet.core.model.employee.Employee;

/**
 * This entity represents a composite id for the relation {@link EmployeeCertification} 
 * 
 * @author Mihail Merkov
 */
public class EmployeeCertificationId implements Serializable {

	private static final long serialVersionUID = -4910847279530120812L;

	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private Certification certification;

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
	 * Get the {@link Certification} behind this relationship
	 * @return {@link Certification}
	 */
	public Certification getCertification() {
		return certification;
	}
	
	/**
	 * Set the {@link Certification} behind this relationship
	 * @param expertise {@link Certification} 
	 */
	public void setCertification(final Certification certification) {
		this.certification = certification;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((certification == null) ? 0 : certification.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
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
		EmployeeCertificationId other = (EmployeeCertificationId) obj;
		if (certification == null) {
			if (other.certification != null)
				return false;
		} else if (!certification.equals(other.certification))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		return true;
	}
}
