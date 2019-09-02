package com.youyicn.controller.cycle;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youyicn.entity.User;
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ArrTurnService;
import com.youyicn.service.cycle.UserRoleService;
/**
 * 轮专题统计
 * 老师的带教统计  暂时未做
 */
@Controller
public class ArrturnCountController {
private Logger logger = Logger.getLogger(ArrturnCountController.class);
	@Autowired
	private ArrTurnService arrTurnService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	//老师的带教统计
	@RequestMapping("/acountController/teacherindex.htm")
	public String accUserindex (HttpServletResponse response,HttpServletRequest request,ModelMap model){
		User userSession = getUserSession(request);
		String loginName = userSession.getUserNum();
		ArrTurn arrTurnCon = new ArrTurn();
		arrTurnCon.setLoginName(loginName);
		List<ArrTurn> arrTurnList = arrTurnService.getArrTurnByCon(arrTurnCon);
		return "";
	}
	
	//通用的配置都是放在最下面
	private User getUserSession(HttpServletRequest request) {
		Object obj= request.getSession().getAttribute("loginName");
		User teacher = new User();
		if(obj instanceof String){
			String loginName = (String) obj;
			teacher  = userService.getByNum(loginName);
		}
		return teacher;
	}
	
	
}
