package com.proxiad.extranet.core.model.project;

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
 * EmployeeProject entity keeps relation between an {@link Employee} the {@link Project} where it is worked
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "EMPLOYEE_PROJECT")
@AssociationOverrides({
		@AssociationOverride(name = "pk.employee", joinColumns = @JoinColumn(name = "ID_EMPLOYEE")), 
		@AssociationOverride(name = "pk.project", joinColumns = @JoinColumn(name = "ID_PROJECT")) })
public class EmployeeProject implements Serializable {
	
	private static final long serialVersionUID = 4826782130396643983L;
	
	@EmbeddedId
	private EmployeeProjectId pk = new EmployeeProjectId();
	
	@Column(name = "START_ON")
	private Date startOn;
	
	@Column(name = "END_ON")
	private Date endOn;
	
	@Column(name = "CURRENT_PROJECT", nullable = false)
	private boolean currentProject;
	
	@Column(name = "RESPONSIBILITIES")
	private String responsibilities;
	
	/**
	 * Get primary key for this relation 
	 * @return {@link EmployeeProjectId}
	 */
	public EmployeeProjectId getPk() {
		return pk;
	}
	
	/**
	 * Set primary key for this relation
	 * @param pk {@link EmployeeProjectId}
	 */
	public void setPk(final EmployeeProjectId pk) {
		this.pk = pk;
	}
	
	/**
	 * Get the date starts on this project
	 * @return {@link Date}
	 */
	public Date getStartOn() {
		return startOn;
	}
	
	/**
	 * Set the date starts on this project
	 * @param startOn
	 */
	public void setStartOn(final Date startOn) {
		this.startOn = startOn;
	}
	
	/**
	 * Get the date of ending of the project
	 * @return {@link Date}
	 */
	public Date getEndOn() {
		return endOn;
	}
	
	/**
	 * Set the date of ending of the project
	 * @return {@link Date}
	 */
	public void setEndOn(final Date endOn) {
		this.endOn = endOn;
	}
	
	/**
	 * Get the flag, which indicate whether this is the current project on the {@link Employee}
	 * @return boolean
	 */
	public boolean isCurrentProject() {
		return currentProject;
	}
	
	/**
	 * Set whether this is the current project on the {@link Employee}
	 * @param currentProject
	 */
	public void setCurrentProject(boolean currentProject) {
		this.currentProject = currentProject;
	}
	
	/**
	 * Get a short comment what are the responsibilities of the user for this project
	 * @return {@link String}
	 */
	public String getResponsibilities() {
		return responsibilities;
	}
	
	/**
	 * Set a short comment what are the responsibilities of the user for this project
	 * @param responsibilities
	 */
	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
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
	 * Get the {@link Project} behind this relationship
	 * @return {@link Project}
	 */
	@Transient
	public Project getProject() {
		return this.pk.getProject();
	}
	
	/**
	 * Set the {@link  Project} behind this relationship
	 * @param employee {@link  Project}
	 */
	public void setProject(final Project project) {
		this.pk.setProject(project);
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (currentProject ? 1231 : 1237);
		result = prime * result + ((endOn == null) ? 0 : endOn.hashCode());
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((responsibilities == null) ? 0 : responsibilities.hashCode());
		result = prime * result + ((startOn == null) ? 0 : startOn.hashCode());
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
		EmployeeProject other = (EmployeeProject) obj;
		if (currentProject != other.currentProject)
			return false;
		if (endOn == null) {
			if (other.endOn != null)
				return false;
		} else if (!endOn.equals(other.endOn))
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (responsibilities == null) {
			if (other.responsibilities != null)
				return false;
		} else if (!responsibilities.equals(other.responsibilities))
			return false;
		if (startOn == null) {
			if (other.startOn != null)
				return false;
		} else if (!startOn.equals(other.startOn))
			return false;
		return true;
	}	
}