package com.youyicn.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyicn.entity.User;
import com.youyicn.service.UserService;

@Controller
public class CheUserController {

	@Autowired
	public UserService userService;
	
	@RequestMapping("/cheUserCont/cheUser.htm")
	@ResponseBody
	public String getName(HttpServletRequest req,HttpServletResponse resp){
		String loginName = req.getParameter("loginName");
		String userName ="编号错误，未查到此人";
		if(null!=loginName && !"".equals(loginName)){
			User user = userService.getByNum(loginName);
			if(null!=user && !"".equals(user)){
				userName = user.getLoginName();
			}
		}
		try {
			resp.getWriter().print(userName);
			resp.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
