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

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.language.EmployeeLanguage;
import com.proxiad.extranet.core.model.language.Language;
import com.proxiad.extranet.core.model.language.LanguageLevel;
import com.proxiad.extranet.core.repository.employee.EmployeeDao;
import com.proxiad.extranet.core.repository.language.LanguageDao;
import com.proxiad.extranet.it.helpers.SimpleEmployeeManagerHelper;

import suit.RepositoryMappingAcceptanceTestSuite;


@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeLanguageRelationshipMappingTest {
	
	private final static int ONE_ELEMENT = 1;
	
	@Autowired
	private SimpleEmployeeManagerHelper employeeManagerHelper;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private LanguageDao languageDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testEmployeeLanguageRelashionship() {
		Employee employee = employeeManagerHelper.createEmployee();
		Language language = createLanguage();
		Long employeeId = employee.getId();
		Long languageId = language.getId();
		
		EmployeeLanguage employeeLanguage = new EmployeeLanguage();
		employeeLanguage.setEmployee(employee);
		employeeLanguage.setLanguage(language);
		employeeLanguage.setLevel(LanguageLevel.B1);
		
		employee.getEmployeeLanguages().add(employeeLanguage);
		language.getEmployeeLanguages().add(employeeLanguage);
		employeeDao.update(employee);
		languageDao.update(language);
		employee = null;
		language = null;
		
		employee = employeeDao.get(employeeId);
		language = languageDao.get(languageId);
		
		Assert.assertNotNull(employee.getEmployeeLanguages());
		Assert.assertEquals(ONE_ELEMENT, employee.getEmployeeLanguages().size());
		
		Assert.assertNotNull(language.getEmployeeLanguages());
		Assert.assertEquals(ONE_ELEMENT, language.getEmployeeLanguages().size());
		
		Assert.assertEquals(employee.getEmployeeLanguages().iterator().next(), language.getEmployeeLanguages().iterator().next());

		cleanup(employee, language);
	}
	
	private void cleanup(final Employee employee, final Language language) {
		employeeManagerHelper.cleanupEmployee(employee);
		languageDao.delete(language);
	}
	
	private Language createLanguage() {
		Language language = new Language();
		language.setName("Bulgaristan");
		language.setFlagCss("bg-flag");
		languageDao.save(language);
		return language;
	}
}
