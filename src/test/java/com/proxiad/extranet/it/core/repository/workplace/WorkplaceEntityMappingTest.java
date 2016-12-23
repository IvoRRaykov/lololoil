package com.proxiad.extranet.it.core.repository.workplace;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proxiad.extranet.core.model.workplace.Office;
import com.proxiad.extranet.core.model.workplace.Workplace;
import com.proxiad.extranet.core.repository.workplace.OfficeDao;
import com.proxiad.extranet.core.repository.workplace.WorkplaceDao;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WorkplaceEntityMappingTest {
	
	@Autowired
	private OfficeDao officeDao;
	
	@Autowired
	private WorkplaceDao workplaceDao;

	@Transactional
	@Rollback(false)
	@Test
	public void testOfficeCrud() {
		Office office = createOffice("Plovdiv", "4000");
		Workplace workplace = new Workplace();
		workplace.setFloor("5");
		workplace.setOffice(office);
		workplace.setRoom("The west side one");
				
		Long workplaceId = workplaceDao.save(workplace);
		Long officeId = office.getId();
		Assert.assertNotNull(workplaceId);
		
		workplace = null;
		
		workplace = workplaceDao.get(workplaceId);
		
		Assert.assertNotNull(office);
		Assert.assertNotNull(workplace);
		Assert.assertNotNull(workplace.getOffice());
		Assert.assertEquals(office.getCity(), workplace.getOffice().getCity());
		Assert.assertEquals("The west side one", workplace.getRoom());
		
		workplaceDao.delete(workplace);
		officeDao.delete(office);
		Assert.assertNull(officeDao.get(officeId));
		Assert.assertNull(workplaceDao.get(workplaceId));
	}
	
	private Office createOffice(final String city, final String postCode) {
		Office office = new Office();
		office.setCity(city);
		office.setPostCode(postCode);
		
		officeDao.save(office);
		
		return office;
	}
}
