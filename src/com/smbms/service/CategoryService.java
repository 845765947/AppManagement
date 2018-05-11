package com.smbms.service;

import java.util.List;

import com.smbms.pojo.App_category;

public interface CategoryService {
	// 获得可获得一二三级分类列表
	public List<App_category> selectOtpions(String categoryLevel1,
			String categoryLevel2);
}
