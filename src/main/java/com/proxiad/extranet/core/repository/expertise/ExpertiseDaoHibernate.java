package com.proxiad.extranet.core.repository.expertise;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.expertise.EmployeeExpertise;
import com.proxiad.extranet.core.model.expertise.EmployeeExpertiseId;
import com.proxiad.extranet.core.model.expertise.Expertise;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Expertise} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class ExpertiseDaoHibernate extends GenericHibernateDao<Expertise, Long> implements ExpertiseDao {

	/**
	 * @see com.proxiad.extranet.core.repository.expertise.ExpertiseDao#listExpertisesWhichBelongsToEmployee(java.lang.Long)
	 */
	@Override
	public Set<Expertise> listExpertisesWhichBelongsToEmployee(final Long employeeId) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Expertise> criteria = builder.createQuery(Expertise.class);
		Root<EmployeeExpertise> employeeExpertise = criteria.from(EmployeeExpertise.class);
		Path<EmployeeExpertiseId> pk = employeeExpertise.get("pk");
		Path<Employee> employee = pk.get("employee");
		Path<Expertise> expertise = pk.get("expertise");
		criteria.select(expertise);
		criteria.where(builder.equal(employee.get("id"), employeeId));
		criteria.distinct(true);
		return getResultSet(criteria);
	}
}
