package com.smbms.dao;

import org.springframework.web.bind.annotation.RequestParam;

import com.smbms.pojo.App_info;

public interface AppinfoMapper {
	/**
	 * 根据info APKName返回对象
	 */

	public App_info selectInfoApkName(@RequestParam("apkName") String apkName);

	/**
	 * 新增用户信息对象
	 */
	public int insertInfo(App_info info);

}
