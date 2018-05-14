package com.smbms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smbms.dao.ManagerMapper;
import com.smbms.pojo.App_category;
import com.smbms.pojo.App_info;
import com.smbms.pojo.App_version;
import com.smbms.pojo.Backend_user;
import com.smbms.pojo.Data_Dictionary;
import com.smbms.pojo.Dev_user;
import com.smbms.service.AppVersionService;
import com.smbms.service.AppinfoService;
import com.smbms.service.CategoryService;
import com.smbms.service.DevService;
import com.smbms.service.ManagerService;
import com.smbms.tools.Constants;
import com.smbms.tools.PageSupport;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private AppVersionService vsService;

	@Resource
	private AppinfoService infoService;

	@Resource
	private DevService devService;

	@Resource
	private CategoryService categoryService;

	@Resource
	private ManagerService managerService;

	/**
	 * 登录页面跳转
	 */
	@RequestMapping("/login")
	public String login() {
		return "backendlogin";
	}

	// 登录方法
	@RequestMapping("/dologin")
	public String login(@RequestParam("userCode") String devCode,
			@RequestParam("userPassword") String pwd, HttpSession session,
			HttpServletRequest request) {
		logger.info("进入登录方法===============================");
		Backend_user user = new Backend_user();
		user = managerService.seleteUserName(devCode, pwd);
		if (user != null) {
			session.setAttribute(Constants.USER_SESSION, user);
			session.setAttribute("userSession", user);
			return "redirect:/manager/main";
		} else {
			request.setAttribute("error", "用户名或密码错误");
			return "backendlogin";
		}
	}

	// 主页面跳转
	@RequestMapping("/main")
	public String mandView() {
		return "/backend/main";
	}

	// 注销
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("注销===============================");
		session.removeAttribute(Constants.USER_SESSION);
		return "../../index";
	}

	// App信息审核
	@RequestMapping("/flatform/app/list")
	public String appMessage(
			@RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
			@RequestParam(value = "queryFlatformId", required = false) String queryFlatformId,
			@RequestParam(value = "queryCategoryLevel1", required = false) String queryCategoryLevel1,
			@RequestParam(value = "queryCategoryLevel2", required = false) String queryCategoryLevel2,
			@RequestParam(value = "queryCategoryLevel3", required = false) String queryCategoryLevel3,
			@RequestParam(value = "pageIndex", required = false) Integer currentPageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			HttpSession session, Model model) {
		logger.info("根据条件分页查询=================================");
		// 设置页面大小
		pageSize = Constants.pageSize;
		// 页面
		PageSupport page = new PageSupport();
		// 设置页面显示大小
		page.setPageSize(pageSize);
		// 设置总数,1为待审核
		page.setTotalCount(devService.coucatAppinfo(querySoftwareName, "1",
				queryFlatformId, queryCategoryLevel1, queryCategoryLevel2,
				queryCategoryLevel3));
		// 设置当前页面

		if (currentPageNo != null && currentPageNo > 0) {
			currentPageNo = currentPageNo.intValue();
			page.setCurrentPageNo(currentPageNo);
		} else {
			currentPageNo = 1;
			page.setCurrentPageNo(currentPageNo);
		}

		// 设置集合
		List<App_info> list = new ArrayList<App_info>();

		list = devService.selectAppinfo(querySoftwareName, "1",
				queryFlatformId, queryCategoryLevel1, queryCategoryLevel2,
				queryCategoryLevel3, (currentPageNo - 1) * pageSize, pageSize,
				0);
		/**
		 * 设置相应参数
		 */
		model.addAttribute("pages", page);
		model.addAttribute("appInfoList", list);
		model.addAttribute("querySoftwareName", querySoftwareName);
		model.addAttribute("queryFlatformId", queryFlatformId);
		model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel3);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel3);

		logger.info("获取各种下拉框集合===============================");
		// 设置状态集合
		List<Data_Dictionary> statusList = new ArrayList<Data_Dictionary>();
		// 设置所属平台集合
		List<Data_Dictionary> flatFormList = new ArrayList<Data_Dictionary>();
		// 获取状态集合
		statusList = devService.selectGrading(Constants.APP_STATUS);
		// 获取所属平台集合
		flatFormList = devService.selectGrading(Constants.APP_FLATFORM);
		// 添加到Model中
		model.addAttribute("statusList", statusList);
		model.addAttribute("flatFormList", flatFormList);
		// 三级分类中的一级分类
		List<App_category> categoryLevel1List = new ArrayList<App_category>();
		// 获得一级分类
		categoryLevel1List = categoryService.selectOtpions(null, null);
		model.addAttribute("categoryLevel1List", categoryLevel1List);
		return "/backend/applist";
	}

	/**
	 * 查看信息方法
	 */
	@RequestMapping("/check")
	public String check(@RequestParam("aid") String aid,
			@RequestParam("vid") String vid, Model model) {
		App_info info = new App_info();
		info = infoService.selectInfoCondition(null, aid);
		System.out.println(info.getCategoryLevel1Name());
		model.addAttribute("appInfo", info);
		App_version version = new App_version();
		version = vsService.selectVersion(vid);
		model.addAttribute("appVersion", version);
		return "/backend/appcheck";
	}

	/**
	 * 审核
	 */
	@RequestMapping("/checksave")
	public String checksave(@RequestParam("id") String id,
			@RequestParam("status") String status) {
		boolean fig = managerService.checksave(id, status);
		if (fig) {
			return "redirect:/manager/flatform/app/list";
		} else {
			return "/backend/appcheck";
		}
	}

}
