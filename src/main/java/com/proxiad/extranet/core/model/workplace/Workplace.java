package com.proxiad.extranet.core.model.workplace;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxiad.extranet.core.model.employee.Employee;

/**
 * Workplace entity represents the workplace for each {@link Employee}
 * 
 * @author Mihail Merkov
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "WORKPLACE")
public class Workplace implements Serializable {

	private static final long serialVersionUID = 2215844910212592899L;

	@Id
	@Column(name = "ID_WORKPLACE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "FLOOR")
	private String floor;
	
	@Column(name = "ROOM")
	private String room;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_OFFICE", nullable = false)
	private Office office;
	
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
	 * Get the floor
	 * @return {@link String}
	 */
	public String getFloor() {
		return floor;
	}
	
	/**
	 * Set the floor
	 * @param floor {@link String}
	 */
	public void setFloor(final String floor) {
		this.floor = floor;
	}
	
	/**
	 * Get the room
	 * @return {@link String}
	 */
	public String getRoom() {
		return room;
	}
	
	/**
	 * Set the room
	 * @param room {@link String}
	 */
	public void setRoom(final String room) {
		this.room = room;
	}
	
	/**
	 * Get the {@link Office} where is deployed this workplace
	 * @return {@link Office}
	 */
	public Office getOffice() {
		return office;
	}
	
	/**
	 * Set the {@link Office} where is deployed this workplace
	 * @param office {@link Office}
	 */
	public void setOffice(final Office office) {
		this.office = office;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((floor == null) ? 0 : floor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((office == null) ? 0 : office.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
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
		Workplace other = (Workplace) obj;
		if (floor == null) {
			if (other.floor != null)
				return false;
		} else if (!floor.equals(other.floor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (office == null) {
			if (other.office != null)
				return false;
		} else if (!office.equals(other.office))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}
}
