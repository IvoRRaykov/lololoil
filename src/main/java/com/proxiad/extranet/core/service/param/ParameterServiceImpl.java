package com.proxiad.extranet.core.service.param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxiad.extranet.core.model.param.ParamName;
import com.proxiad.extranet.core.model.param.Parameter;
import com.proxiad.extranet.core.repository.param.ParameterDao;

/**
 * Parameter services access the {@link Parameter} entity
 * 
 * @author Mihail Merkov
 */
@Service
public class ParameterServiceImpl implements ParameterService {

	/*-------------------------------------------------- REPOSITORIES ------------------------------------------------*/
	@Autowired
	private ParameterDao parameterDao;

	/*------------------------------------------------------ API -----------------------------------------------------*/
	/**
	 * @see com.proxiad.extranet.core.service.param.ParameterService#getParamAsString(com.proxiad.extranet.core.model.param.ParamName)
	 */
	@Override
	public String getParamAsString(final ParamName paramName) {
		Parameter parameter = parameterDao.getByName(paramName.getValue());
		return parameter != null ? parameter.getValue() : null;
	}
}
