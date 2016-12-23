package com.proxiad.extranet.core.repository.user;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.security.Credential;
import com.proxiad.extranet.core.model.user.User;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link User} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class UserDaoHibernate extends GenericHibernateDao<User, Long> implements UserDao {
	
	/**
	 * @see com.proxiad.extranet.core.repository.user.UserDao#getByLogin(java.lang.String)
	 */
	@Override
	public User getByLogin(final String login) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> user = criteria.from(User.class);
		Join<User, Credential> credential = user.join("credential");
		Path<String> loginPath = credential.get("login");
		
		criteria.where(builder.equal(loginPath, login));
		criteria.distinct(true);
		return getEntityOrNull(criteria);
	}
}
