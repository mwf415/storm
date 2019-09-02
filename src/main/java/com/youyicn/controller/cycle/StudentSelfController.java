package com.youyicn.controller.cycle;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youyicn.entity.User;
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ArrTurnService;

/**
 * 学生自己统计自己在各个科室的学习情况
 */
@Controller
public class StudentSelfController {
	@Autowired
	private ArrTurnService arrTurnService;
	
	@RequestMapping("/studentSelfController/index.htm")
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap model,String type){
		User userSession = getSession(request);
		if("s".equals(type)){
			ArrTurn arrTurnCon = new ArrTurn();
			arrTurnCon.setLoginName(userSession.getLoginName());
			List<ArrTurn> arrTurnList=arrTurnService.getArrTurnByCon(arrTurnCon);
			model.put("arrTurnList", arrTurnList);
			return "/studentSelf/index";
		}
		return null;
	}
	
	
	
	private User getSession(HttpServletRequest request) {
		Object obj= request.getSession().getAttribute("loginName");
		User user = new User();
		if(obj instanceof String){
			String loginName = (String) obj;
			user  = userService.getByNum(loginName);
		}
		return user;
	}
	@Autowired
	public UserService userService;
}
