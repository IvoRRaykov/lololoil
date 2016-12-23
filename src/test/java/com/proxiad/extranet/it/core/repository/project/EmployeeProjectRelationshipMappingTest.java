package com.proxiad.extranet.it.core.repository.project;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proxiad.extranet.core.model.client.Client;
import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.project.EmployeeProject;
import com.proxiad.extranet.core.model.project.Project;
import com.proxiad.extranet.core.repository.client.ClientDao;
import com.proxiad.extranet.core.repository.employee.EmployeeDao;
import com.proxiad.extranet.core.repository.project.ProjectDao;
import com.proxiad.extranet.it.helpers.SimpleEmployeeManagerHelper;

import suit.RepositoryMappingAcceptanceTestSuite;


@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeProjectRelationshipMappingTest {
	
	private final static int ONE_ELEMENT = 1;
	
	@Autowired
	private SimpleEmployeeManagerHelper employeeManagerHelper;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private ProjectDao projectDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testEmployeeProjectRelashionship() {
		Employee employee = employeeManagerHelper.createEmployee();
		Project project = createProject();
		Long employeeId = employee.getId();
		Long projectId = project.getId();
		
		EmployeeProject employeeProject = new EmployeeProject();
		employeeProject.setEmployee(employee);
		employeeProject.setProject(project);
		employeeProject.setResponsibilities("Ailyak whole day");
		employeeProject.setStartOn(new Date());
		employeeProject.setCurrentProject(true);
		
		employee.getEmployeeProjects().add(employeeProject);
		project.getEmployeeProjects().add(employeeProject);
		employeeDao.update(employee);
		projectDao.update(project);
		employee = null;
		project = null;
		
		employee = employeeDao.get(employeeId);
		project = projectDao.get(projectId);
		
		Assert.assertNotNull(employee.getEmployeeProjects());
		Assert.assertEquals(ONE_ELEMENT, employee.getEmployeeProjects().size());
		
		Assert.assertNotNull(project.getEmployeeProjects());
		Assert.assertEquals(ONE_ELEMENT, project.getEmployeeProjects().size());
		
		Assert.assertEquals(employee.getEmployeeProjects().iterator().next(), project.getEmployeeProjects().iterator().next());

		cleanup(employee, project);
	}
	
	private void cleanup(final Employee employee, final Project project) {
		Client client = project.getClient();
		
		employeeManagerHelper.cleanupEmployee(employee);
		projectDao.delete(project);
		clientDao.delete(client);
	}
	
	private Client createClient() {
		Client client = new Client();
		client.setName("Proxiad");
		client.setServiceCenter(true);
		client.setActive(true);
		client.setCodeCEGID1("somecode");
		client.setSpecial(true);
		clientDao.save(client);
		
		return client;
	}
	
	private Project createProject() {
		Client client = createClient();
		Project project = new Project();
		project.setBusinessDomain("Internal business optimization");
		project.setClient(client);
		project.setDescription("Creation of an entire new platform from the scratch");
		project.setName("Trombinoscope");
		projectDao.save(project);
		
		return project;
	}
}
