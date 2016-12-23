package com.proxiad.extranet.it.core.repository.param;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proxiad.extranet.core.model.param.ParamName;
import com.proxiad.extranet.core.service.param.ParameterService;

import suit.RepositoryMappingAcceptanceTestSuite;

@Category(RepositoryMappingAcceptanceTestSuite.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ParameterEntityMappingTest {
	
	@Autowired
	private ParameterService parameterService;

	@Transactional
	@Rollback(false)
	@Test
	public void testParameterFetching() {
		String imageSrcPath = parameterService.getParamAsString(ParamName.IMAGES_SOURCE_PATH);
		Assert.assertNotNull(imageSrcPath);
	}
}
