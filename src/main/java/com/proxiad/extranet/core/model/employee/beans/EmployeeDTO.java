package com.proxiad.extranet.core.model.employee.beans;

import java.util.Date;

import com.proxiad.extranet.core.model.user.UserDetails;
import com.proxiad.extranet.core.model.user.beans.UserDTO;
import com.proxiad.extranet.core.model.workplace.Workplace;

/**
 * Data transfer object for {@link EmployeeDTO} entity
 * 
 * @author Mihail Merkov
 */
public class EmployeeDTO extends UserDTO {
	private Date dateOfJoining;
	private Date dateOfLeaving;
	private Workplace workplace;
	private String position;
	
	public EmployeeDTO() {};
	
	public EmployeeDTO(final Long id, final String firstName, final String lastName,
			final String pathToProfilePicture, final char sex, final boolean active,
			final Date createdOn, final Long createdById, final UserDetails details,
			final Date dateOfJoining, final Date dateOfLeaving,	final Workplace workplace,
			final String position) {
		
		super(id, firstName, lastName, pathToProfilePicture, sex, active, createdOn, createdById, details);
		this.dateOfJoining = dateOfJoining;
		this.dateOfLeaving = dateOfLeaving;
		this.workplace = workplace;
		this.position = position;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Date getDateOfLeaving() {
		return dateOfLeaving;
	}

	public void setDateOfLeaving(Date dateOfLeaving) {
		this.dateOfLeaving = dateOfLeaving;
	}

	public Workplace getWorkplace() {
		return workplace;
	}

	public void setWorkplace(Workplace workplace) {
		this.workplace = workplace;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
