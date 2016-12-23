package com.proxiad.extranet.core.model.feedback;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.proxiad.extranet.core.model.employee.Employee;

/**
 * Compentency entity is a part from one of the many competency in a {@link Feedback}, 
 * which is given for specific employee {@link Employee} from specific manager {@link Employee}
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "COMPETENCY")
public class Competency implements Serializable {
	
	private static final long serialVersionUID = -7086956860914872640L;

	@Id
	@Column(name = "ID_COMPETENCY")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_BEHAVIOUR_LABEL", nullable = false)
	private BehaviourLabel behaviourLabel;
	
	@Column(name = "SELF_APPRAISAL_RATING")
	private int selfAppraisalRating;
	
	@Column(name = "MANAGER_RATING")
	private int managerRating;
	
	@Column(name = "SELF_APPRAISAL_COMMENT")
	private String selfAppraisalComment;
	
	@Column(name = "MANAGER_COMMENT")
	private String managerComment;
	
	@Column(name = "ADDITIONAL_COMMENT")
	private String additionalComment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FEEDBACK", nullable = false)
	private Feedback feedback;
	
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
	 * Get the {@link BehaviourLabel} for this competency
	 * @return {@link BehaviourLabel}
	 */
	public BehaviourLabel getBehaviourLabel() {
		return behaviourLabel;
	}
	
	/**
	 * Set the {@link BehaviourLabel} for this competency
	 * @param behaviourLabel {@link BehaviourLabel}
	 */
	public void setBehaviourLabel(final BehaviourLabel behaviourLabel) {
		this.behaviourLabel = behaviourLabel;
	}
	
	/**
	 * Get the rating given by the {@link Employee} itself
	 * @return int
	 */
	public int getSelfAppraisalRating() {
		return selfAppraisalRating;
	}
	
	/**
	 * Set the rating given by the {@link Employee} itself
	 * @param selfAppraisalRating int
	 */
	public void setSelfAppraisalRating(int selfAppraisalRating) {
		this.selfAppraisalRating = selfAppraisalRating;
	}
	
	/**
	 * Get the rating given by the manager {@link Employee}
	 * @return int
	 */
	public int getManagerRating() {
		return managerRating;
	}
	
	/**
	 * Set the rating given by the manager {@link Employee}
	 * @param managerRating
	 */
	public void setManagerRating(int managerRating) {
		this.managerRating = managerRating;
	}
	
	/**
	 * Get the comment given by the {@link Employee} itself
	 * @return {@link String}
	 */
	public String getSelfAppraisalComment() {
		return selfAppraisalComment;
	}
	
	/**
	 * Set the rating given by the {@link Employee} itself
	 * @param selfAppraisalRating int
	 */
	public void setSelfAppraisalComment(final String selfAppraisalComment) {
		this.selfAppraisalComment = selfAppraisalComment;
	}
	
	/**
	 * Get the comment given by the manager {@link Employee}
	 * @return {@link String}
	 */
	public String getManagerComment() {
		return managerComment;
	}
	
	/**
	 * Set the comment given by the manager {@link Employee}
	 * @param managerComment {@link String}
	 */
	public void setManagerComment(final String managerComment) {
		this.managerComment = managerComment;
	}
	
	/**
	 * Get the additional comment
	 * @return {@link String}
	 */
	public String getAdditionalComment() {
		return additionalComment;
	}
	
	/**
	 * Set the additional comment
	 * @param additionalComment
	 */
	public void setAdditionalComment(final String additionalComment) {
		this.additionalComment = additionalComment;
	}
	
	/**
	 * Get the {@link Feedback} for which is this competence
	 * @return {@link Feedback}
	 */
	public Feedback getFeedback() {
		return feedback;
	}
	
	/**
	 * Set the {@link Feedback} for which is this competence
	 * @param feedback
	 */
	public void setFeedback(final Feedback feedback) {
		this.feedback = feedback;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalComment == null) ? 0 : additionalComment.hashCode());
		result = prime * result + ((behaviourLabel == null) ? 0 : behaviourLabel.hashCode());
		result = prime * result + ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((managerComment == null) ? 0 : managerComment.hashCode());
		result = prime * result + managerRating;
		result = prime * result + ((selfAppraisalComment == null) ? 0 : selfAppraisalComment.hashCode());
		result = prime * result + selfAppraisalRating;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Competency other = (Competency) obj;
		if (additionalComment == null) {
			if (other.additionalComment != null)
				return false;
		} else if (!additionalComment.equals(other.additionalComment))
			return false;
		if (behaviourLabel == null) {
			if (other.behaviourLabel != null)
				return false;
		} else if (!behaviourLabel.equals(other.behaviourLabel))
			return false;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (managerComment == null) {
			if (other.managerComment != null)
				return false;
		} else if (!managerComment.equals(other.managerComment))
			return false;
		if (managerRating != other.managerRating)
			return false;
		if (selfAppraisalComment == null) {
			if (other.selfAppraisalComment != null)
				return false;
		} else if (!selfAppraisalComment.equals(other.selfAppraisalComment))
			return false;
		if (selfAppraisalRating != other.selfAppraisalRating)
			return false;
		return true;
	}
}
