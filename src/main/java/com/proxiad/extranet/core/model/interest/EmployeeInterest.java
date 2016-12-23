package com.proxiad.extranet.core.model.interest;

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
 * EmployeeInterest entity keeps relation between an {@link Employee} and the date where it is added some {@link Interest}
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "EMPLOYEE_INTEREST")
@AssociationOverrides({
		@AssociationOverride(name = "pk.employee", joinColumns = @JoinColumn(name = "ID_EMPLOYEE")), 
		@AssociationOverride(name = "pk.interest", joinColumns = @JoinColumn(name = "ID_INTEREST")) })
public class EmployeeInterest implements Serializable {
	
	private static final long serialVersionUID = 4779466225493686459L;
	
	@EmbeddedId
	private EmployeeInterestId pk = new EmployeeInterestId();
	
	@Column(name = "DATE", nullable = false)
	private Date date;
	
	/**
	 * Get primary key for this relation 
	 * @return {@link EmployeeInterestId}
	 */
	public EmployeeInterestId getPk() {
		return pk;
	}
	
	/**
	 * Set primary key for this relation 
	 * @param pk {@link EmployeeInterestId}
	 */
	public void setPk(final EmployeeInterestId pk) {
		this.pk = pk;
	}
	
	/**
	 * Get the date where is added some {@link Interest}
	 * @return {@link Date}
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Set the date where is added some {@link Interest}
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
	 * Get the {@link Interest} behind this relationship
	 * @return {@link Interest}
	 */
	@Transient
	public Interest getInterest() {
		return this.pk.getInterest();
	}
	
	/**
	 * Set the {@link Interest} behind this relationship
	 * @param expertise {@link Interest} 
	 */
	public void setInterest(final Interest interest) {
		this.pk.setInterest(interest);
	}
}
