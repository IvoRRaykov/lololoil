package com.proxiad.extranet.core.service.param;

import com.proxiad.extranet.core.model.param.ParamName;
import com.proxiad.extranet.core.model.param.Parameter;

/**
 * Parameter services access the {@link Parameter} entity
 * 
 * @author Mihail Merkov
 */
public interface ParameterService {
	
	/**
	 * Get specific parameter as {@link String}
	 * @param paramName {@link ParamName}
	 * @return {@link String}
	 */
	String getParamAsString(final ParamName paramName);
}
