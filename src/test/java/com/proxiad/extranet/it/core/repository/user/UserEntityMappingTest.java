package com.proxiad.extranet.it.core.repository.user;

import java.util.Date;
import java.util.HashSet;

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
import com.proxiad.extranet.core.model.security.Role;
import com.proxiad.extranet.core.model.user.TypeSex;
import com.proxiad.extranet.core.model.user.User;
import com.proxiad.extranet.core.model.user.UserDetails;
import com.proxiad.extranet.core.repository.security.CredentialDao;
import com.proxiad.extranet.core.repository.security.RoleDao;
import com.proxiad.extranet.core.repository.user.UserDao;
import com.proxiad.extranet.core.repository.user.UserDetailsDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserEntityMappingTest {
	
	private final static int ONE_ELEMENT = 1;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	private CredentialDao credentialDao;
	
	@Autowired
	private RoleDao roleDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testUserCrud() {
		Credential credential = createCredential("admin", "admin");
		Role role = createSimpleRole("SIMPLE_ROLE", "Super important role");
		User userAdmin = new User();
		userAdmin.setActive(true);
		userAdmin.setCredential(credential);
		userAdmin.setRoles(new HashSet<Role>());
		userAdmin.getRoles().add(role);
		userAdmin.setFirstName("Admin");
		userAdmin.setLastName("Adminovich");
		userAdmin.setSex(TypeSex.MAN);
		userAdmin.setCreatedOn(new Date());
		UserDetails details = createUserDetails();
		userAdmin.setDetails(details);
		Long idUserAdmin = userDao.save(userAdmin);
		userAdmin = null;
		userAdmin = userDao.get(idUserAdmin);
		
		Assert.assertNotNull(idUserAdmin);
		Assert.assertNotNull(userAdmin.getCredential());
		Assert.assertNotNull(userAdmin.getRoles());
		Assert.assertEquals(ONE_ELEMENT, userAdmin.getRoles().size());
		Assert.assertEquals("Admin", userAdmin.getFirstName());
		Assert.assertEquals(true, userAdmin.isActive());
		Assert.assertNotNull(userAdmin.getDetails());
		Assert.assertNull(userAdmin.getCreatedBy());

		userAdmin = null;
		userAdmin = userDao.get(idUserAdmin);
		
		Assert.assertNotNull(userAdmin.getDetails());
		//Assert.assertEquals(userAdmin.getId(), userAdmin.getDetails().getId());
		
		User user = new User();
		user.setActive(true);
		user.setCredential(credential);
		user.setRoles(new HashSet<Role>());
		user.getRoles().add(role);
		user.setFirstName("Ivan");
		user.setLastName("Ivanovich");
		user.setSex(TypeSex.WOMAN);
		user.setCreatedBy(userAdmin);
		UserDetails details2 = new UserDetails();
		details2.setEmail("123Det@abv.bg");
		details2.setBlog("mlog");
		userDetailsDao.save(details2);
		user.setDetails(details);
		user.setCreatedOn(new Date());
		
		Long idUser = userDao.save(user);
		user = null;
		user = userDao.get(idUser);
		Assert.assertNotNull(user.getCreatedBy());
		
		userAdmin = null;
		userAdmin = userDao.get(idUserAdmin);
		
		createUserDetails();
		userDao.update(userAdmin);
		userAdmin = null;
		userAdmin = userDao.get(idUserAdmin);
		
		userDao.delete(userAdmin);
		userDao.delete(user);
		roleDao.delete(role);
		credentialDao.delete(credential);
		userDetailsDao.delete(details);
		userDetailsDao.delete(details2);
		
		Assert.assertNull(userDao.get(user.getId()));
		Assert.assertNull(roleDao.get(role.getId()));
		Assert.assertNull(credentialDao.get(credential.getId()));
		Assert.assertNull(userDetailsDao.get(details.getId()));
		Assert.assertNull(userDetailsDao.get(details2.getId()));
	}
	
	private UserDetails createUserDetails() {
		UserDetails userDetails = new UserDetails();
		userDetails.setEmail("ivan@abv.bg");
		userDetails.setBirthdate(new Date());
		userDetails.setPhone("054 1223 44");
		userDetails.setShowBirthdate(true);
		userDetails.setWebsite("www.mywebsite.org");		
		userDetailsDao.save(userDetails);
		return userDetails;
	}
	
	private Credential createCredential(final String login, final String password) {
		Credential credential = new Credential();
		credential.setLogin(login);
		credential.setPassword(password);
		credentialDao.save(credential);
		return credential;
	}
	
	private Role createSimpleRole(final String name, final String description) {
		Role role = new Role();
		role.setName(name);
		role.setDescription(description);
		roleDao.save(role);
		return role;
	}
}
