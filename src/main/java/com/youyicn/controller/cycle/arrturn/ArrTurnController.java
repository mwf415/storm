package com.youyicn.controller.cycle.arrturn;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.youyicn.common.DateUtil;
import com.youyicn.common.SortUtil;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.entity.cycle.ArrturnRule;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.Room;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ArrTurnService;
import com.youyicn.service.cycle.ArrturnRuleService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.util.ControllerHelper;

@Controller
public class ArrTurnController {
	public Logger log= LoggerFactory.getLogger(ArrTurnController.class);
	public static final Integer LOWTYPE=3;//本科生 
	public static final Integer HIGHTYPE=2; //研究生
	public static final Integer isAt=1; //是否安排成功
	public static final String DEFAULT_FORMAT3 = "yyyy-MM-dd";
	public final static String CHECKSTATUS="0";

	public final static String BATCH0="0";//代表批量
	public final static String BATCH1="1";//代表单个
	
	
	public final static int ONEDAY=1;
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;	
	@Autowired
	private UserService userService;
	@Autowired
	private ArrTurnService arrTurnService;
	@Autowired
	private ArrturnRuleService arrturnRuleService;
	//首页渲染使用
	@RequestMapping("/arrTurnController/arrturn_index.htm")
	public String arrTurnindex(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String li= request.getParameter("li");
		String div= request.getParameter("div");
		String type= request.getParameter("type");
		String grade = request.getParameter("grade");
		model.put("grade", grade);
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		List<Base> baseValues = baseService.queryAllBase();
		model.put("baseValues", baseValues);
		String baseName = request.getParameter("baseName");
		model.put("baseName", baseName);
		ArrturnRule arrTurnRuleCon = new ArrturnRule();
		arrTurnRuleCon.setBaseName(baseName);
		arrTurnRuleCon.setType(Integer.parseInt(type));
		List<ArrturnRule> arrTurnRuleList = arrturnRuleService.getArrTurnByCon(arrTurnRuleCon);
		if(null!=grade && ""!= grade){
			//获取基地下本科生信息 某一年级的学生
			User userCon = new User();
			userCon.setBaseName(baseName);
			if("2".equals(type)||"2"==type){
				userCon.setTrainTime(HIGHTYPE);
			}
			if("3".equals(type)||"3"==type){
				userCon.setTrainTime(LOWTYPE);
			}
			userCon.setGrade(Integer.parseInt(grade));
			List<User> userListTemp = userService.getByCondition(userCon);
			List<User> userList= new ArrayList<User>();
			userList .addAll(userListTemp);
			
			//这里主要是为了减去已经安排的学员
			ArrTurn arrTurnCon = new ArrTurn();
			arrTurnCon.setBasename(baseName);
			List<ArrTurn> arrTurnList = arrTurnService.getArrTurnByCon(arrTurnCon);
			Set<String> loginNameSet = new HashSet<String>();
			for (ArrTurn arrTurn : arrTurnList) {
				String loginName = arrTurn.getLoginName();
				loginNameSet.add(loginName);
			}
			for (User user : userListTemp) {
				String loginName = user.getLoginName();
				for (String loginName1 : loginNameSet) {
					if(loginName== loginName1||loginName.equals(loginName1)){
						userList.remove(user);
					}
				}
			}
			//查询所有已分配的学员信息
			model.put("userList", userList);
			model.put("arrTurnRuleList", arrTurnRuleList);
		}else{
			model.put("userList", null);
			model.put("arrTurnRuleList", arrTurnRuleList);
		}
		return "/arrTurn/arrTurnIndex";
	}
	
	/**
	 * 插入轮转安排
	 */
	@RequestMapping("/arrTurnController/subArrTurn.htm")
	@ResponseBody
	public String subArrTurn(HttpServletRequest req, HttpServletResponse resp,ModelMap model) throws Exception{
		String li= req.getParameter("li");
		String  div= req.getParameter("div");
		String type= req.getParameter("type");
		String grade = req.getParameter("grade");
		String baseName = req.getParameter("baseName");
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		model.put("baseName", baseName);
		
	    String userParm = req.getParameter("userParm");
	    String[] userNameNum = userParm.split(":");
	    String loginName =userNameNum[0];
	    String userName =userNameNum[1];
		String roomName =  URLDecoder.decode(req.getParameter("roomName"),"UTF-8");
		String startTime1 = req.getParameter("startTime");
		//获取轮转规则
		
		ArrturnRule arrTurnRuleCon = new ArrturnRule();
		arrTurnRuleCon.setBaseName(baseName);
		arrTurnRuleCon.setType(Integer.parseInt(type));
		List<ArrturnRule> arrTurnRulesTemp = arrturnRuleService.getArrTurnByCon(arrTurnRuleCon);
		//轮转规则排序
		List<ArrturnRule> arrTurnRules =SortUtil.roomSort(arrTurnRulesTemp, roomName);
		if(""!=startTime1&& null!=startTime1 && ""!=type &&null!=type){
			startTime1 = startTime1+" 00:00:00";
				Timestamp startTime =DateUtil.str2Timestamp(startTime1, null);
				startTime.setHours(01);
				Timestamp endTime;
				User user = userService.getByNum(loginName);
				for (ArrturnRule arrTurnRule : arrTurnRules) {
					ArrTurn arrTurn = new ArrTurn();
					arrTurn.setBasename(arrTurnRule.getBaseName());
					arrTurn.setRoomName(arrTurnRule.getRoomName());
					arrTurn.setStartTime(startTime);
					String period= arrTurnRule.getPeriod();
					Double num1 = Double.valueOf(period.toString());
					endTime =DateUtil.addMonth(startTime, num1) ;
					arrTurn.setCheckStatus(CHECKSTATUS);
					if("2".equals(type)||"2"==type){
						arrTurn.setTrainTime(HIGHTYPE);
					}
					if("3".equals(type)||"3"==type){
						arrTurn.setTrainTime(LOWTYPE);
					}
					arrTurn.setHospitalId(user.getHospitalId());
					arrTurn.setRealName(userName);
					arrTurn.setLoginName(loginName);
					arrTurn.setHospitalId(user.getHospitalId());
					arrTurn.setEndTime(endTime);
					arrTurn.setBatch(BATCH0);
					arrTurn.setGrade(Integer.parseInt(grade));
					arrTurnService.addArrTurn(arrTurn);
					//为下一个循环做准备
					startTime = DateUtil.addday(endTime, ONEDAY);
			}
			user.setIsAt(isAt);
			userService.editUser(user);
			ControllerHelper.respOut(resp, true);
			
		}
		return null;	
	}
	
	

	/**
	 * 插入批量自动轮转安排
	 */
	@RequestMapping("/arrTurnController/subArrTurnPick.htm")
	@ResponseBody
	public String subArrTurnPick(HttpServletRequest req, HttpServletResponse resp,ModelMap model) throws Exception{
		String li= req.getParameter("li");
		String  div= req.getParameter("div");
		String type= req.getParameter("type");
		String grade = req.getParameter("grade");
		String baseName = req.getParameter("baseName");
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		model.put("baseName", baseName);
		
	    String [] userNamePick = req.getParameterValues("userNamePick");
		String startTime1 = req.getParameter("startTime1");
		//获取轮转规则
		
		ArrturnRule arrTurnRuleCon = new ArrturnRule();
		arrTurnRuleCon.setBaseName(baseName);
		arrTurnRuleCon.setType(Integer.parseInt(type));
		List<ArrturnRule> arrTurnRules = arrturnRuleService.getArrTurnByCon(arrTurnRuleCon);
		for (ArrturnRule arrturnRule : arrTurnRules) {
			System.out.print(arrturnRule.getRoomName()+":"+arrturnRule.getRoomSort());
		}
		Collections.shuffle(arrTurnRules);
		for (ArrturnRule arrturnRule : arrTurnRules) {
			System.out.print(arrturnRule.getRoomName()+":"+arrturnRule.getRoomSort());
		}
		System.out.println();
		if(userNamePick !=null && userNamePick.length>0){
			for (String loginName : userNamePick) {
				/**
				 * 随机排序，一个人一个顺序
				 */
				Collections.shuffle(arrTurnRules);
				User user =  userService.getByNum(loginName);
				if(""!=startTime1&& null!=startTime1 && ""!=type &&null!=type){
					startTime1 = startTime1+" 00:00:00";
					Timestamp startTime =DateUtil.str2Timestamp(startTime1, null);
					startTime.setHours(01);
					Timestamp endTime;
					for (ArrturnRule arrTurnRule : arrTurnRules) {
						ArrTurn arrTurn = new ArrTurn();
						arrTurn.setBasename(arrTurnRule.getBaseName());
						arrTurn.setRoomName(arrTurnRule.getRoomName());
						arrTurn.setStartTime(startTime);
						String period= arrTurnRule.getPeriod();
						Double num1 = Double.valueOf(period.toString());
						endTime =DateUtil.addMonth(startTime, num1) ;
						arrTurn.setCheckStatus(CHECKSTATUS);
						if("2".equals(type)||"2"==type){
							arrTurn.setTrainTime(HIGHTYPE);
						}
						if("3".equals(type)||"3"==type){
							arrTurn.setTrainTime(LOWTYPE);
						}
						arrTurn.setHospitalId(user.getHospitalId());
						arrTurn.setRealName(user.getRealName());
						arrTurn.setLoginName(user.getLoginName());
						arrTurn.setHospitalId(user.getHospitalId());
						arrTurn.setEndTime(endTime);
						arrTurn.setBatch(BATCH0);
						arrTurn.setGrade(Integer.parseInt(grade));
						arrTurnService.addArrTurn(arrTurn);
						//为下一个循环做准备
						startTime = DateUtil.addday(endTime, ONEDAY);
					}
					user.setIsAt(isAt);
					userService.editUser(user);
					
				}
			}
			ControllerHelper.respOut(resp, true);
		}
		return null;
	}
	
	@RequestMapping("/arrTurnController/querySingleArrturn_index.htm")
	public String querySingleArrturn(HttpServletRequest req, ModelMap map){
		String loginName = req.getParameter("loginName");
		String identity = req.getParameter("identity");
		ArrTurn arrturn = new ArrTurn();
		if(identity.equals("1")){
			arrturn.setLoginName(loginName);
		}else{
			arrturn.setTeacherNum1(loginName);
		}
		List<ArrTurn> arrturnList = arrTurnService.getArrTurnByCon(arrturn);
		map.put("arrturnList", arrturnList);
		map.put("flag", "flag");
		return "/arrTurn/arrTurnSingleIndex";
	}
	
	
	//添加页面, 往页面传入的参数是科室的信息，同时注意获取用户的身份 角色，以及相关基地；
	@RequestMapping("/arrTurnController/arrturn_param.htm")
	public String getParame(HttpServletRequest request,HttpServletResponse response,ModelMap model){	
		//获得所有的年级
		Set<Integer> gradeList=getGradeList();
		model.put("gradeList", gradeList);
		//把科室传到前台；
		List<Room> roomValues = roomService.queryAllRoom();
		model.put("roomValues", roomValues);
		
		return "/arrTurn/arrTurnAdd";
	}

	//根据用户查询用户年级
	private Set<Integer> getGradeList() {
		User user= new User();
		List<User> userList=userService.getByCondition(user);
		if(userList.size()>0){
			Set<Integer> gradeList = new HashSet<Integer>();//年级列表
			for (User user2 : userList) {
				Integer grade=user2.getGrade(); 
				gradeList.add(grade);
			}
			return gradeList;
		}
		return null;
	}
	//ajax发送请求，根据年级查找用户
	@RequestMapping("/arrTurnController/getUserByGrade.htm")
	public String getuserByGrade (HttpServletRequest request){
		String grade= request.getParameter("grade");
		User user= new User();
		if(null!=grade && !"".equals(grade)){
			user.setGrade(Integer.parseInt(grade));
		}
		List <User> userList=userService.getByCondition(user);
		
		String rNum = "{\"r\":\"" + JSON.toJSONString(userList) + "\"}";

		//把科室传到前台；
		return rNum;
	}
	
}
