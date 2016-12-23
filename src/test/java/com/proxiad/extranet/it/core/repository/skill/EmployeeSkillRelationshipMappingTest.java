package com.proxiad.extranet.it.core.repository.skill;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.skill.EmployeeSkill;
import com.proxiad.extranet.core.model.skill.Skill;
import com.proxiad.extranet.core.model.skill.SkillCategory;
import com.proxiad.extranet.core.repository.employee.EmployeeDao;
import com.proxiad.extranet.core.repository.skill.SkillCategoryDao;
import com.proxiad.extranet.core.repository.skill.SkillDao;
import com.proxiad.extranet.it.helpers.SimpleEmployeeManagerHelper;

import suit.RepositoryMappingAcceptanceTestSuite;


@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeSkillRelationshipMappingTest {
	
	private final static int ONE_ELEMENT = 1;
	
	@Autowired
	private SimpleEmployeeManagerHelper employeeManagerHelper;
	
	@Autowired
	private SkillCategoryDao skillCategoryDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private SkillDao skillDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testEmployeeSkillRelashionship() {
		Employee employee = employeeManagerHelper.createEmployee();
		Skill skill = createSkill();
		Long employeeId = employee.getId();
		Long skillId = skill.getId();
		
		EmployeeSkill employeeSkill = new EmployeeSkill();
		employeeSkill.setEmployee(employee);
		employeeSkill.setSkill(skill);
		employeeSkill.setDate(new Date());
		
		employee.getEmployeeSkills().add(employeeSkill);
		skill.getEmployeeSkills().add(employeeSkill);
		employeeDao.update(employee);
		skillDao.update(skill);
		employee = null;
		skill = null;
		
		employee = employeeDao.get(employeeId);
		skill = skillDao.get(skillId);
		
		Assert.assertNotNull(employee.getEmployeeSkills());
		Assert.assertEquals(ONE_ELEMENT, employee.getEmployeeSkills().size());
		
		Assert.assertNotNull(skill.getEmployeeSkills());
		Assert.assertEquals(ONE_ELEMENT, skill.getEmployeeSkills().size());
		
		Assert.assertEquals(employee.getEmployeeSkills().iterator().next(), skill.getEmployeeSkills().iterator().next());

		cleanup(employee, skill);
	}
	
	private void cleanup(final Employee employee, final Skill skill) {
		SkillCategory skillCategory = skill.getCategory();
		
		employeeManagerHelper.cleanupEmployee(employee);
		skillDao.delete(skill);
		skillCategoryDao.delete(skillCategory);
	}
	
	private Skill createSkill() {
		Skill skill = new Skill();
		skill.setTitle("React JS");
		SkillCategory skillCategory = createSkillCategory("Frontend technologies"); 
		skill.setCategory(skillCategory);
		skillCategory.setSkills(new LinkedHashSet<Skill>(Arrays.asList(skill)));
		skillDao.save(skill);
		return skill;
	}
	
	private SkillCategory createSkillCategory(final String title) {
		SkillCategory skillCategory = new SkillCategory();
		skillCategory.setTitle(title);
		
		skillCategoryDao.save(skillCategory);
		
		return skillCategory;
	}
}
