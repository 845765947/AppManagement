package com.smbms.service;

import org.apache.ibatis.annotations.Param;

import com.smbms.pojo.Backend_user;

public interface ManagerService {
	/**
	 * 根据用户名查找User
	 */
	public Backend_user seleteUserName(String id, String pwd);

	/**
	 * 设置审核是否通过
	 */
	public boolean checksave(String id, String status);
}
