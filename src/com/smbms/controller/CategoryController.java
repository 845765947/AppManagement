package com.smbms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smbms.pojo.App_category;
import com.smbms.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Resource
	private CategoryService service;

	@RequestMapping("/categorylevellist")
	@ResponseBody
	public List<App_category> categorylevellist(
			@RequestParam(value = "categoryLevel1", required = false) String categoryLevel1,
			@RequestParam(value = "categoryLevel2", required = false) String categoryLevel2) {
		List<App_category> categoryLevel1List = new ArrayList<App_category>();
		// 获取三级分类菜单
		categoryLevel1List = service.selectOtpions(categoryLevel1,
				categoryLevel2);
		return categoryLevel1List;
	}
}
	