package com.smbms.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smbms.dao.CategoryMapper;
import com.smbms.pojo.App_category;
import com.smbms.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Resource
	private CategoryMapper categorydao;

	@Override
	public List<App_category> selectOtpions(String categoryLevel1,
			String categoryLevel2) {
		List<App_category> list = new ArrayList<App_category>();
		list = categorydao.selectOtpions(categoryLevel1, categoryLevel2);
		return list;
	}
}
