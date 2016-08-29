package com.galaxyinternet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.BaseDAO;
import com.galaxyinternet.dao.RoleDAO;
import com.galaxyinternet.model.Role;
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role>implements RoleService
{
	@Autowired
	private RoleDAO roleDAO;
	@Override
	protected BaseDAO<Role> getDao()
	{
		return roleDAO;
	}

	

}
