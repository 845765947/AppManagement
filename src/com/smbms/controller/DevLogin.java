package com.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smbms.pojo.Dev_user;
import com.smbms.service.DevService;
import com.smbms.tools.Constants;

@Controller
@RequestMapping("/login")
public class DevLogin {
	Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private DevService devService;

	// 注销
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("注销===============================");
		session.removeAttribute(Constants.USER_SESSION);
		return "../../index";
	}

	// 登入页面跳转
	@RequestMapping("/loginView")
	public String loginView() {
		return "devlogin";
	}

	// 登录方法
	@RequestMapping("/dologin")
	public String login(@RequestParam("devCode") String devCode,
			@RequestParam("devPassword") String pwd, HttpSession session) {
		logger.info("进入登录方法===============================");
		Dev_user user = new Dev_user();
		user = devService.seleteUserName(devCode, pwd);
		if (user != null) {
			session.setAttribute(Constants.USER_SESSION, user);
			session.setAttribute("devUserSession", user);
			return "redirect:/dev/sys/main";
		}
		throw new RuntimeException("用户名或密码错误");
	}

}
