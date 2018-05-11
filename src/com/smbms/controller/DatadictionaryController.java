package com.smbms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smbms.pojo.App_category;
import com.smbms.pojo.Data_Dictionary;
import com.smbms.service.CategoryService;
import com.smbms.service.DevService;
import com.smbms.tools.Constants;

@Controller
@RequestMapping("/Datadictionary")
public class DatadictionaryController {

	@Resource
	private DevService userservice;

	@Resource
	private CategoryService categoryService;

	// 获取所属平台列表
	@RequestMapping("/datadictionarylist")
	@ResponseBody
	public List<Data_Dictionary> datadictionarylist() {
		List<Data_Dictionary> list = new ArrayList<Data_Dictionary>();
		// 获取用户所属平台
		list = userservice.selectGrading(Constants.APP_FLATFORM);
		return list;
	}

	@RequestMapping("/categorylevellist")
	@ResponseBody
	public List<App_category> categorylevellist() {
		List<App_category> list = new ArrayList<App_category>();
		// 获得一级分类
		list = categoryService.selectOtpions(null, null);
		return list;
	}
}
