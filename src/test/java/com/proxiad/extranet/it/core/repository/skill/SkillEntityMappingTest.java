package com.proxiad.extranet.it.core.repository.skill;

import java.util.Arrays;
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

import com.proxiad.extranet.core.model.skill.Skill;
import com.proxiad.extranet.core.model.skill.SkillCategory;
import com.proxiad.extranet.core.repository.skill.SkillCategoryDao;
import com.proxiad.extranet.core.repository.skill.SkillDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillEntityMappingTest {
	
	@Autowired
	private SkillCategoryDao skillCategoryDao;
	
	@Autowired
	private SkillDao skillDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testSkillCategoryCrud() {
		Skill skill = new Skill();
		skill.setTitle("React JS");
		SkillCategory skillCategory = createSkillCategory("Frontend technologies"); 
		skill.setCategory(skillCategory);
		skillCategory.setSkills(new LinkedHashSet<Skill>(Arrays.asList(skill)));
		
		Long id = skillDao.save(skill);
		skillCategoryDao.update(skillCategory);
		
		Assert.assertNotNull(id);
		
		skill = null;
		skill = skillDao.get(id);
		Assert.assertNotNull(skill);
		Assert.assertNotNull(skill.getCategory());
		Assert.assertNotNull(skill.getCategory().getSkills());
		Assert.assertEquals(skill.getId(), skill.getCategory().getSkills().iterator().next().getId());
		Assert.assertEquals("React JS", skill.getTitle());
		
		Long skillCategoryId = skill.getCategory().getId();
		skillDao.delete(skill);
		skillCategoryDao.delete(skillCategoryDao.get(skillCategoryId));
		Assert.assertNull(skillDao.get(id));
		Assert.assertNull(skillCategoryDao.get(skillCategoryId));
	}
	
	private SkillCategory createSkillCategory(final String title) {
		SkillCategory skillCategory = new SkillCategory();
		skillCategory.setTitle(title);
		
		skillCategoryDao.save(skillCategory);
		
		return skillCategory;
	}
}
