package com.smbms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.smbms.pojo.Dev_user;
import com.smbms.tools.Constants;

public class SysInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(SysInterceptor.class);

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out
				.println("SysInterceptor preHandle ==========================");
		logger.debug("SysInterceptor preHandle ==========================");
		HttpSession session = request.getSession();

		Dev_user user = (Dev_user) session.getAttribute(Constants.USER_SESSION);

		if (null == user) {
			response.sendRedirect(request.getContextPath() + "/WEB-INF/403.jsp");
			return false;
		}
		return true;
	}
}
