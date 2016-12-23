package com.proxiad.extranet.core.model.employee;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.proxiad.extranet.core.model.audit.AuditableEntity;
import com.proxiad.extranet.core.model.user.User;

/**
 * Employee history entity keeps information about the modification of specific field from an {@link Employee}
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "EMPLOYEE_HISTORY")
@Inheritance(strategy = InheritanceType.JOINED)
public class EmployeeHistory implements AuditableEntity<Employee> {
	
	@Id
	@Column(name = "ID_EMPLOYEE_HISTORY")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "MODIFIED_ON")
	private Date modifiedOn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFIED_BY", nullable = false)
	private User modifiedBy;
	
	@Column(name = "NAME_OF_FIELD")
	private String nameOfField;
	
	@Column(name = "OLD_VALUE")
	private String oldValue;
	
	@Column(name = "NEW_VALUE")
	private String newValue;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TARGET_EMPLOYEE", nullable = false)
	private Employee target;
	
	/**
	 * @see com.proxiad.extranet.core.model.audit.AuditableEntity#getId()
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @see com.proxiad.extranet.core.model.audit.AuditableEntity#setId(java.lang.Long)
	 */
	public void setId(final Long id) {
		this.id = id;
	}
	
	/**
	 * @see com.proxiad.extranet.core.model.audit.AuditableEntity#getModifiedOn()
	 */
	public Date getModifiedOn() {
		return modifiedOn;
	}
	
	/**
	 * @see com.proxiad.extranet.core.model.audit.AuditableEntity#setModifiedOn(java.util.Date)
	 */
	public void setModifiedOn(final Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	/**
	 * @see com.proxiad.extranet.core.model.audit.AuditableEntity#getModifiedBy()
	 */
	public User getModifiedBy() {
		return modifiedBy;
	}
	
	/**
	 * @see com.proxiad.extranet.core.model.audit.AuditableEntity#setModifiedBy(com.proxiad.extranet.core.model.user.User)
	 */
	public void setModifiedBy(final User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	/**
	 * @see com.proxiad.extranet.core.model.audit.AuditableEntity#getNameOfField()
	 */
	public String getNameOfField() {
		return nameOfField;
	}
	
	/**
	 * @see com.proxiad.extranet.core.model.audit.AuditableEntity#setNameOfField(java.lang.String)
	 */
	public void setNameOfField(final String nameOfField) {
		this.nameOfField = nameOfField;
	}
	
	/**
	 * @see com.proxiad.extranet.core.model.audit.AuditableEntity#getOldValue()
	 */
	public String getOldValue() {
		return oldValue;
	}
	
	/**
	 * @see com.proxiad.extranet.core.model.audit.AuditableEntity#setOldValue(java.lang.String)
	 */
	public void setOldValue(final String oldValue) {
		this.oldValue = oldValue;
	}
	
	/**
	 * @see com.proxiad.extranet.core.model.audit.AuditableEntity#getNewValue()
	 */
	public String getNewValue() {
		return newValue;
	}
	
	/**
	 * @see com.proxiad.extranet.core.model.audit.AuditableEntity#setNewValue(java.lang.String)
	 */
	public void setNewValue(final String newValue) {
		this.newValue = newValue;
	}
	
	/**
	 * @see com.proxiad.extranet.core.model.audit.AuditableEntity#getTarget()
	 */
	public Employee getTarget() {
		return target;
	}
	
	/**
	 * @see com.proxiad.extranet.core.model.audit.AuditableEntity#setTarget(java.io.Serializable)
	 */
	public void setTarget(final Employee target) {
		this.target = target;
	}	
}
