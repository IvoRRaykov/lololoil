package com.proxiad.extranet.core.repository.language;

import java.util.Set;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.language.EmployeeLanguage;
import com.proxiad.extranet.core.model.language.Language;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Repository access interface for {@link Language} entity
 * 
 * @author Mihail Merkov
 */
public interface LanguageDao extends CrudDao<Language, Long> {
	
	/**
	 * List all {@link Language}s, that specific {@link Employee} knows
	 * @param employeeId the id of specific {@link Employee}
	 * @return {@link Set} from {@link Language}
	 */
	Set<EmployeeLanguage> listLanguagesWhoEmployeeKnows(final Long employeeId);
}
