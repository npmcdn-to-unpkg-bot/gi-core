package com.galaxyinternet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.BaseDAO;
import com.galaxyinternet.dao.PermissionDAO;
import com.galaxyinternet.model.Permission;
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission>implements PermissionService
{
	@Autowired
	private PermissionDAO permissionDAO;
	@Override
	protected BaseDAO<Permission> getDao()
	{
		return permissionDAO;
	}

	

}
