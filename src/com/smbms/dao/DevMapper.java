package com.smbms.dao;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.apache.ibatis.annotations.Param;

import com.smbms.pojo.App_info;
import com.smbms.pojo.Data_Dictionary;
import com.smbms.pojo.Dev_user;

public interface DevMapper {
	/**
	 * 根据用户名查找User
	 */
	public Dev_user seleteUserName(@Param("devCode") String devCode);

	/**
	 * 获取app信息列表
	 */
	public List<App_info> selectAppinfo(
			@Param("querySoftwareName") String querySoftwareName,
			@Param("queryStatus") String queryStatus,
			@Param("queryFlatformId") String queryFlatformId,
			@Param("queryCategoryLevel1") String queryCategoryLevel1,
			@Param("queryCategoryLevel2") String queryCategoryLevel2,
			@Param("queryCategoryLevel3") String queryCategoryLevel3,
			@Param("currentPageNo") Integer currentPageNo,
			@Param("pageSize") Integer pageSize, @Param("id") long id);

	/**
	 * 获取app信息总数
	 */
	public int coucatAppinfo(
			@Param("querySoftwareName") String querySoftwareName,
			@Param("queryStatus") String queryStatus,
			@Param("queryFlatformId") String queryFlatformId,
			@Param("queryCategoryLevel1") String queryCategoryLevel1,
			@Param("queryCategoryLevel2") String queryCategoryLevel2,
			@Param("queryCategoryLevel3") String queryCategoryLevel3);

	/**
	 * 根据typeCode获取分级列表
	 */
	public List<Data_Dictionary> selectGrading(
			@Param("typeCode") String typeCode);
}
