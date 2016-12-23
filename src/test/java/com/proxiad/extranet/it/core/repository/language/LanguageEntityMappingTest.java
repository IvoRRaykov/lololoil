package com.proxiad.extranet.it.core.repository.language;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proxiad.extranet.core.model.language.Language;
import com.proxiad.extranet.core.repository.language.LanguageDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LanguageEntityMappingTest {
	
	@Autowired
	private LanguageDao languageDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testLanguageCrud() {
		Language language = new Language();
		language.setName("Bulgarian");
		language.setFlagCss("flag-bg");
		
		Long id = languageDao.save(language);
		Assert.assertNotNull(id);
		
		language = null;
		
		language = languageDao.get(id);
		Assert.assertNotNull(language);
		Assert.assertEquals("Bulgarian", language.getName());
		
		languageDao.delete(language);
		Assert.assertNull(languageDao.get(id));
	}
}
