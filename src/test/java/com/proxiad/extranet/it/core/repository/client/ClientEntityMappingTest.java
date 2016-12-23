package com.proxiad.extranet.it.core.repository.client;

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
import com.proxiad.extranet.core.repository.client.ClientDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClientEntityMappingTest {
	
	@Autowired
	private ClientDao clientDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testClientCrud() {
		Client client = new Client();
		client.setName("Trombinoscope");
		client.setServiceCenter(true);
		client.setActive(true);
		client.setCodeCEGID1("somecode");
		client.setSpecial(true);
		
		Long id = clientDao.save(client);
		Assert.assertNotNull(id);
		
		client = null;
		
		client = clientDao.get(id);
		Assert.assertNotNull(client);
		Assert.assertEquals("Trombinoscope", client.getName());
		
		clientDao.delete(client);
		Assert.assertNull(clientDao.get(id));
	}
}
