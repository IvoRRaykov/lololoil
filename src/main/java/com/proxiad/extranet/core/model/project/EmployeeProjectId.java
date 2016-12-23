package com.proxiad.extranet.core.model.project;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.proxiad.extranet.core.model.employee.Employee;

/**
 * This entity represents a composite id for the relation {@link EmployeeProject} 
 * 
 * @author Mihail Merkov
 */
@Embeddable
public class EmployeeProjectId implements Serializable {
	
	private static final long serialVersionUID = -421633732512171363L;

	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private Project project;

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
	 * Get the {@link Project} behind this relationship
	 * @return {@link Project}
	 */
	public Project getProject() {
		return project;
	}
	
	/**
	 * Set the {@link Project} behind this relationship
	 * @param employee {@link Project}
	 */
	public void setProject(final Project project) {
		this.project = project;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
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
		EmployeeProjectId other = (EmployeeProjectId) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		return true;
	}
}
