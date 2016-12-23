package com.proxiad.extranet.core.repository.language;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.language.EmployeeLanguage;
import com.proxiad.extranet.core.model.language.EmployeeLanguageId;
import com.proxiad.extranet.core.model.language.Language;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Language} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class LanguageDaoHibernate extends GenericHibernateDao<Language, Long> implements LanguageDao {

	/**
	 * @see com.proxiad.extranet.core.repository.language.LanguageDao#listLanguagesWhoEmployeeKnows(java.lang.Long)
	 */
	@Override
	public Set<EmployeeLanguage> listLanguagesWhoEmployeeKnows(final Long employeeId) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<EmployeeLanguage> criteria = builder.createQuery(EmployeeLanguage.class);
		Root<EmployeeLanguage> employeeLanguage = criteria.from(EmployeeLanguage.class);
		Path<EmployeeLanguageId> pk = employeeLanguage.get("pk");
		Path<Employee> employee = pk.get("employee");
		criteria.where(builder.equal(employee.get("id"), employeeId));
		criteria.distinct(true);
		return getResultSet(criteria);
	}

}
