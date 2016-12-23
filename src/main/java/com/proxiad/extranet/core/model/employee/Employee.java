package com.proxiad.extranet.core.model.employee;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang3.math.NumberUtils;

import com.proxiad.extranet.core.model.certification.EmployeeCertification;
import com.proxiad.extranet.core.model.education.Education;
import com.proxiad.extranet.core.model.expertise.EmployeeExpertise;
import com.proxiad.extranet.core.model.interest.EmployeeInterest;
import com.proxiad.extranet.core.model.language.EmployeeLanguage;
import com.proxiad.extranet.core.model.project.EmployeeProject;
import com.proxiad.extranet.core.model.skill.EmployeeSkill;
import com.proxiad.extranet.core.model.user.User;
import com.proxiad.extranet.core.model.workplace.Workplace;

/**
 * Employee entity is the primary entity where all logic is related to it.
 * An employee is the {@link User}, which works to the company and it will keep
 * information about it, about its skills, about the projects it worked an many other
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "EMPLOYEE")
@PrimaryKeyJoinColumn(name = "ID_USER")
public class Employee extends User implements Serializable {
	private static final long serialVersionUID = -656559649735486454L;
	
	@Column(name="DATE_OF_JOINING")
	private Date dateOfJoining;
	
	@Column(name="DATE_OF_LEAVING")
	private Date dateOfLeaving;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_WORKPLACE", nullable = false)
	private Workplace workplace;
	
	@Column(name="POSITION")
	private String position;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.employee", cascade = CascadeType.ALL)
	private Set<EmployeeProject> employeeProjects = new HashSet<EmployeeProject>(NumberUtils.INTEGER_ZERO);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.employee", cascade = CascadeType.ALL)
	private Set<EmployeeSkill> employeeSkills = new HashSet<EmployeeSkill>(NumberUtils.INTEGER_ZERO);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.employee", cascade = CascadeType.ALL)
	private Set<EmployeeInterest> employeeInterests = new HashSet<EmployeeInterest>(NumberUtils.INTEGER_ZERO);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.employee", cascade = CascadeType.ALL)
	private Set<EmployeeExpertise> employeeExpertises = new HashSet<EmployeeExpertise>(NumberUtils.INTEGER_ZERO);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.employee", cascade = CascadeType.ALL)
	private Set<EmployeeLanguage> employeeLanguages = new HashSet<EmployeeLanguage>(NumberUtils.INTEGER_ZERO);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private Set<Education> educations = new HashSet<Education>(NumberUtils.INTEGER_ZERO);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.employee", cascade = CascadeType.ALL)
	private Set<EmployeeCertification> employeeCertifications = new HashSet<EmployeeCertification>(NumberUtils.INTEGER_ZERO);

	/**
	 * Get date of joining of the company
	 * @return {@link Date}
	 */
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	
	/**
	 * Set date of joining of the company
	 * @param dateOfJoining {@link Date}
	 */
	public void setDateOfJoining(final Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	
	/**
	 * Get date of leaving of the company
	 * @return {@link Date}
	 */
	public Date getDateOfLeaving() {
		return dateOfLeaving;
	}
	
	/**
	 * Set date of leaving of the company
	 * @param dateOfLeaving {@link Date}
	 */
	public void setDateOfLeaving(final Date dateOfLeaving) {
		this.dateOfLeaving = dateOfLeaving;
	}
	
	/**
	 * Get the {@link Workplace} of the employee
	 * @return {@link Workplace}
	 */
	public Workplace getWorkplace() {
		return workplace;
	}
	
	/**
	 * Set the {@link Workplace} of the employee
	 * @param workplace {@link Workplace}
	 */
	public void setWorkplace(final Workplace workplace) {
		this.workplace = workplace;
	}
	
	/**
	 * Get the current position of the employee
	 * @return {@link String}
	 */
	public String getPosition() {
		return position;
	}
	
	/**
	 * Set the current position of the employee
	 * @param position
	 */
	public void setPosition(final String position) {
		this.position = position;
	}
	
	/**
	 * Get the {@link Set} from {@link EmployeeProject} projects where the employee has worked
	 * @return {@link Set} from {@link EmployeeProject}
	 */ 
	public Set<EmployeeProject> getEmployeeProjects() {
		return employeeProjects;
	}
	
	/**
	 * Set the {@link Set} from {@link EmployeeProject} projects where the employee has worked
	 * @param employeeProjects {@link Set} from {@link EmployeeProject}
	 */ 
	public void setEmployeeProjects(final Set<EmployeeProject> employeeProjects) {
		this.employeeProjects = employeeProjects;
	}	
	
	/**
	 * Get the {@link Set} from {@link EmployeeSkill} skills which the employee owns
	 * @return {@link Set} from {@link EmployeeSkill}
	 */
	public Set<EmployeeSkill> getEmployeeSkills() {
		return employeeSkills;
	}
	
	/**
	 * Set the {@link Set} from {@link EmployeeSkill} skills which the employee owns
	 * @param employeeSkills {@link Set} from {@link EmployeeSkill}
	 */
	public void setEmployeeSkills(final Set<EmployeeSkill> employeeSkills) {
		this.employeeSkills = employeeSkills;
	}
	
	/**
	 * Get the {@link Set} from {@link EmployeeInterest} interests of given employee
	 * @return {@link Set} from {@link EmployeeInterest}
	 */
	public Set<EmployeeInterest> getEmployeeInterests() {
		return employeeInterests;
	}
	
	/**
	 * Set the {@link Set} from {@link EmployeeInterest} interests of given employee
	 * @param employeeInterests {@link Set} from {@link EmployeeInterest}
	 */
	public void setEmployeeInterests(final Set<EmployeeInterest> employeeInterests) {
		this.employeeInterests = employeeInterests;
	}
	
	/**
	 * Get the {@link Set} from {@link EmployeeExpertise} expertise of given employee
	 * @return {@link Set} from {@link EmployeeExpertise}
	 */
	public Set<EmployeeExpertise> getEmployeeExpertises() {
		return employeeExpertises;
	}
	
	/**
	 * Set the {@link Set} from {@link EmployeeExpertise} expertise of given employee
	 * @param employeeExpertises {@link Set} from {@link EmployeeExpertise}
	 */
	public void setEmployeeExpertises(final Set<EmployeeExpertise> employeeExpertises) {
		this.employeeExpertises = employeeExpertises;
	}
	
	/**
	 * Get the {@link Set} from {@link EmployeeLanguage} languages which employee speaks
	 * @return {@link Set} from {@link EmployeeLanguage}
	 */
	public Set<EmployeeLanguage> getEmployeeLanguages() {
		return employeeLanguages;
	}
	
	/**
	 * Set the {@link Set} from {@link EmployeeLanguage} languages which employee speaks
	 * @param employeeLanguages {@link Set} from {@link EmployeeLanguage}
	 */
	public void setEmployeeLanguages(final Set<EmployeeLanguage> employeeLanguages) {
		this.employeeLanguages = employeeLanguages;
	}
	
	/**
	 * Get the {@link Set} from {@link Education} educations for the employee
	 * @return {@link Set} from {@link Education} 
	 */
	public Set<Education> getEducations() {
		return educations;
	}
	
	/**
	 * Set the {@link Set} from {@link Education} educations for the employee
	 * @param educations {@link Set} from {@link Education}
	 */
	public void setEducations(final Set<Education> educations) {
		this.educations = educations;
	}
	
	/**
	 * Get the {@link Set} from {@link EmployeeCertification} languages which employee speaks
	 * @return {@link Set} from {@link EmployeeCertification}
	 */
	public Set<EmployeeCertification> getEmployeeCertifications() {
		return employeeCertifications;
	}

	/**
	 * Set the {@link Set} from {@link EmployeeCertification} languages which employee speaks
	 * @param employeeLanguages {@link Set} from {@link EmployeeCertification}
	 */
	public void setEmployeeCertifications(final Set<EmployeeCertification> employeeCertifications) {
		this.employeeCertifications = employeeCertifications;
	}

	/**
	 * @see com.proxiad.extranet.core.model.user.User#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dateOfJoining == null) ? 0 : dateOfJoining.hashCode());
		result = prime * result + ((dateOfLeaving == null) ? 0 : dateOfLeaving.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	/**
	 * @see com.proxiad.extranet.core.model.user.User#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (dateOfJoining == null) {
			if (other.dateOfJoining != null)
				return false;
		} else if (!dateOfJoining.equals(other.dateOfJoining))
			return false;
		if (dateOfLeaving == null) {
			if (other.dateOfLeaving != null)
				return false;
		} else if (!dateOfLeaving.equals(other.dateOfLeaving))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}
}
