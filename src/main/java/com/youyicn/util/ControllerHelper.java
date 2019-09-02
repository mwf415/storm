package com.youyicn.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ControllerHelper {
	
	private static Logger logger = Logger.getLogger(ControllerHelper.class);
	
	public static void respOut(HttpServletResponse response, Object content) {
		if (null == content)
			return;
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			if (content instanceof String)
				response.getWriter().write(content.toString());
			else if (content instanceof Integer)
				response.getWriter().write(content + "");
			else if (content instanceof Boolean)
				response.getWriter().write(content + "");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public static void responseRedirect(HttpServletRequest request, HttpServletResponse response, String url) {
		try {
			response.sendRedirect(request.getContextPath() + url);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	public static boolean checkDac(HttpServletRequest request, String priCode) {
//		LoginSession session = (LoginSession) request.getSession().getAttribute("loginSession");
//		Map<String, Boolean> priMap = session.getPriMap();
//		if (null != priMap) {
//			if (priMap.get(priCode))
//				return true;
//		}
		return false;
	}

}
