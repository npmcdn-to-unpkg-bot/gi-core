package com.galaxyinternet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.galaxyinternet.model.Resource;
import com.galaxyinternet.model.ResponseData;
import com.galaxyinternet.service.BaseService;
import com.galaxyinternet.service.ResourceService;

@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseControllerImpl<Resource>
{
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping("/getTree")
	@ResponseBody
	public List<Resource> getTree()
	{
		return resourceService.getResourceTree();
	}
	
	@Override
	@RequestMapping("/edit")
	@ResponseBody
	public ResponseData<Resource> edit(@RequestBody Resource entity)
	{
		return super.edit(entity);
	}
	
	@Override
	@RequestMapping("/add")
	@ResponseBody
	public ResponseData<Resource> add(@RequestBody Resource entity)
	{
		return super.add(entity);
	}



	@Override
	protected String getPath()
	{
		return "resource";
	}

	@Override
	protected BaseService<Resource> getService()
	{
		return resourceService;
	}
}
