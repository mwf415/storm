package com.youyicn.model.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.youyicn.util.JedisUtils;
import com.youyicn.util.StringUtils;
/**
 * 
 * 此处全局的一些处理，保存值以及需要删除缓存的公共处理
 */
public class CycleRequestFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		request.setCharacterEncoding("utf-8");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		Object obj = req.getSession().getAttribute("loginSession");
		String urls = req.getRequestURI();

		if (obj != null) {// 登录状态,session有效
			chain.doFilter(new HttpServletRequestWrapper((HttpServletRequest) request) {
				@Override
				public String getRequestURI() {
					return super.getRequestURI();
				}
			}, response);
		} else {
			if (urls.contains("/adminLogin")||urls.contains("subjectztree") || urls.contains("base/list") || urls.contains("room/list")|| urls.contains("userContrller/user_submit") || urls.contains("/checkLoginName")) {
				// 登录页面,通过
				chain.doFilter(new HttpServletRequestWrapper((HttpServletRequest) request) {
					@Override
					public String getRequestURI() {
						return super.getRequestURI();
					}
				}, response);
			} else {// session超时,重新登录				
				Cookie[] cookies = req.getCookies();
				if(cookies.length>0){					
					String token ="";
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("TT_TOKEN")) {
							token = cookie.getValue();
						}
					}
					if(StringUtils.NotNull(token)){						
						JedisUtils jedisUtil =JedisUtils.getInstance();
						JedisUtils.Strings strings=jedisUtil.new Strings();  
						String loginName = strings.get("REDIS_SESSION_KEY:"+token);				
					
						if(loginName!=null ){
							chain.doFilter(new HttpServletRequestWrapper((HttpServletRequest) request) {
								@Override
								public String getRequestURI() {
									return super.getRequestURI();
								}
							}, response);
						}
					}
					resp.sendRedirect("/cycle/login.html");
				}else{
					resp.sendRedirect("/cycle/login.html");
				}
			}
		}
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		// do nothing
	}

	public void destroy() {
		// do nothing
	}

}