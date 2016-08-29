package com.galaxyinternet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.BaseDAO;
import com.galaxyinternet.dao.UserDAO;
import com.galaxyinternet.model.User;
@Service
public class UserServiceImpl extends BaseServiceImpl<User>implements UserService
{
	@Autowired
	private UserDAO userDAO;

	@Override
	protected BaseDAO<User> getDao()
	{
		return userDAO;
	}
	
	

}
