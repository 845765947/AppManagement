package com.smbms.service;

import java.util.List;

import com.smbms.pojo.App_category;

public interface CategoryService {
	public List<App_category> selectOtpions(String categoryLevel1,
			String categoryLevel2);
}
