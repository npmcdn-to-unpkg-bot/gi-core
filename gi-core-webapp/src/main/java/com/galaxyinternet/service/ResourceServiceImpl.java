package com.galaxyinternet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.bo.PermissionBO;
import com.galaxyinternet.bo.ResourceBO;
import com.galaxyinternet.bo.UserRoleBO;
import com.galaxyinternet.dao.BaseDAO;
import com.galaxyinternet.dao.PermissionDAO;
import com.galaxyinternet.dao.ResourceDAO;
import com.galaxyinternet.dao.UserRoleDAO;
import com.galaxyinternet.model.Permission;
import com.galaxyinternet.model.Resource;
import com.galaxyinternet.model.UserRole;

@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService
{
	@Autowired
	private ResourceDAO resourceDAO;
	@Autowired
	private UserRoleDAO userRoleDAO;
	@Autowired
	private PermissionDAO permissionDAO;
	@Override
	protected BaseDAO<Resource> getDao()
	{
		return resourceDAO;
	}
	public List<Resource> getUserPermission(Long userId)
	{
		List<Resource> userPermission = null;
		UserRoleBO urQuery = new UserRoleBO();
		urQuery.setUserId(userId);
		List<UserRole> urList = userRoleDAO.select(urQuery);
		if(urList != null && urList.size() > 0)
		{
			List<Long> roleIds = new ArrayList<Long>();
			for(UserRole ur : urList)
			{
				if(ur.getRoleId() != null && !roleIds.contains(ur.getRoleId()))
				{
					roleIds.add(ur.getRoleId());
				}
			}
			if(roleIds.size() > 0)
			{
				PermissionBO pQuery = new PermissionBO();
				pQuery.setRoleIds(roleIds);
				List<Permission> pList = permissionDAO.select(pQuery);
				if(pList != null && pList.size() > 0)
				{
					List<Long> rIds = new ArrayList<Long>();
					for(Permission p : pList)
					{
						if(p.getResourceId() != null && !rIds.contains(p.getResourceId()))
						{
							rIds.add(p.getResourceId());
						}
					}
					if(rIds.size() > 0)
					{
						ResourceBO rQuery = new ResourceBO();
						rQuery.setIds(rIds);;
						userPermission = select(rQuery);
					}
				}
			}
		}
		
		return userPermission;
	}
	public List<Resource> getResourceTree()
	{
		ResourceBO query = new ResourceBO();
		query.setParentId(0L);
		List<Resource> list = resourceDAO.select(query);
		if(list != null && list.size() >0)
		{
			for(Resource child : list)
			{
				setChildren(child,null);
			}
		}
		return list;
	}
	
	private void setChildren(Resource node, String resourceType)
	{
		if(node.getId() != null)
		{
			ResourceBO query = new ResourceBO();
			query.setParentId(node.getId());
			query.setResourceType(resourceType);
			List<Resource> children = resourceDAO.select(query);
			if(children != null && children.size() >0)
			{
				for(Resource child : children)
				{
					setChildren(child,resourceType);
				}
				node.setChildren(children);
			}
		}
	}
	public List<Resource> getMenuTree()
	{
		ResourceBO query = new ResourceBO();
		query.setParentId(0L);
		query.setResourceType("resourceType:1");
		List<Resource> list = resourceDAO.select(query);
		if(list != null && list.size() >0)
		{
			for(Resource child : list)
			{
				setChildren(child,"resourceType:1");
			}
		}
		return list;
	}
	
	
	

}
