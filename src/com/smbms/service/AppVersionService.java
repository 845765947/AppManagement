package com.smbms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.pojo.App_version;

public interface AppVersionService {

	/**
	 * 根据appinfoid放回App_version集合
	 */
	public List<App_version> selectInfoList(String appinfoid);

	/**
	 * appinfoid返回单个的App_version
	 */
	public App_version selectVersion(String appinfoid);

	/**
	 * 新增版本信息
	 */
	public boolean addversionsave(App_version app);

	/**
	 * 删除File文件缓存
	 */
	public boolean deleteFile(String appinfoid, int modifyBy);

	/**
	 * 修改版本信息
	 */
	public boolean appversionmodifysave(App_version vserion);

	/**
	 * 根据appinfo的id删除app的所有历史版本
	 */
	public boolean deleteAppVersion(String id);

}
