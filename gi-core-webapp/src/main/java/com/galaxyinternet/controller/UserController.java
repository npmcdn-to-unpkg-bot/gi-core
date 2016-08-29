package com.galaxyinternet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.galaxyinternet.model.ResponseData;
import com.galaxyinternet.model.User;
import com.galaxyinternet.service.BaseService;
import com.galaxyinternet.service.UserService;
@Controller
@RequestMapping("/user")
public class UserController extends BaseControllerImpl<User>
{
	@Autowired
	private UserService userService;
	@Override
	protected String getPath()
	{
		return "user/";
	}

	@Override
	protected BaseService<User> getService()
	{
		return userService;
	}

	@Override
	@RequestMapping("/select")
	@ResponseBody
	public ResponseData<User> select(@RequestBody User entity)
	{
		return super.select(entity);
	}
	
	

}
