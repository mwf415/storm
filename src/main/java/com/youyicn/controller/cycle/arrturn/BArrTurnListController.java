package com.youyicn.controller.cycle.arrturn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youyicn.entity.User;
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ArrTurnService;
import com.youyicn.service.cycle.ArrturnRuleService;
import com.youyicn.service.cycle.RoomService;

@Controller
public class BArrTurnListController {
	public Logger log= LoggerFactory.getLogger(ArrTurnController.class);
	public final static String CHECKSTATUS="0";
	public final static int ONEDAY=1;
	public static final Integer LOWTYPE=3;//本科生 
	public static final Integer HIGHTYPE=2; //研究生
	@Autowired
	public ArrturnRuleService arrTurnRuleService;
	@Autowired
	public ArrturnRuleService arrturnRuleService;
	@Autowired
	private UserService userService;
	@Autowired
	private ArrTurnService arrTurnService;
	@Autowired
	private RoomService roomService; 
	//这个主要用来查询本科生的
	@RequestMapping("/arrTurnController/arrturnQuery_index.htm")
	public String arrTurnInde(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		User userSession = getUserSession(request);
		String li= request.getParameter("li");
		String  div= request.getParameter("div");
		String type= request.getParameter("type");
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		User userCon= new User();
		userCon.setBaseName(userSession.getBaseName());
		if("2".equals(type)||"2"==type){
			userCon.setTrainTime(HIGHTYPE);
		}
		if("3".equals(type)||"3"==type){
			userCon.setTrainTime(LOWTYPE);
		}
		List<User> userList= userService.getByCondition(userCon);
		model.put("userList", userList);
		
		return "/arrTurn/showArrTurnIndex";
	}
	
	@RequestMapping("/bArrTurnController/list.htm")
	public String list(HttpServletRequest request,HttpServletResponse response ,ModelMap model){
		String li= request.getParameter("li");
		String  div= request.getParameter("div");
		String type= request.getParameter("type");
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		ArrTurn arrTurnCon = new ArrTurn();
		//隐藏的是自动查询，可以把这一块先不做，
		//		if(null!=grade&& ""!=grade){
//			arrTurnCon.setGrade(grade);
//		}
//		if(null!=loginName && ""!=loginName){
//			arrTurnCon.setLoginName(loginName);
//		}
		User userSession = getUserSession(request);
		User userCon= new User();
		userCon.setBaseName(userSession.getBaseName());
		if("2".equals(type)||"2"==type){
			userCon.setTrainTime(HIGHTYPE);
		}
		if("3".equals(type)||"3"==type){
			userCon.setTrainTime(LOWTYPE);
		}
		List<User> userList= userService.getByCondition(userCon);
		List<ArrTurn> arrTurnList = new ArrayList<ArrTurn>();
		Map<String,List<ArrTurn>> resultMap = new HashMap<String, List<ArrTurn>>();
		for (User user : userList) {
			arrTurnCon.setLoginName(user.getLoginName());
			arrTurnList=arrTurnService.getArrTurnByCon(arrTurnCon);
			if(arrTurnList.size() > 0){
				resultMap.put(user.getLoginName(), arrTurnList);
			}
		}
		model.put("resultMap", resultMap);
		return "/arrTurn/arrTurnList";
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
