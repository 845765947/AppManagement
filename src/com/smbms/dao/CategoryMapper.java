package com.smbms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.pojo.App_category;

public interface CategoryMapper {
	public List<App_category> selectOtpions(
			@Param("categoryLevel1") String categoryLevel1,
			@Param("categoryLevel2") String categoryLevel2);
}
