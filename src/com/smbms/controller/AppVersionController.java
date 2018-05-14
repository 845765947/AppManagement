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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.smbms.pojo.App_info;
import com.smbms.pojo.App_version;
import com.smbms.pojo.Dev_user;
import com.smbms.service.AppVersionService;
import com.smbms.service.AppinfoService;
import com.smbms.tools.Constants;

@Controller
@RequestMapping("/appVersion")
public class AppVersionController {
	@Resource
	private AppVersionService vsService;

	@Resource
	private AppinfoService infoService;

	Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 新增版本信息跳转
	 */
	@RequestMapping("/appversionadd/{appinfoid}/{APKName}")
	public String appversionaddView(@PathVariable String appinfoid,
			@PathVariable String APKName, Model model) {
		logger.info("addVersionadd========================================");
		List<App_version> list = new ArrayList<App_version>();
		list = vsService.selectInfoList(appinfoid);
		model.addAttribute("appVersionList", list);
		// 防止为空值时类型转换错误
		model.addAttribute("appVersion", appinfoid);
		model.addAttribute("APKName", APKName);
		return "/developer/appversionadd";
	}

	@RequestMapping("/addversionsave")
	public String addversionsave(
			App_version version,
			@RequestParam(value = "a_downloadLink", required = false) MultipartFile multipartFile,
			HttpServletRequest request, HttpSession session) {

		// 设置文件名
		String fileName = version.getAPKName() + "-V" + version.getVersionNo()
				+ ".apk";
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
			int filesize = 50000000;
			logger.debug("uploadFIle size=================================>"
					+ multipartFile.getSize());

			if (multipartFile.getSize() > filesize) {
				request.setAttribute("fileUploadError", "上传的文件大小不能超过50M");
				return "forward:/appVersion/addversionsave";

			} else if (perfix.equalsIgnoreCase("apk")) {
				logger.debug("new fileName===========" + fileName);
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
					return "forward:/appVersion/addversionsave";
				}
			} else {
				request.setAttribute("fileUploadError", "*上传APK格式不正确!");
				return "/developer/appversionadd";
			}
		}

		// 开始设置各种信息
		// 设置缓存中的下载链接
		version.setDownloadLink(request.getContextPath()
				+ "/statics/uploadfiles/" + fileName);
		// APk的服务器存储路径
		logoLocPath = path + File.separator + fileName;
		version.setApkFileName(fileName);
		version.setApkLocPath(logoLocPath);
		version.setCreatedBy(((Dev_user) session
				.getAttribute(Constants.USER_SESSION)).getId());
		version.setCreationDate(new Date());
		version.setVersionNo("v" + version.getVersionNo());
		boolean fig = vsService.addversionsave(version);

		if (fig) {
			boolean versionFig = infoService.updateAppVersion(
					Long.toString(version.getId()),
					Long.toString(version.getAppId()));
			if (versionFig) {
				return "redirect:/dev/sys/flatform/app/list";
			}
		}
		return "/developer/appversionadd";
	}

	/**
	 * 修改app版本页面跳转
	 */
	@RequestMapping("/appversionmodify/{appinfoid}/{versionId}")
	public String appversionmodify(@PathVariable String appinfoid,
			@PathVariable String versionId, Model model) {
		// 历史版本查询
		List<App_version> list = new ArrayList<App_version>();
		list = vsService.selectInfoList(appinfoid);

		model.addAttribute("appVersionList", list);
		// 防止为空值时类型转换错误
		model.addAttribute("appVersion", appinfoid);
		// 获取version信息
		App_version version = new App_version();
		version = vsService.selectVersion(versionId);
		model.addAttribute("appVersion", version);
		return "/developer/appversionmodify";
	}

	/**
	 * 删除APK文件
	 */
	@RequestMapping("/delfile")
	@ResponseBody
	public Object delfile(@RequestParam("id") String id, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		int modifyBy = (int) ((Dev_user) session
				.getAttribute(Constants.USER_SESSION)).getId();
		App_version version = new App_version();
		version = vsService.selectVersion(id);
		boolean fig = vsService.deleteFile(id, modifyBy);
		if (fig) {
			File file = new File(version.getApkLocPath());
			file.delete();
			map.put("result", "success");
		} else {
			map.put("result", "failed");
		}
		return map;
	}

	/**
	 * 更新appVersion
	 */
	@RequestMapping("/appversionmodifysave")
	public String appversionmodifysave(
			App_version version,
			@RequestParam(value = "attach", required = false) MultipartFile multipartFile,
			HttpServletRequest request, HttpSession session) {
		// 设置文件名
		String fileName = version.getAPKName() + "-V" + version.getVersionNo()
				+ ".apk";
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
			int filesize = 50000000;
			logger.debug("uploadFIle size=================================>"
					+ multipartFile.getSize());

			if (multipartFile.getSize() > filesize) {
				request.setAttribute("fileUploadError", "上传的文件大小不能超过50M");
				return "/developer/appversionmodify";

			} else if (perfix.equalsIgnoreCase("apk")) {
				logger.debug("new fileName===========" + fileName);
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
					return "/developer/appversionmodify";
				}
			} else {
				request.setAttribute("fileUploadError", "*上传APK格式不正确!");
				return "/developer/appversionmodify";
			}
		}
		logger.info("appversionmodifysave========================");
		// 开始设置各种信息
		// 设置缓存中的下载链接
		version.setDownloadLink(request.getContextPath()
				+ "/statics/uploadfiles/" + fileName);
		// APk的服务器存储路径
		logoLocPath = path + File.separator + fileName;
		version.setApkFileName(fileName);
		version.setApkLocPath(logoLocPath);
		version.setModifyBy(((Dev_user) session
				.getAttribute(Constants.USER_SESSION)).getId());
		version.setModifyDate(new Date());

		boolean fig = vsService.appversionmodifysave(version);
		if (fig) {
			return "redirect:/dev/sys/flatform/app/list";
		} else {
			request.setAttribute("fileUploadError", "修改失败");
			return "/developer/appversionmodify";
		}
	}

	/**
	 * 查看app信息页面跳转
	 */
	@RequestMapping("/appview/{appinfoid}")
	public String appview(@PathVariable String appinfoid, Model model) {
		App_info info = new App_info();
		info = infoService.selectInfoCondition(null, appinfoid);
		model.addAttribute("appInfo", info);
		List<App_version> list = new ArrayList<App_version>();
		list = vsService.selectInfoList(appinfoid);
		model.addAttribute("appVersionList", list);
		return "/developer/appinfoview";
	}
}
