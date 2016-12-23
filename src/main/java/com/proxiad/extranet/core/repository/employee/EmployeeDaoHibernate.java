package com.proxiad.extranet.core.repository.employee;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.employee.beans.EmployeeDTO;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Employee} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class EmployeeDaoHibernate extends GenericHibernateDao<Employee, Long> implements EmployeeDao {

	/**
	 * @see com.proxiad.extranet.core.repository.employee.EmployeeDao#listActive()
	 */
	@Override
	public List<EmployeeDTO> listActiveEmployeesWithMainDataOnly() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<EmployeeDTO> criteria = builder.createQuery(EmployeeDTO.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		criteria.select(builder.construct(EmployeeDTO.class, 
			root.get("id"), root.get("firstName"), root.get("lastName"), root.get("pathToProfilePicture"),
			root.get("sex"), root.get("active"), root.get("createdOn"), root.get("createdBy").get("id").alias("createdById"), 
			root.get("details"), root.get("dateOfJoining"), root.get("dateOfLeaving"), 
			root.get("workplace"), root.get("position")
		));
		
		criteria.where(builder.equal(root.get("active"), Boolean.TRUE));
		
		return getResultList(criteria);
	}
		
}
