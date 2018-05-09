package com.smbms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	// 登入页面跳转
	@RequestMapping("/login")
	public String loginView() {
		return "devlogin";
	}
}
