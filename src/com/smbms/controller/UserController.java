package com.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smbms.pojo.Dev_user;
import com.smbms.service.UserService;
import com.smbms.tools.Constants;

@Controller
@RequestMapping("/dev")
public class UserController {
	Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private UserService userservice;

	// 登入页面跳转
	@RequestMapping("/loginView")
	public String loginView() {
		return "devlogin";
	}

	// 登录方法
	@RequestMapping("/dologin")
	public String login(@RequestParam("devCode") String devCode,
			@RequestParam("devPassword") String pwd, HttpSession session) {
		Dev_user user = new Dev_user();
		user = userservice.seleteUserName(devCode, pwd);
		if (user.getDevName() != null) {
			session.setAttribute(Constants.USER_SESSION, user);
			session.setAttribute("devUserSession", user);
			return "redirect:/dev/sys/main";
		}
		throw new RuntimeException("用户名或密码错误");
	}
 
	// 主页面跳转
	@RequestMapping("/sys/main")
	public String mandView() {
		return "/developer/main";
	}

	// 注销
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(Constants.USER_SESSION);
		return "../index";
	}
}
