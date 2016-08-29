package com.galaxyinternet.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.galaxyinternet.bo.RoleBO;
import com.galaxyinternet.model.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class RoleServiceTest
{
	@Autowired
	private RoleService roleService;
	
	@Test
	public void add()
	{
		List<Long> ids = new ArrayList<Long>();
		Role entity = null;
		for(int i = 1; i<=10;i++)
		{
			entity = new Role();
			entity.setRoleCode("code "+i);
			entity.setRoleName("name "+i);
			entity.setRoleDesc("desc "+i);
			roleService.insert(entity);
			ids.add(Long.valueOf(i));
		}
		RoleBO query = new RoleBO();
		query.setIds(ids);
		List<Role> list = roleService.select(query);
		Assert.assertTrue(list.size()>=10);
	}
}
