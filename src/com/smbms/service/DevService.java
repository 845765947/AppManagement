package com.smbms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.pojo.App_info;
import com.smbms.pojo.Data_Dictionary;
import com.smbms.pojo.Dev_user;

public interface DevService {
	/**
	 * 根据用户名查找User
	 */
	public Dev_user seleteUserName(String devCode, String pwd);

	/**
	 * 获取app信息列表
	 */
	public List<App_info> selectAppinfo(String querySoftwareName,
			String queryStatus, String queryFlatformId,
			String queryCategoryLevel1, String queryCategoryLevel2,
			String queryCategoryLevel3, Integer currentPageNo,
			Integer pagesize, long id);

	/**
	 * 获取app信息总数
	 */
	public int coucatAppinfo(String querySoftwareName, String queryStatus,
			String queryFlatformId, String queryCategoryLevel1,
			String queryCategoryLevel2, String queryCategoryLevel3);

	/**
	 * 根据pid获取分级列表
	 */
	public List<Data_Dictionary> selectGrading(String typeCode);
}
