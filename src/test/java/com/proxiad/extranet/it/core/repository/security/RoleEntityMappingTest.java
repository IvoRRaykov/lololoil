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

import com.proxiad.extranet.core.model.security.Role;
import com.proxiad.extranet.core.repository.security.RoleDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RoleEntityMappingTest {
	
	@Autowired
	private RoleDao roleDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testRoleCrud() {
		Role role = new Role();
		role.setName("ROLE_ADMINISTRATOR");
		role.setDescription("The primary administrator of the system");
		
		Long id = roleDao.save(role);
		Assert.assertNotNull(id);
		
		role = null;
		
		role = roleDao.get(id);
		Assert.assertNotNull(role);
		Assert.assertEquals("ROLE_ADMINISTRATOR", role.getName());
		
		roleDao.delete(role);
		Assert.assertNull(roleDao.get(id));
	}
}
