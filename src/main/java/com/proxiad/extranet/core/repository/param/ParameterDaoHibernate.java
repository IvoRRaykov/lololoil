package com.proxiad.extranet.core.repository.param;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.param.Parameter;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Parameter} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class ParameterDaoHibernate extends GenericHibernateDao<Parameter, Long> implements ParameterDao {

	/**
	 * @see com.proxiad.extranet.core.repository.param.ParameterDao#getByName(java.lang.String)
	 */
	@Override
	public Parameter getByName(final String name) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Parameter> criteria = builder.createQuery(Parameter.class);
		Root<Parameter> root = criteria.from(Parameter.class);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get("name"), name));
		
		return getEntityOrNull(criteria);
	}
}
