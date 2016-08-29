package com.galaxyinternet.bo;

import java.util.List;

import com.galaxyinternet.model.Permission;

public class PermissionBO extends Permission
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Long> roleIds;

	public List<Long> getRoleIds()
	{
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds)
	{
		this.roleIds = roleIds;
	}
	
}
