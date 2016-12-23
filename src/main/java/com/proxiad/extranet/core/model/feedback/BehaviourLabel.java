package com.proxiad.extranet.core.model.feedback;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BehaviourLabel entity will keeps different labels needed for a {@link Competency}
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "BEHAVIOUR_LABEL")
public class BehaviourLabel implements Serializable {
	
	private static final long serialVersionUID = -5018134151524177506L;

	@Id
	@Column(name = "ID_BEHAVIOUR_LABEL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "LABEL")
	private String label;
	
	@Column(name = "DEFAULT_LABEL", nullable = false)
	private boolean defaultLabel;
	
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
	 * Get the label
	 * @return {@link String}
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Set the label
	 * @param label {@link String}
	 */
	public void setLabel(final String label) {
		this.label = label;
	}
	
	/**
	 * Flag which indicate, whether this label is default one or not
	 * @return boolean
	 */
	public boolean isDefaultLabel() {
		return defaultLabel;
	}
	
	/**
	 * Set whether this label is default one or not
	 * @param defaultLabel boolean
	 */
	public void setDefaultLabel(boolean defaultLabel) {
		this.defaultLabel = defaultLabel;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (defaultLabel ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		BehaviourLabel other = (BehaviourLabel) obj;
		if (defaultLabel != other.defaultLabel)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}
}
