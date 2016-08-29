package com.galaxyinternet.bo;

import java.util.List;

import com.galaxyinternet.model.UserRole;

public class UserRoleBO extends UserRole
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Long> userIds;
	private List<Long> roleIds;
	public List<Long> getUserIds()
	{
		return userIds;
	}
	public void setUserIds(List<Long> userIds)
	{
		this.userIds = userIds;
	}
	public List<Long> getRoleIds()
	{
		return roleIds;
	}
	public void setRoleIds(List<Long> roleIds)
	{
		this.roleIds = roleIds;
	}
	
}
