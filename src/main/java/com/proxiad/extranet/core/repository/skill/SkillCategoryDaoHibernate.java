package com.proxiad.extranet.core.repository.skill;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.skill.SkillCategory;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link SkillCategory} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class SkillCategoryDaoHibernate extends GenericHibernateDao<SkillCategory, Long> implements SkillCategoryDao {}
