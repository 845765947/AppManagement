package com.smbms.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.smbms.pojo.Backend_user;

public interface ManagerMapper {
	/**
	 * 根据用户名查找User
	 */
	public Backend_user seleteUserName(@Param("id") String id);

	/**
	 * 设置审核是否通过
	 */
	public int checksave(@Param("id") String id, @Param("status") String status);
}
