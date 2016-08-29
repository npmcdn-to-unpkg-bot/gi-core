package com.galaxyinternet.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.galaxyinternet.cache.Cache;
import com.galaxyinternet.model.User;
import com.galaxyinternet.service.UserService;
import com.galaxyinternet.sso.SSOToken;
import com.galaxyinternet.util.Base64Utils;
import com.galaxyinternet.util.Constants;
import com.galaxyinternet.util.RSAUtils;

@Controller
@RequestMapping("/login")
public class LoginController
{
	@Autowired
	private UserService userService;
	@RequestMapping
	public ModelAndView showLogin(HttpServletRequest request) throws Exception
	{
		ModelAndView mv = new ModelAndView("login");
		RSAUtils.RASKey key = RSAUtils.getKey();
		mv.addObject("publicKey", Base64Utils.encode(key.getPublicKey().getEncoded()));
		HttpSession session = request.getSession();
		session.setAttribute("privateKey", Base64Utils.encode(key.getPrivateKey().getEncoded()));
		return mv;
	}
	@RequestMapping("/auth")
	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String gotoUrl = request.getParameter("gotoUrl");
		
		try
		{
			if(username != null && password != null)
			{
				User bo = new User();
				bo.setUserLogin(username);
				List<User> users = userService.select(bo);
				if(users != null && users.size() >0)
				{
					HttpSession session = request.getSession();
					String privateKey = (String)session.getAttribute("privateKey");
					byte[] decodedData = RSAUtils.decryptByPrivateKey(Base64.decodeBase64(password.getBytes("UTF-8")), privateKey);  
			        password = new String(decodedData);  
					User user = users.iterator().next();
					if(password.equals(user.getPassword()))
					{
						session.setAttribute(Constants.SESSION_ATTR_NAME_USER_LOGIN, user);
						if(gotoUrl == null)
						{
							gotoUrl = "../index";
						}
						response.sendRedirect(gotoUrl);
						return;
					}
				}
			}
			response.sendRedirect("../login");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return;
	}
}
