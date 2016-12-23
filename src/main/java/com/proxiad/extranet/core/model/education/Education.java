package com.proxiad.extranet.core.model.education;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxiad.extranet.core.model.employee.Employee;

/**
 * Education entity will keep information about the education of specific {@link Employee}
 * 
 * @author Mihail Merkov
 */
@JsonIgnoreProperties({"employee"})
@Entity
@Table(name = "EDUCATION")
public class Education implements Serializable {

	private static final long serialVersionUID = 8722670676633195671L;

	@Id
	@Column(name = "ID_EDUCATION")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DEGREE")
	private String degree;
	
	@Column(name = "FIELD_OF_STUDY")
	private String fieldOfStudy;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "END_DATE")
	private Date endDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SCHOOL", nullable = false)
	private School school;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EMPLOYEE", nullable = false)
	private Employee employee;
	
	/**
	 * Get the id of the entity
	 * @return {@link Long}
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Set the id of the entity
	 * @param id {@link Long}
	 */
	public void setId(final Long id) {
		this.id = id;
	}
	
	/**
	 * Get the degree
	 * @return {@link String}
	 */
	public String getDegree() {
		return degree;
	}
	
	/**
	 * Set the degree
	 * @param degree {@link String}
	 */
	public void setDegree(final String degree) {
		this.degree = degree;
	}
	
	/**
	 * Get field of the study
	 * @return {@link String}
	 */
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}
	
	/**
	 * Set field of study
	 * @param fieldOfStudy
	 */
	public void setFieldOfStudy(final String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	
	/**
	 * Get description of the education
	 * @return {link String}
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the description for the education
	 * @param description {@link String}
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	
	/**
	 * Get starting date of the education
	 * @return {@link Date}
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * Set starting date of the education
	 * @param startDate {@link Date}
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Get ending date of the education
	 * @return {@link Date}
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Set ending date of the education
	 * @param endDate {@link Date}
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Get the {@link School} for this education
	 * @return {@link School}
	 */
	public School getSchool() {
		return school;
	}
	
	/**
	 * Set the {@link School} for this education
	 * @param school
	 */
	public void setSchool(final School school) {
		this.school = school;
	}
	
	/**
	 * Get the {@link Employee} which is related to this education
	 * @return {@link Employee}
	 */
	public Employee getEmployee() {
		return employee;
	}
	
	/**
	 * Get the {@link Employee} which is related to this education
	 * @param employee {@link Employee}
	 */
	public void setEmployee(final Employee employee) {
		this.employee = employee;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((degree == null) ? 0 : degree.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((fieldOfStudy == null) ? 0 : fieldOfStudy.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((school == null) ? 0 : school.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		Education other = (Education) obj;
		if (degree == null) {
			if (other.degree != null)
				return false;
		} else if (!degree.equals(other.degree))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (fieldOfStudy == null) {
			if (other.fieldOfStudy != null)
				return false;
		} else if (!fieldOfStudy.equals(other.fieldOfStudy))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (school == null) {
			if (other.school != null)
				return false;
		} else if (!school.equals(other.school))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
}
