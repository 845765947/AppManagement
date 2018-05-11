package com.smbms.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.StringUtils;
import com.smbms.pojo.App_info;
import com.smbms.pojo.Dev_user;
import com.smbms.service.AppinfoService;
import com.smbms.tools.Constants;

@Controller
@RequestMapping("/appinfo")
public class AppinfoController {
	Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private AppinfoService infoService;

	@RequestMapping("/apkexist")
	@ResponseBody
	public Object selectInfoApkName(
			@RequestParam(value = "APKName", required = false) String apkName) {
		System.out.println(apkName);
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtils.isNullOrEmpty(apkName)) {
			App_info info = new App_info();
			info = infoService.selectInfoApkName(apkName);
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
				request.setAttribute("errorInfo", "上传的文件大小不能超过500KB");
				return "useradd";
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
					request.setAttribute("uploadFileError", "*上传失败!");
					return "useradd";
				}
			} else {
				request.setAttribute("uploadFileError", "*上传图片格式不正确!");
				return "useradd";
			}

			// LOGO图片url路径
			logoPicPath = fileName;
			// LOGO图片的服务器存储路径
			logoLocPath = path + File.separator + fileName;

			// 设置新增id
			info.setCreatedBy(((Dev_user) session
					.getAttribute(Constants.USER_SESSION)).getId());
			// 设置新增时间
			info.setCreationDate(new Date());
			// 文件上传结束，开始新增
			boolean fig = infoService.insertInfo(info);
		}
		return "redirect:/dev/flatform/app/list";
	}
}
