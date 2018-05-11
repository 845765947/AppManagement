package com.smbms.service;

import com.smbms.pojo.App_info;

public interface AppinfoService {
	/**
	 * 根据info APKName返回对象
	 */

	public App_info selectInfoApkName(String apkName);

	/**
	 * 新增用户信息对象
	 */
	public boolean insertInfo(App_info info);
}