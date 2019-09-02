package com.youyicn.controller.cycle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youyicn.entity.LoginSession;
import com.youyicn.entity.User;
import com.youyicn.service.cycle.ArrTurnService;

/**
 * 换科查询
 */
@Controller
public class MenuController {
	@Autowired
	public ArrTurnService arrTurnService;

	@RequestMapping("/menuController/index.htm")
	public String index(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,String userRole) {
		HttpSession session = request.getSession();
		LoginSession <User> loginSession = (LoginSession<User>) session.getAttribute("loginSession");
		loginSession.setUserRole(userRole);
		
		if("s".equals(userRole)){
			return "redirect:/singleArrTurnCont/index.htm?li=li50&div=div_5&menuOrder=5&type=s";
		}
		if("t".equals(userRole)){
			return "redirect:/teacheringController/index.htm?li=li41&div=div_4&menuOrder=4";	
		}
		if("r".equals(userRole)){
			return "redirect:/arrTeacherCont/arrTeacherindex.htm?li=li312&div=div_3&menuOrder=3&type=r";	
		}
		if("b".equals(userRole)){
			return "redirect:/singleArrTurnCont/index.htm?li=li20&div=div_2&menuOrder=2&type=b";	
		}
		if("h".equals(userRole)){
			return "redirect:/plotController/plot_index.htm?li=li62&div=div_6&menuOrder=1&type=h";	
		}
		return null;
	}
	
}
