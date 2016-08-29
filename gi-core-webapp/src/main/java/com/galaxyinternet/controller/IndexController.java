package com.galaxyinternet.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.galaxyinternet.model.Resource;
import com.galaxyinternet.model.ResponseData;
import com.galaxyinternet.service.ResourceService;

@Controller
@RequestMapping("/index")
public class IndexController
{
	final Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private ResourceService resourceService;
	@RequestMapping
	public String showIndex()
	{
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/getMenus")
	public ResponseData<Resource> getMenus()
	{
		logger.info("++++++++++++++++++++getMenus()++++++++++++++++++++++++");
		ResponseData<Resource> data = new ResponseData<Resource>();
		List<Resource> list = resourceService.getMenuTree();
		data.setList(list);
		return data;
	}
	
	
}
