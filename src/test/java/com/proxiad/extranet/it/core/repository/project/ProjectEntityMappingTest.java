package com.proxiad.extranet.it.core.repository.project;

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
import com.proxiad.extranet.core.model.project.Project;
import com.proxiad.extranet.core.repository.client.ClientDao;
import com.proxiad.extranet.core.repository.project.ProjectDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProjectEntityMappingTest {
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private ProjectDao projectDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testProjectCrud() {
		Client client = createClient();
		Project project = new Project();
		project.setBusinessDomain("Internal business optimization");
		project.setClient(client);
		project.setDescription("Creation of an entire new platform from the scratch");
		project.setName("Trombinoscope");
		
		Long projectId = projectDao.save(project);
		Assert.assertNotNull(projectId);
		project = null;
		project = projectDao.get(projectId);
		
		Assert.assertNotNull(project);
		Assert.assertNotNull(project.getClient());
		Assert.assertEquals("Trombinoscope", project.getName());
		
		projectDao.delete(project);
		Assert.assertNull(projectDao.get(projectId));
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
}
