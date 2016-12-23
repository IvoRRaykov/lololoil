package com.proxiad.extranet.it.core.repository.employee;

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

import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.security.Credential;
import com.proxiad.extranet.core.model.security.Role;
import com.proxiad.extranet.core.model.user.TypeSex;
import com.proxiad.extranet.core.model.user.UserDetails;
import com.proxiad.extranet.core.model.workplace.Office;
import com.proxiad.extranet.core.model.workplace.Workplace;
import com.proxiad.extranet.core.repository.employee.EmployeeDao;
import com.proxiad.extranet.core.repository.security.CredentialDao;
import com.proxiad.extranet.core.repository.security.RoleDao;
import com.proxiad.extranet.core.repository.user.UserDetailsDao;
import com.proxiad.extranet.core.repository.workplace.OfficeDao;
import com.proxiad.extranet.core.repository.workplace.WorkplaceDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeEntityMappingTest {
	
	private final static int ONE_ELEMENT = 1;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	private CredentialDao credentialDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private OfficeDao officeDao;
	
	@Autowired
	private WorkplaceDao workplaceDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testEmployeeCrud() {
		Credential credential = createCredential("admin", "admin");
		Role role = createSimpleRole("SIMPLE_ROLE", "Super important role");
		Employee employeeAdmin = new Employee();
		employeeAdmin.setActive(true);
		employeeAdmin.setCredential(credential);
		employeeAdmin.setRoles(new HashSet<Role>());
		employeeAdmin.getRoles().add(role);
		employeeAdmin.setFirstName("Admin");
		employeeAdmin.setLastName("Adminovich");
		employeeAdmin.setSex(TypeSex.MAN);
		employeeAdmin.setCreatedOn(new Date());
		employeeAdmin.setDateOfJoining(new Date());
		UserDetails adminDetails = createUserDetails();
		employeeAdmin.setDetails(adminDetails);
		employeeAdmin.setWorkplace(createWorkplace("Plovdiv", "4000", "Best Room"));
		employeeAdmin.setPosition("Super Admin");
		Long idEmployeeAdmin = employeeDao.save(employeeAdmin);
		employeeAdmin = null;
		employeeAdmin = employeeDao.get(idEmployeeAdmin);
		
		Assert.assertNotNull(idEmployeeAdmin);
		Assert.assertNotNull(employeeAdmin.getCredential());
		Assert.assertNotNull(employeeAdmin.getRoles());
		Assert.assertEquals(ONE_ELEMENT, employeeAdmin.getRoles().size());
		Assert.assertEquals("Admin", employeeAdmin.getFirstName());
		Assert.assertEquals(true, employeeAdmin.isActive());
		Assert.assertNotNull(employeeAdmin.getDetails());
		Assert.assertNull(employeeAdmin.getCreatedBy());
		Assert.assertNotNull(employeeAdmin.getWorkplace());
		Assert.assertNotNull(employeeAdmin.getWorkplace().getOffice());
		Assert.assertEquals("Plovdiv", employeeAdmin.getWorkplace().getOffice().getCity());
		Assert.assertEquals("Best Room", employeeAdmin.getWorkplace().getRoom());

		employeeAdmin = null;
		employeeAdmin = employeeDao.get(idEmployeeAdmin);
		
		Assert.assertNotNull(employeeAdmin.getDetails());
		//Assert.assertEquals(employeeAdmin.getId(), employeeAdmin.getDetails().getId());
		
		Employee employee = new Employee();
		employee.setActive(true);
		employee.setDateOfJoining(new Date());
		employee.setCredential(credential);
		employee.setRoles(new HashSet<Role>());
		employee.getRoles().add(role);
		employee.setFirstName("Ivan");
		employee.setLastName("Ivanovich");
		employee.setSex(TypeSex.WOMAN);
		employee.setCreatedBy(employeeAdmin);
		employee.setDetails(new UserDetails());
		UserDetails employeeDetails = createUserDetails();
		employee.setDetails(employeeDetails);
		employee.setCreatedOn(new Date());
		employee.setWorkplace(createWorkplace("Sofia", "2536", "Best Room"));
		employee.setPosition("Java Senior Software Enfineer");
		
		Long idEmployee = employeeDao.save(employee);
		employee = null;
		employee = employeeDao.get(idEmployee);
		Assert.assertNotNull(employee.getCreatedBy());
		
		employeeAdmin = null;
		employeeAdmin = employeeDao.get(idEmployeeAdmin);

		employeeAdmin = null;
		employeeAdmin = employeeDao.get(idEmployeeAdmin);
		
		Office officePld = employeeAdmin.getWorkplace().getOffice();
		Office officeSf = employee.getWorkplace().getOffice();
		Workplace adminWorkplace = employeeAdmin.getWorkplace();
		Workplace employeeWorkplace = employee.getWorkplace();
		employeeDao.delete(employeeAdmin);
		employeeDao.delete(employee);
		roleDao.delete(role);
		credentialDao.delete(credential);
		workplaceDao.delete(adminWorkplace);
		workplaceDao.delete(employeeWorkplace);
		officeDao.delete(officePld);
		officeDao.delete(officeSf);
		userDetailsDao.delete(adminDetails);
		userDetailsDao.delete(employeeDetails);
		
		Assert.assertNull(employeeDao.get(employee.getId()));
		Assert.assertNull(roleDao.get(role.getId()));
		Assert.assertNull(credentialDao.get(credential.getId()));
		Assert.assertNull(officeDao.get(officePld.getId()));
		Assert.assertNull(officeDao.get(officePld.getId()));
		Assert.assertNull(workplaceDao.get(adminWorkplace.getId()));
		Assert.assertNull(workplaceDao.get(employeeWorkplace.getId()));
		Assert.assertNull(userDetailsDao.get(adminDetails.getId()));
		Assert.assertNull(userDetailsDao.get(employeeDetails.getId()));
	}
	
	private UserDetails createUserDetails() {
		UserDetails employeeDetails = new UserDetails();
		employeeDetails.setEmail("ivan@abv.bg");
		employeeDetails.setBirthdate(new Date());
		employeeDetails.setPhone("054 1223 44");
		employeeDetails.setShowBirthdate(true);
		employeeDetails.setWebsite("www.mywebsite.org");
		userDetailsDao.save(employeeDetails);
		return employeeDetails;
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
	
	private Workplace createWorkplace(final String city, final String postCode, final String room) {
		Office office = new Office();
		office.setCity(city);
		office.setPostCode(postCode);
		officeDao.save(office);
		
		Workplace workplace = new Workplace();
		workplace.setOffice(office);
		workplace.setFloor("5-th");
		workplace.setRoom(room);
		
		workplaceDao.save(workplace);
		
		return workplace;
	}
}
