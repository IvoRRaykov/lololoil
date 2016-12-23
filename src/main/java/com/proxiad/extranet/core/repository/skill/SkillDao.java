package com.proxiad.extranet.core.repository.skill;

import java.util.Set;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.skill.Skill;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Repository access interface for {@link Skill} entity
 * 
 * @author Mihail Merkov
 */
public interface SkillDao extends CrudDao<Skill, Long> {
	
	/**
	 * List all {@link Skill}s which belongs to specific {@link Employee}
	 * @param employeeId
	 * @return {@link Set} from {@link Skill}
	 */
	Set<Skill> listSkillsWhichBelongsToEmployee(final Long employeeId);
}