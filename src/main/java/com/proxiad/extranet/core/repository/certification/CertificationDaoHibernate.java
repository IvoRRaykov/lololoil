package com.proxiad.extranet.core.repository.certification;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.certification.Certification;
import com.proxiad.extranet.core.model.certification.EmployeeCertification;
import com.proxiad.extranet.core.model.certification.EmployeeCertificationId;
import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Certification} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class CertificationDaoHibernate extends GenericHibernateDao<Certification, Long> implements CertificationDao {

	/**
	 * @see com.proxiad.extranet.core.repository.certification.CertificationDao#listCertificationsWhichBelongsToEmployee(java.lang.Long)
	 */
	@Override
	public Set<Certification> listCertificationsWhichBelongsToEmployee(final Long employeeId) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Certification> criteria = builder.createQuery(Certification.class);
		Root<EmployeeCertification> employeeCertification = criteria.from(EmployeeCertification.class);
		Path<EmployeeCertificationId> pk = employeeCertification.get("pk");
		Path<Employee> employee = pk.get("employee");
		Path<Certification> certification = pk.get("certification");
		criteria.select(certification);
		criteria.where(builder.equal(employee.get("id"), employeeId));
		criteria.distinct(true);
		return getResultSet(criteria);
	}
	
	
}
