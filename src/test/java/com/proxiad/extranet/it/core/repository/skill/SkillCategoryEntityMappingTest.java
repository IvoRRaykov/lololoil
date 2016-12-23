package com.proxiad.extranet.it.core.repository.skill;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proxiad.extranet.core.model.skill.SkillCategory;
import com.proxiad.extranet.core.repository.skill.SkillCategoryDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillCategoryEntityMappingTest {
	
	@Autowired
	private SkillCategoryDao skillCategoryDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testSkillCategoryCrud() {
		SkillCategory skillCategory = new SkillCategory();
		skillCategory.setTitle("Frontend technologies");
		
		Long id = skillCategoryDao.save(skillCategory);
		Assert.assertNotNull(id);
		
		skillCategory = null;
		
		skillCategory = skillCategoryDao.get(id);
		Assert.assertNotNull(skillCategory);
		Assert.assertEquals("Frontend technologies", skillCategory.getTitle());
		
		skillCategoryDao.delete(skillCategory);
		Assert.assertNull(skillCategoryDao.get(id));
	}
}
