package com.smbms.service;


import com.smbms.pojo.Dev_user;

public interface UserService {
	/**
	 * 根据用户名查找User
	 */
	public Dev_user seleteUserName(String devCode,String pwd);
}
