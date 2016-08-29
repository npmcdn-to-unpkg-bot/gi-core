package com.galaxyinternet.service;

import java.util.List;

import com.galaxyinternet.model.Resource;

public interface ResourceService extends BaseService<Resource>
{
	public List<Resource> getUserPermission(Long userid);
	public List<Resource> getResourceTree();
	public List<Resource> getMenuTree();
}
