package com.smbms.dao;

import org.apache.ibatis.annotations.Param;

import com.smbms.pojo.App_info;

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

}
