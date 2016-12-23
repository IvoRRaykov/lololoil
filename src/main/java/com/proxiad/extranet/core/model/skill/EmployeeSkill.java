package com.proxiad.extranet.core.model.skill;

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
 * EmployeeSkill entity keeps relation between an {@link Employee} and the date where it is added some {@link Skill}
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "EMPLOYEE_SKILL")
@AssociationOverrides({
		@AssociationOverride(name = "pk.employee", joinColumns = @JoinColumn(name = "ID_EMPLOYEE")), 
		@AssociationOverride(name = "pk.skill", joinColumns = @JoinColumn(name = "ID_SKILL")) })
public class EmployeeSkill implements Serializable {
	
	private static final long serialVersionUID = -2146664026407349728L;

	@EmbeddedId
	private EmployeeSkillId pk = new EmployeeSkillId();
	
	@Column(name = "DATE", nullable = false)
	private Date date;
	

	/**
	 * Get primary key for this relation 
	 * @return {@link EmployeeSkillId}
	 */
	public EmployeeSkillId getPk() {
		return pk;
	}
	
	/**
	 * Set primary key for this relation 
	 * @param pk {@link EmployeeSkillId}
	 */
	public void setPk(final EmployeeSkillId pk) {
		this.pk = pk;
	}
	
	/**
	 * Get the date where is added some {@link Skill}
	 * @return {@link Date}
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Set the date where is added some {@link Skill}
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
	 * Get the {@link Skill} behind this relationship
	 * @return {@link Skill}
	 */
	@Transient
	public Skill getSkill() {
		return this.pk.getSkill();
	}
	
	/**
	 * Set the {@link Skill} behind this relationship
	 * @param expertise {@link Skill} 
	 */
	public void setSkill(final Skill skill) {
		this.pk.setSkill(skill);
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
		EmployeeSkill other = (EmployeeSkill) obj;
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
