package com.proxiad.extranet.core.repository.interest;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.interest.EmployeeInterest;
import com.proxiad.extranet.core.model.interest.EmployeeInterestId;
import com.proxiad.extranet.core.model.interest.Interest;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Interest} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class InterestDaoHibernate extends GenericHibernateDao<Interest, Long> implements InterestDao {

	/**
	 * @see com.proxiad.extranet.core.repository.interest.InterestDao#listInterestsWhichBelongsToEmployee(java.lang.Long)
	 */
	@Override
	public Set<Interest> listInterestsWhichBelongsToEmployee(final Long employeeId) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Interest> criteria = builder.createQuery(Interest.class);
		Root<EmployeeInterest> employeeInterest = criteria.from(EmployeeInterest.class);
		Path<EmployeeInterestId> pk = employeeInterest.get("pk");
		Path<Employee> employee = pk.get("employee");
		Path<Interest> interest = pk.get("interest");
		criteria.select(interest);
		criteria.where(builder.equal(employee.get("id"), employeeId));
		criteria.distinct(true);
		return getResultSet(criteria);
	}
}
