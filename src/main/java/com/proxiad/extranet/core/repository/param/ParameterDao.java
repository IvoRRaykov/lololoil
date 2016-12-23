package com.proxiad.extranet.core.repository.param;

import com.proxiad.extranet.core.model.param.Parameter;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Repository access interface for {@link Parameter} entity
 * 
 * @author Mihail Merkov
 */
public interface ParameterDao extends CrudDao<Parameter, Long> {
	
	/**
	 * Get a {@link Parameter} by its name
	 * @return {@link Parameter} or <code>null</code>
	 */
	Parameter getByName(final String name);
}
