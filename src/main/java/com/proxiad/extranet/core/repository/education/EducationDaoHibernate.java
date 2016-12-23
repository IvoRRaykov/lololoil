package com.proxiad.extranet.core.repository.education;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.education.Education;
import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Education} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class EducationDaoHibernate extends GenericHibernateDao<Education, Long> implements EducationDao {

	/**
	 * @see com.proxiad.extranet.core.repository.education.EducationDao#listEducationsOfEmployee(java.lang.Long)
	 */
	@Override
	public Set<Education> listEducationsOfEmployee(final Long employeeId) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Education> criteria = builder.createQuery(Education.class);
		Root<Education> education = criteria.from(Education.class);
		Path<Employee> employee = education.get("employee");
		criteria.where(builder.equal(employee.get("id"), employeeId));
		criteria.orderBy(builder.desc(education.get("startDate")));
		criteria.distinct(true);
		return getResultSet(criteria);
	}
}