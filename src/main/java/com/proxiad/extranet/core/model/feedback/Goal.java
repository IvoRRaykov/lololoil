package com.proxiad.extranet.core.model.feedback;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.proxiad.extranet.core.model.employee.Employee;

/**
 * Goal entity will keeps information about specific goals assigned to given {@link Employee}
 * for future period of time. They are used primary due giving of some {@link Feedback} to specific {@link Employee}
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "GOAL")
public class Goal implements Serializable {

	private static final long serialVersionUID = 7881475731740756214L;

	@Id
	@Column(name = "ID_GOAL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TITLE")
	private String title;
	
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
	 * Get the title
	 * @return {@link String}
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Set the title
	 * @return {@link String}
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Goal other = (Goal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
