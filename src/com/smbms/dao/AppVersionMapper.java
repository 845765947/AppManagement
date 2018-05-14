package com.smbms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.pojo.App_version;

public interface AppVersionMapper {

	/**
	 * 根据appinfoid放回版本信息中的App_version集合
	 */
	public List<App_version> selectInfoList(@Param("appinfoid") String appinfoid);

	/**
	 * appinfoid返回单个的App_version
	 */
	public App_version selectVersion(@Param("appinfoid") String appinfoid);

	/**
	 * 新增版本信息
	 */
	public int addversionsave(App_version app);

	/**
	 * 修改版本信息
	 */
	public int appversionmodifysave(App_version vserion);

	/**
	 * 删除File文件缓存
	 */
	public int deleteFile(@Param("appinfoid") String appinfoid,
			@Param("modifyBy") int modifyBy);

	/**
	 * 根据appinfo的id删除app的所有历史版本
	 */
	public int deleteAppVersion(@Param("id") String id);
}
