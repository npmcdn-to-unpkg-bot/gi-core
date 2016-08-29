package com.galaxyinternet.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.galaxyinternet.bo.ResourceBO;
import com.galaxyinternet.model.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class ResourceServiceTest
{
	@Autowired
	private ResourceService resourceService;
	@Test
	public void add()
	{
		List<Long> ids = new ArrayList<Long>();
		int size = 10;
		Resource entity = null;
		for(int i=1;i<=size;i++)
		{
			entity = new Resource();
			entity.setParentId(0L);
			entity.setResourceCode("code "+i);
			entity.setResourceName("name "+i);
			entity.setResourceDesc("desc "+i);
			entity.setResourceOrder(i);
			entity.setSystemCode("platform");
			resourceService.insert(entity);
			Assert.notNull(entity.getId());
			ids.add(entity.getId());
		}
		ResourceBO query = new ResourceBO();
		query.setIds(ids);
		List<Resource> list = resourceService.select(query);
		Assert.isTrue(list.size() == size);
		
	}
	@Test
	public void test2()
	{
		List<Resource> perms = resourceService.getUserPermission(1L);
		Assert.notNull(perms);
	}
	
}
