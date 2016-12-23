package com.proxiad.extranet.core.model.certification;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.proxiad.extranet.core.model.employee.Employee;

/**
 * EmployeeCertification entity keeps relation between an {@link Employee} and the date where it is added some {@link Certification}
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "EMPLOYEE_CERTIFICATION")
@AssociationOverrides({
		@AssociationOverride(name = "pk.employee", joinColumns = @JoinColumn(name = "ID_EMPLOYEE")), 
		@AssociationOverride(name = "pk.certification", joinColumns = @JoinColumn(name = "ID_CERTIFICATION")) })
public class EmployeeCertification implements Serializable {

	private static final long serialVersionUID = 1922202742754462320L;

	@EmbeddedId
	private EmployeeCertificationId pk = new EmployeeCertificationId();
	
	@Column(name = "DATE", nullable = false)
	private Date date;
	
	/**
	 * Get primary key for this relation 
	 * @return {@link EmployeeCertificationId}
	 */
	public EmployeeCertificationId getPk() {
		return pk;
	}
	
	/**
	 * Set primary key for this relation 
	 * @param pk {@link EmployeeCertificationId}
	 */
	public void setPk(final EmployeeCertificationId pk) {
		this.pk = pk;
	}
	
	/**
	 * Get the date where is added some {@link Certification}
	 * @return {@link Date}
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Set the date where is added some {@link Certification}
	 * @param date {@link Date}
	 */
	public void setDate(final Date date) {
		this.date = date;
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
	 * Set the {@link Employee} behind this relationship
	 * @param employee {@link Employee}
	 */
	public void setEmployee(final Employee employee) {
		this.pk.setEmployee(employee);
	}
	
	/**
	 * Get the {@link Certification} behind this relationship
	 * @return {@link Certification}
	 */
	@Transient
	public Certification getCertification() {
		return this.pk.getCertification();
	}
	
	/**
	 * Set the {@link Certification} behind this relationship
	 * @param certification {@link Certification} 
	 */
	public void setCertification(final Certification certification) {
		this.pk.setCertification(certification);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		EmployeeCertification other = (EmployeeCertification) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}
}
