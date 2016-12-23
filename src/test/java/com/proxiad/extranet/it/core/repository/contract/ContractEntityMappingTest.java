package com.proxiad.extranet.it.core.repository.contract;

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
import com.proxiad.extranet.core.model.contract.Contract;
import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.repository.client.ClientDao;
import com.proxiad.extranet.core.repository.contract.ContractDao;
import com.proxiad.extranet.it.helpers.SimpleEmployeeManagerHelper;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ContractEntityMappingTest {
	
	@Autowired
	private ContractDao contractDao;
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private SimpleEmployeeManagerHelper employeeManagerHelper;

	@Transactional
	@Rollback(false)
	@Test
	public void testContractCrud() {
		Employee employee = employeeManagerHelper.createEmployee();
		Client client = createClient();
		Contract contract = new Contract();
		
		contract.setClient(client);
		contract.setEmployee(employee);
		contract.setDailyRate(5.14);
		contract.setNumber("20160235");
		contract.setStartDate(new Date());
		
		Long contractId = contractDao.save(contract);
		contract = null;
		Assert.assertNotNull(contractId);
		contract = contractDao.get(contractId);
		Assert.assertNotNull(contract);
		Assert.assertNotNull(contract.getClient());
		Assert.assertNotNull(contract.getEmployee());
		Assert.assertEquals("20160235", contract.getNumber());
		
		contractDao.delete(contract);
		Assert.assertNull(contractDao.get(contractId));
		
		employeeManagerHelper.cleanupEmployee(employee);
		clientDao.delete(client);
	}
	
	private Client createClient() {
		Client client = new Client();
		client.setName("Trombinoscope");
		client.setServiceCenter(true);
		client.setActive(true);
		client.setCodeCEGID1("somecode");
		client.setSpecial(true);
		
		clientDao.save(client);
		return client;
	}
}
