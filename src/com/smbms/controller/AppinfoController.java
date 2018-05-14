package com.smbms.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.StringUtils;
import com.smbms.pojo.App_info;
import com.smbms.pojo.App_version;
import com.smbms.pojo.Dev_user;
import com.smbms.service.AppVersionService;
import com.smbms.service.AppinfoService;
import com.smbms.tools.Constants;

@Controller
@RequestMapping("/appinfo")
public class AppinfoController {
	Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private AppinfoService infoService;

	@Resource
	private AppVersionService vsService;

	@RequestMapping("/apkexist")
	@ResponseBody
	public Object selectInfoApkName(
			@RequestParam(value = "APKName", required = false) String apkName) {
		System.out.println(apkName);
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtils.isNullOrEmpty(apkName)) {
			App_info info = new App_info();
			info = infoService.selectInfoCondition(apkName, null);
			if (info != null) {
				map.put("APKName", "exist");
			} else {
				map.put("APKName", "noexist");
			}
		} else {
			map.put("APKName", "empty");
		}
		return map;
	}

	// 添加信息
	@RequestMapping("/appinfoaddsave")
	public String appinfoaddsave(App_info info,
			@RequestParam(value = "a_logoPicPath") MultipartFile multipartFile,
			HttpServletRequest request, HttpSession session) {
		// 设置文件名
		String fileName = null;
		// LOGO图片url路径
		String logoPicPath = null;
		// LOGO图片的服务器存储路径
		String logoLocPath = null;
		String path = request.getSession().getServletContext()
				.getRealPath("statics" + File.separator + "uploadfiles");
		logger.info("uploadFile path==========================>" + path);
		if (!multipartFile.isEmpty()) {
			String oldFileName = multipartFile.getOriginalFilename();// 原文件名
			logger.info("uploadFile oldFileName=============================>"
					+ oldFileName);
			String perfix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
			logger.debug("uploadFile prefix==========================?>"
					+ perfix);
			int filesize = 500000;
			logger.debug("uploadFIle size=================================>"
					+ multipartFile.getSize());
			if (multipartFile.getSize() > filesize) {
				request.setAttribute("fileUploadError", "上传的文件大小不能超过500KB");
				return "forward:/appinfo/appinfoaddsave";
			} else if (perfix.equalsIgnoreCase("jpg")
					|| perfix.equalsIgnoreCase("png")
					|| perfix.equalsIgnoreCase("jpeg")
					|| perfix.equalsIgnoreCase("pneg")) {
				fileName = System.currentTimeMillis()
						+ RandomUtils.nextInt(1000000) + "_Personal.jpg";
				logger.debug("new fileName==========="
						+ multipartFile.getName());
				File targetFile = new File(path, fileName);
				// 如果没有当前路径，则创建一个，包括子包
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					multipartFile.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("fileUploadError", "*上传失败!");
					return "forward:/appinfo/appinfoaddsave";
				}
			} else {
				request.setAttribute("fileUploadError", "*上传图片格式不正确!");
				return "forward:/appinfo/appinfoaddsave";
			}

			// LOGO图片url路径
			logoPicPath = fileName;
			// LOGO图片的服务器存储路径
			logoLocPath = path + File.separator + fileName;
			// 设置logoLocPath
			info.setLogoLocPath(logoLocPath);
			// 设置logopicPath
			info.setLogoPicPath(request.getContextPath()
					+ "/statics/uploadfiles/" + fileName);
			// 设置创建者id
			info.setCreatedBy(((Dev_user) session
					.getAttribute(Constants.USER_SESSION)).getId());
			// 设置新增时间
			info.setCreationDate(new Date());
			// 设置开发者id
			info.setDevId(((Dev_user) session
					.getAttribute(Constants.USER_SESSION)).getId());
			// 文件上传结束，开始新增
			boolean fig = infoService.insertInfo(info);
		}
		return "redirect:/dev/sys/flatform/app/list";
	}

	/**
	 * app信息修改页面跳转
	 */
	@RequestMapping("/appversionmodify/{appinfoid}")
	public String appinfomodifyView(@PathVariable String appinfoid, Model model) {
		App_info info = new App_info();
		info = infoService.selectInfoCondition(null, appinfoid);
		model.addAttribute("appInfo", info);
		return "/developer/appinfomodify";
	}

	/**
	 * 删除缓存中的文件
	 */
	@RequestMapping("/delfile")
	@ResponseBody
	public Object delfile(String id) {
		Map<String, String> map = new HashMap<String, String>();
		App_info info = new App_info();
		info = infoService.selectInfoCondition(null, id);
		// 删除文件
		File fileid = new File(info.getLogoLocPath());
		if (fileid.exists()) {
			fileid.delete();
			// 删除文件成功后设置为空
			info.setLogoLocPath("");
			info.setLogoPicPath("");
			infoService.updateAppinfo(info);
			logger.info("删除缓存文件成功==================");
			map.put("result", "success");
		} else {
			map.put("result", "failed");
			logger.info("删除缓存文件失败==================");
		}
		return map;
	}

	/**
	 * 更新app信息
	 */
	@RequestMapping("/appinfomodifysave")
	public String appinfomodifysave(App_info info, Model model,
			@RequestParam(value = "attach") MultipartFile multipartFile,
			HttpServletRequest request, HttpSession session) {

		// 设置文件名
		String fileName = null;
		// LOGO图片url路径
		String logoPicPath = null;
		// LOGO图片的服务器存储路径
		String logoLocPath = null;
		String path = request.getSession().getServletContext()
				.getRealPath("statics" + File.separator + "uploadfiles");
		logger.info("uploadFile path==========================>" + path);
		if (!multipartFile.isEmpty()) {
			String oldFileName = multipartFile.getOriginalFilename();// 原文件名
			logger.info("uploadFile oldFileName=============================>"
					+ oldFileName);
			String perfix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
			logger.debug("uploadFile prefix==========================?>"
					+ perfix);
			int filesize = 5000000;
			logger.debug("uploadFIle size=================================>"
					+ multipartFile.getSize());
			if (multipartFile.getSize() > filesize) {
				request.setAttribute("fileUploadError", "上传的文件大小不能超过500KB");
				return "forward:/appinfo/appversionmodify/" + info.getId();
			} else if (perfix.equalsIgnoreCase("jpg")
					|| perfix.equalsIgnoreCase("png")
					|| perfix.equalsIgnoreCase("jpeg")
					|| perfix.equalsIgnoreCase("pneg")) {
				fileName = System.currentTimeMillis()
						+ RandomUtils.nextInt(1000000) + "_Personal.jpg";
				logger.debug("new fileName==========="
						+ multipartFile.getName());
				File targetFile = new File(path, fileName);
				// 如果没有当前路径，则创建一个，包括子包
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					multipartFile.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("fileUploadError", "*上传失败!");
					return "forward:/appinfo/appversionmodify/" + info.getId();
				}
			} else {
				request.setAttribute("fileUploadError", "*上传图片格式不正确!");
				return "forward:/appinfo/appversionmodify/" + info.getId();
			}
		}

		// LOGO图片url路径
		logoPicPath = fileName;
		// LOGO图片的服务器存储路径
		logoLocPath = path + File.separator + fileName;
		// 设置logoLocPath
		info.setLogoLocPath(logoLocPath);
		// 设置logopicPath
		info.setLogoPicPath(request.getContextPath() + "/statics/uploadfiles/"
				+ fileName);
		// 设置更新者以及时间
		info.setModifyBy(((Dev_user) session
				.getAttribute(Constants.USER_SESSION)).getId());
		info.setModifyDate(new Date());
		// 更新操作
		boolean fig = infoService.updateAppinfo(info);
		return "redirect:/dev/sys/flatform/app/list";
	}

	/**
	 * 删除App基础信息，以及该app的所有历史版本
	 */
	@RequestMapping("/delapp")
	@ResponseBody
	public Object delapp(@RequestParam("id") String id) {
		Map<String, String> map = new HashMap<String, String>();
		boolean figVersion = vsService.deleteAppVersion(id);
		if (figVersion) {
			// 获取所有的版本信息，并且删除apk缓存文件
			List<App_version> versionList = new ArrayList<App_version>();
			versionList = vsService.selectInfoList(id);
			for (int a = 0; a < versionList.size(); a++) {
				File file = new File(versionList.get(a).getApkLocPath());
				file.delete();
			}
			boolean figInfo = infoService.deleteAppinfo(id);
			if (figInfo) {
				map.put("delResult", "true");
			} else {
				map.put("delResult", "false");
			}
		} else {
			map.put("delResult", "false");
		}
		return map;
	}

	/**
	 * 上架和下架
	 */
	@RequestMapping("/sale")
	@ResponseBody
	public Object sale(@RequestParam("appId") String appId,
			@RequestParam("appstatus") String appstatus) {
		Map<String, String> map = new HashMap<String, String>();
		// 默认为没有异常
		map.put("errorCode", "0");
		try {
			boolean fig = infoService.sale(appId, appstatus);
			if (fig) {
				map.put("resultMsg", "success");
				if (appstatus.equals(4)) {
					map.put("saleSwitch", "open");
				} else if (appstatus.equals(5)) {
					map.put("saleSwitch", "close");
				}
			}
		} catch (Exception e) {
			map.put("errorCode", "1");
			e.printStackTrace();
		}
		return map;
	}
}
