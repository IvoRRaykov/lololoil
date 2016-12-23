package com.proxiad.extranet.core.repository.skill;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.skill.EmployeeSkill;
import com.proxiad.extranet.core.model.skill.EmployeeSkillId;
import com.proxiad.extranet.core.model.skill.Skill;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Skill} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class SkillDaoHibernate extends GenericHibernateDao<Skill, Long> implements SkillDao {

	/**
	 * @see com.proxiad.extranet.core.repository.skill.SkillDao#listSkillsWhichBelongsToEmployee(java.lang.Long)
	 */
	@Override
	public Set<Skill> listSkillsWhichBelongsToEmployee(final Long employeeId) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Skill> criteria = builder.createQuery(Skill.class);
		Root<EmployeeSkill> employeeSkill = criteria.from(EmployeeSkill.class);
		Path<EmployeeSkillId> pk = employeeSkill.get("pk");
		Path<Employee> employee = pk.get("employee");
		Path<Skill> skill = pk.get("skill");
		criteria.select(skill);
		criteria.where(builder.equal(employee.get("id"), employeeId));
		criteria.distinct(true);
		return getResultSet(criteria);
	}
}
