package com.proxiad.extranet.core.model.skill;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.proxiad.extranet.core.model.employee.Employee;

/**
 * This entity represents a composite id for the relation {@link EmployeeSkill} 
 * 
 * @author Mihail Merkov
 */
@Embeddable
public class EmployeeSkillId implements Serializable {

	private static final long serialVersionUID = 8652906789849358991L;
	
	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private Skill skill;

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
	 * Get the {@link Skill} behind this relationship
	 * @return {@link Skill}
	 */
	public Skill getSkill() {
		return skill;
	}
	

	/**
	 * Set the {@link Skill} behind this relationship
	 * @param expertise {@link Skill} 
	 */
	public void setSkill(final Skill skill) {
		this.skill = skill;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((skill == null) ? 0 : skill.hashCode());
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
		EmployeeSkillId other = (EmployeeSkillId) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (skill == null) {
			if (other.skill != null)
				return false;
		} else if (!skill.equals(other.skill))
			return false;
		return true;
	}
}
