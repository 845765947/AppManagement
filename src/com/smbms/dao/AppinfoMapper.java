package com.smbms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.smbms.pojo.App_info;
import com.smbms.pojo.App_version;

public interface AppinfoMapper {
	/**
	 * 根据info APKName返回对象
	 */
	public App_info selectInfoCondition(@Param("apkName") String apkName,
			@Param("appinfoid") String appinfoid);

	/**
	 * 新增app信息对象
	 */
	public int insertInfo(App_info info);

	/**
	 * 更新app信息
	 */
	public int updateAppinfo(App_info info);

	/**
	 * 更新Appinfo的最新版本号
	 */
	public int updateAppVersion(@Param("versionId") String versionId,
			@Param("id") String id);

	/**
	 * 删除app基础信息及该app的所有历史版本
	 */
	public int deleteAppinfo(@Param("id") String id);

	/**
	 * appInfo状态的更改
	 */
	public int sale(@Param("appId") String appId,
			@Param("appstatus") String appstatus);

}
