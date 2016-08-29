package com.galaxyinternet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.galaxyinternet.bo.UserRoleBO;
import com.galaxyinternet.dao.UserRoleDAO;
import com.galaxyinternet.model.User;
import com.galaxyinternet.model.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class UserServiceTest
{
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleDAO userRoleDAO;
	
	@Test
	public void testAdd()
	{
		User user = new User();
		user.setUserName("用户1");
		user.setUserLogin("user1");
		user.setPassword("pasword1");
		user.setCreatedUser("cuser1");
		user.setCreatedTime(new Date());
		user.setUpdatedTime(new Date());
		user.setUpdatedUser("uuser1");
		userService.insert(user);
	}
	@Test
	public void addUserRole()
	{
		UserRole entity = new UserRole();
		entity.setUserId(1L);
		entity.setRoleId(1L);
		userRoleDAO.insert(entity);
		Assert.notNull(entity.getId());
	}
	@Test
	public void searchUserRole()
	{
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(1L);
		userIds.add(2L);
		userIds.add(3L);
		
		List<Long> roleIds = new ArrayList<Long>();
		roleIds.add(3L);
		roleIds.add(2L);
		roleIds.add(1L);
		
		UserRoleBO entity = new UserRoleBO();
		entity.setUserIds(userIds);
		entity.setRoleIds(roleIds);
		userRoleDAO.select(entity);
	}
}
