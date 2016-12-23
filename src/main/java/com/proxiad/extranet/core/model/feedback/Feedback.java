package com.proxiad.extranet.core.model.feedback;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.math.NumberUtils;

import com.proxiad.extranet.core.model.employee.Employee;

/**
 * Feedback entity will serves to keep information about the feedback of main manager {@link Employee} 
 * to speficic {@link Employee} for specific interval of time
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "FEEDBACK")
public class Feedback implements Serializable {
	
	private static final long serialVersionUID = 3058613524727832011L;

	@Id
	@Column(name = "ID_FEEDBACK")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MANAGER", nullable = false)
	private Employee manager;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TARGET_EMPLOYEE", nullable = false)
	private Employee targetEmployee;
	
	@Column(name = "GENERAL_IMPRESSIONS")
	private String generalImpressions;
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "END_DATE")
	private Date endDate;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
		name = "FEEDBACK_GOAL", 
		joinColumns = {
			@JoinColumn(name = "ID_FEEDBACK", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "ID_GOAL", nullable = false, updatable = false) 
			}
		)
	private Set<Goal> goals = new HashSet<Goal>(NumberUtils.INTEGER_ZERO);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "feedback")
	private Set<Competency> competencies = new HashSet<Competency>(NumberUtils.INTEGER_ZERO);

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
	 * Get the manager who gives this feedback
	 * @return {@link Employee}
	 */
	public Employee getManager() {
		return manager;
	}
	
	/**
	 * Set the manager who gives this feedback
	 * @param manager {@link Employee}
	 */
	public void setManager(final Employee manager) {
		this.manager = manager;
	}
	
	/**
	 * Get the target {@link Employee} for who is this feedback
	 * @return {@link Employee}
	 */
	public Employee getTargetEmployee() {
		return targetEmployee;
	}
	
	/**
	 * Set the target {@link Employee} for who is this feedback
	 * @param targetEmployee {@link Employee}
	 */
	public void setTargetEmployee(final Employee targetEmployee) {
		this.targetEmployee = targetEmployee;
	}
	
	/**
	 * Get the general impressions made for this feedback
	 * @return {@link String}
	 */
	public String getGeneralImpressions() {
		return generalImpressions;
	}
	
	/**
	 * Set the general impressions made for this feedback
	 * @param generalImpressions {@link String}
	 */
	public void setGeneralImpressions(final String generalImpressions) {
		this.generalImpressions = generalImpressions;
	}
	
	/**
	 * Get the start date of period for which is give this feedback
	 * @return {@link Date}
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * Set the start date of period for which is give this feedback
	 * @param startDate {@link Date}
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Get the end date of period for which is give this feedback
	 * @return {@link Date}
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Set the end date of period for which is give this feedback
	 * @param endDate {@link Date}
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Get a {@link Set} from {@link Goal}s which are assigned to the target {@link Employee}
	 * to the next {@link Feedback}
	 * @return {@link Set} from {@link Goal}
	 */
	public Set<Goal> getGoals() {
		return goals;
	}
	
	/**
	 * Set a {@link Set} from {@link Goal}s which are assigned to the target {@link Employee}
	 * to the next {@link Feedback}
	 * @param goals {@link Set} from {@link Goal}
	 */
	public void setGoals(final Set<Goal> goals) {
		this.goals = goals;
	}
	
	/**
	 * Get a {@link Set} from {@link Competency}
	 * @return {@link Set} from {@link Competency}
	 */
	public Set<Competency> getCompetencies() {
		return competencies;
	}
	
	/**
	 * Set a {@link Set} from {@link Competency}
	 * @param competencies {@link Set} from {@link Competency}
	 */
	public void setCompetencies(final Set<Competency> competencies) {
		this.competencies = competencies;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((competencies == null) ? 0 : competencies.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((generalImpressions == null) ? 0 : generalImpressions.hashCode());
		result = prime * result + ((goals == null) ? 0 : goals.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((targetEmployee == null) ? 0 : targetEmployee.hashCode());
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
		Feedback other = (Feedback) obj;
		if (competencies == null) {
			if (other.competencies != null)
				return false;
		} else if (!competencies.equals(other.competencies))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (generalImpressions == null) {
			if (other.generalImpressions != null)
				return false;
		} else if (!generalImpressions.equals(other.generalImpressions))
			return false;
		if (goals == null) {
			if (other.goals != null)
				return false;
		} else if (!goals.equals(other.goals))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (targetEmployee == null) {
			if (other.targetEmployee != null)
				return false;
		} else if (!targetEmployee.equals(other.targetEmployee))
			return false;
		return true;
	}
}
