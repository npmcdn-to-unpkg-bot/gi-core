package com.galaxyinternet.bo;

import java.util.List;

import com.galaxyinternet.model.Resource;

public class ResourceBO extends Resource
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Long> ids;

	public List<Long> getIds()
	{
		return ids;
	}

	public void setIds(List<Long> ids)
	{
		this.ids = ids;
	}
}
