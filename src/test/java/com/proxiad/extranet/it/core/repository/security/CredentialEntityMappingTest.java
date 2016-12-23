package com.proxiad.extranet.it.core.repository.security;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proxiad.extranet.core.model.security.Credential;
import com.proxiad.extranet.core.repository.security.CredentialDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CredentialEntityMappingTest {
	
	@Autowired
	private CredentialDao credentialDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testRoleCrud() {
		Credential credential = new Credential();
		credential.setLogin("m.merkov");
		credential.setPassword("123456");
		
		Long id = credentialDao.save(credential);
		Assert.assertNotNull(id);
		
		credential = null;
		
		credential = credentialDao.get(id);
		Assert.assertNotNull(credential);
		Assert.assertEquals("m.merkov", credential.getLogin());
		
		credentialDao.delete(credential);
		Assert.assertNull(credentialDao.get(id));
	}
}
