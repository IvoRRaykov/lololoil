package com.proxiad.extranet.core.model.audit;

import java.io.Serializable;
import java.util.Date;

import com.proxiad.extranet.core.model.user.User;

/**
 * Common Audit Interface. Each entity, which requires to implement some audit logic,
 * should implement this interface. This way, all audit logic will follow common interface
 * 
 * @author Mihail Merkov
 */
public interface AuditableEntity<T extends Serializable> {
	/**
	 * Get the id of the entity
	 * @return {@link Long}
	 */
	Long getId();
	
	/**
	 * Set the id of the entity
	 * @param id {@link Long}
	 */
	void setId(final Long id);
	
	/**
	 * Get when the modification was made
	 * @return {@link Date}
	 */
	Date getModifiedOn();
	
	/**
	 * Set when the modification was made
	 * @param modifiedOn {@link Date}
	 */
	void setModifiedOn(final Date modifiedOn);
	
	/**
	 * Get who made the modification
	 * @return {@link User}
	 */
	User getModifiedBy();
	
	/**
	 * Set who made the modification
	 * @param modifiedBy {@link User}
	 */
	void setModifiedBy(final User modifiedBy);
	
	/**
	 * Get the name of the field over which is made the modification
	 * @return {@link String}
	 */
	String getNameOfField();
	
	/**
	 * Set the name of the field over which is made the modification
	 * @param nameOfField {@link String}
	 */
	void setNameOfField(final String nameOfField);
	
	
	/**
	 * Get the value of field before modification
	 * @return {@link String}
	 */
	String getOldValue();
	
	/**
	 * Set the value of field before modification
	 * @param oldValue {@link String}
	 */
	void setOldValue(String oldValue);
	
	/**
	 * Get the value of field after modification
	 * @return {@link String}
	 */
	String getNewValue();
	
	/**
	 * Set the value of field after modification
	 * @return {@link String}
	 */
	void setNewValue(final String newValue);
	
	/**
	 * Get the target entity for which is made this modification
	 */
	T getTarget();
	
	/**
	 * Set the target entity for which is made this modification
	 * @param target
	 */
	void setTarget(final T target);
}
