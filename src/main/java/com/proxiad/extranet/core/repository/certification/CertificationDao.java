package com.proxiad.extranet.core.repository.certification;

import java.util.Set;

import com.proxiad.extranet.core.model.certification.Certification;
import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Repository access interface for {@link Certification} entity
 * 
 * @author Mihail Merkov
 */
public interface CertificationDao extends CrudDao<Certification, Long> {
	
	/**
	 * List all {@link Certification}s which belongs to specific {@link Employee}
	 * @param employeeId
	 * @return {@link Set} from {@link Certification}
	 */
	Set<Certification> listCertificationsWhichBelongsToEmployee(final Long employeeId);
}
