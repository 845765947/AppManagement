package com.smbms.dao;

import org.apache.ibatis.annotations.Param;

import com.smbms.pojo.Dev_user;

public interface UserMapper {
	/**
	 * 根据用户名查找User
	 */
	public Dev_user seleteUserName(@Param("devCode") String devCode);

	/**
	 * 获取
	 */
}
