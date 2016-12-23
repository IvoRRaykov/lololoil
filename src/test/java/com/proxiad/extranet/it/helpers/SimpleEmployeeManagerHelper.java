package com.proxiad.extranet.it.helpers;

import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

@Component
public class SimpleEmployeeManagerHelper {
	
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
	
	public Employee createEmployee() {
		Credential credential = createCredential("admin", "admin");
		Role role = createSimpleRole("SIMPLE_ROLE", "Super important role");
		Employee employee = new Employee();
		employee.setActive(true);
		employee.setCredential(credential);
		employee.setRoles(new HashSet<Role>());
		employee.getRoles().add(role);
		employee.setFirstName("Admin");
		employee.setLastName("Adminovich");
		employee.setSex(TypeSex.MAN);
		employee.setCreatedOn(new Date());
		employee.setDateOfJoining(new Date());
		UserDetails userDetails = createUserDetails();
		employee.setDetails(userDetails);
		employee.setWorkplace(createWorkplace("Plovdiv-Mlovdiv", "4000", "Best Room"));
		employee.setPosition("Super Admin");
		employeeDao.save(employee);
		
		return employee;
	}
	
	public void cleanupEmployee(final Employee employee) {
		Office office = employee.getWorkplace().getOffice();
		Workplace workplace = employee.getWorkplace();
		Role role = employee.getRoles().iterator().next();
		Credential credential = employee.getCredential();
		UserDetails details = employee.getDetails();
		
		employeeDao.delete(employee);
		roleDao.delete(role);
		credentialDao.delete(credential);
		workplaceDao.delete(workplace);
		officeDao.delete(office);
		userDetailsDao.delete(details);
	}
	
	private UserDetails createUserDetails() {
		UserDetails employeeDetails = new UserDetails();
		employeeDetails.setEmail("ivan-petrovich@abv.bg");
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
