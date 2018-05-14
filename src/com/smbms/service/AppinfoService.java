package com.smbms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.pojo.App_info;
import com.smbms.pojo.App_version;

public interface AppinfoService {
	/**
	 * 根据info APKName返回对象
	 */

	public App_info selectInfoCondition(String apkName, String appinfoid);

	/**
	 * 新增用户信息对象
	 */
	public boolean insertInfo(App_info info);

	/**
	 * 更新app信息
	 */
	public boolean updateAppinfo(App_info info);

	/**
	 * 更新Appinfo的最新版本号
	 */
	public boolean updateAppVersion(String versionId, String id);

	/**
	 * 删除app基础信息及该app的所有历史版本
	 */
	public boolean deleteAppinfo(String id);

	/**
	 * appInfo状态的更改
	 */
	public boolean sale(String appId, String appstatus);

}
