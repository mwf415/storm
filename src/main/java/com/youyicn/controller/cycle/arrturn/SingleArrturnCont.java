package com.youyicn.controller.cycle.arrturn;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.common.DateUtil;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.entity.cycle.ArrturnRule;
import com.youyicn.entity.cycle.Base;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ArrTurnService;
import com.youyicn.service.cycle.ArrturnRuleService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.util.ControllerHelper;
import org.springframework.web.bind.annotation.ResponseBody;


//轮转详情，主要是指学员安排
@Controller
public class SingleArrturnCont {
	public Logger log= LoggerFactory.getLogger(ArrTurnController.class);
	private final static String CHESTATUSIN="1";
	@Autowired
	private UserService userService;
	@Autowired
	private ArrTurnService arrTurnService;
	@Autowired
	private RoomService roomService; 
	@Autowired
	private ArrturnRuleService arrturnRuleService;
	@Autowired
	public BaseService baseService;
	
	/**
	 * 这里不用考虑科室的问题
	 */
	@RequestMapping("/singleArrTurnCont/index.htm") 
	public String jindex(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer menuOrder){
		String div = request.getParameter("div");
		String li= request.getParameter("li");
		String type = request.getParameter("type");
		
		HttpSession session = request.getSession();
		session.setAttribute("li", li);
		session.setAttribute("div", div);
		session.setAttribute("type", type);
		session.setAttribute("menuOrder", menuOrder);
		
		User userSession = getSession(request);
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		String pageNum = request.getParameter("pageIndex");
		int pageIndex = 1;
		if (null != pageNum && !"".equals(pageNum)) {
			pageIndex = Integer.valueOf(pageNum);
		}
		User userCon = new User();
		if("s".equals(type)){
			ArrTurn arrTurnCon = new ArrTurn();
			arrTurnCon.setLoginName(userSession.getLoginName());
			User user = userService.getByNum(userSession.getLoginName());
			model.put("loginName", user.getLoginName());
			String baseName = user.getBaseName();
			List<ArrturnRule> roomValues = new ArrayList<ArrturnRule>();
			
			roomValues= getRoomValues(baseName);
			
			model.put("roomValues", roomValues);
			
			List<ArrTurn> arrTurnList=arrTurnService.getArrTurnByCon(arrTurnCon);
			for (ArrTurn arrTurn : arrTurnList) {
				List<User> teacherList = teacherService.getTByRoom(arrTurn.getRoomName());
				arrTurn.setTeacherList(teacherList);
			}
			model.put("arrTurnList", arrTurnList);
			return "/arrTurnSingle/arrTurns";
		}else{
			String userName = request.getParameter("realName");
			String baseName = request.getParameter("baseName");
			String grade1 = request.getParameter("grade");
			String roomName = request.getParameter("roomName");
		
			int grade =0 ;
			if(null!= grade1 && ""!= grade1){
				grade = Integer.parseInt(grade1);
			}	
			model.put("userNamed", userName);
			
			if(null!=pageNum && !"".equals(pageNum) ){
				pageIndex = Integer.valueOf(pageNum);
			}
			PageHelper.startPage(pageIndex, 15);
			User user =new User();
			if(null != baseName && !"".equals(baseName)){
				user.setBaseName(baseName);
				model.put("baseNamed", baseName);
			}
			
			if(null != roomName && !"".equals(roomName)){
				user.setRoomName(roomName);
				model.put("roomNamed", roomName);
			}
			
			if(null != userName && !"".equals(userName)){
				user.setRealName("%"+ userName+"%");
			}
			
			if(0!= grade ){
				user.setGrade(grade);
				model.put("grade", grade);
			}
			user.setIdentityId(2);
			
			List<User> userList=userService.getByCondition(user); 
			PageInfo<User> page = new PageInfo<User>(userList);
			model.put("page", page);
			
			List<Base> baseValues = baseService.queryAllBase();
			model.put("baseValues", baseValues);
			
			return "/arrTurnSingle/index";
		}
	}
	
	@RequestMapping("/singleArrTurnCont/detail.htm")
	public String detail(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer menuOrder){
		String li= request.getParameter("li");
		String div = request.getParameter("div");
		String type = request.getParameter("type");
		String baseName = request.getParameter("baseName");
		model.put("baseName", baseName);
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		model.put("menuOrder", menuOrder);
		List<ArrturnRule> roomValues = new ArrayList<ArrturnRule>();


		String loginName= request.getParameter("loginName");
		model.put("loginName", loginName);
		
		roomValues= getRoomValues(baseName);
		model.put("roomValues", roomValues);
		model.put("baseName", baseName);
		ArrTurn arrTurnCon = new ArrTurn();
		arrTurnCon.setLoginName(loginName);
		List<ArrTurn> arrTurnList=arrTurnService.getArrTurnByCon(arrTurnCon);
		int sizeLen=arrTurnList.size();
		model.put("sizeLen", sizeLen);
		model.put("arrTurnList", arrTurnList);
		return "/arrTurnSingle/arrTurnDetailb";
	}
	
	/**
	 * 单独修改
	 * 
	 */
	@RequestMapping("/singleArrTurnCont/updateone.htm")
	
	public String updateone (HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String li= request.getParameter("li");
		String div = request.getParameter("div");
		String type = request.getParameter("type");
		String baseName =request.getParameter("baseName");
		
		String loginName =request.getParameter("loginName");
		if(baseName==null || "".equals(baseName)){
			baseName = userService.getByNum(loginName).getBaseName();
		}
		model.put("loginName", loginName);
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		model.put("baseName", baseName);
		String id1=request.getParameter("id");
		ArrTurn arrTurn = arrTurnService.getArrTurnById(Integer.parseInt(id1));
		model.put("arrTurn", arrTurn);
		List<ArrturnRule> baseRoomList = arrturnRuleService.getArrTurnRuleByBaseName(baseName);
		model.put("baseRoomList", baseRoomList);
		return "/arrTurnSingle/arrTurnRuleUpDate";
		
		
	}
	/**
	 * 推迟一个月
	 * 
	 */
	@RequestMapping("/singleArrTurnCont/updateTime.htm")
	public String updateTime(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer menuOrder){
		String li= request.getParameter("li");
		String div = request.getParameter("div");
		String type = request.getParameter("type");
		String startTime =request.getParameter("startTime");
		String endTime =request.getParameter("endTime");
		String id =request.getParameter("id");
		String loginName =request.getParameter("loginName");
		String basename=request.getParameter("basename");
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		String id1=request.getParameter("id");
		startTime=startTime+" 00:00:00";
		endTime=endTime+" 00:00:00";
		try {
		ArrTurn arrTurn = arrTurnService.getArrTurnById(Integer.parseInt(id1));
		arrTurn.setStartTime(DateUtil.str2Timestamp(startTime,DateUtil.DEFAULT_FORMAT1));
		arrTurn.setEndTime(DateUtil.str2Timestamp(endTime,DateUtil.DEFAULT_FORMAT1));
		arrTurnService.upDataArrTurnTime(arrTurn);
			ControllerHelper.respOut(response, true);
		}catch (Exception e) {
			ControllerHelper.respOut(response, false);
		}
		return null;
	}
	/**
	 * 推迟一个月
	 * 
	 */
	@RequestMapping("/singleArrTurnCont/updateTime1.htm")
	public String updateTime1(HttpServletRequest request,HttpServletResponse response,ModelMap model){
//		String id =request.getParameter("id");
		String loginName =request.getParameter("loginName");
		String basename=request.getParameter("basename");
//		String id1=request.getParameter("id");
		int index=Integer.parseInt(request.getParameter("index"));
		ArrTurn arrTurnCon = new ArrTurn();
		arrTurnCon.setLoginName(loginName);
		List<ArrTurn> arrTurnList=arrTurnService.getArrTurnByCon(arrTurnCon);
		for(int i=index;i<arrTurnList.size();i++){
			String stime=DateUtil.timestamp2Str(arrTurnList.get(i).getStartTime());
			String etime=DateUtil.timestamp2Str(arrTurnList.get(i).getEndTime());
			Integer id=arrTurnList.get(i).getArrTurnId();
			Integer year1=Integer.parseInt(stime.substring(0,4));
			Integer  year2=Integer.parseInt(etime.substring(0,4));
			Integer  mon1=Integer.parseInt(stime.substring(5,7));
			Integer  mon2=Integer.parseInt(etime.substring(5,7));
			int day1=1;
			int day2=30;
			boolean isR=((year2 % 4 == 0) && (year2 % 100 != 0 || year2 % 400 == 0));
			if(mon1==12){
				year1=year1+1;
				mon1=1;
				mon2=2;
				if(isR==true){
					day2=29;
				}else{
					day2=28;
				}
			}else{
				mon1=mon1+1;
				if(mon2==12){
					year2=year2+1;
					mon2=1;
				}else{
					mon2=mon2+1;
				}
				if(mon2==1 || mon2==3||mon2==5||mon2==7||mon2==8||mon2==10||mon2==12){
					day2=31;
				}else if(mon2==2){
					if(isR==true){
						day2=29;
					}else{
						day2=28;
					}
				}else{
					day2=30;
				}
			}
			String mons1="",mons2="";
			if(mon1<10){
				mons1="0"+mon1;
			}else{
				mons1=String.valueOf(mon1);
			}
			if(mon2<10){
				mons2="0"+mon2;
			}else{
				mons2=String.valueOf(mon2);
			}
			String startTime=year1+"-"+mons1+"-01"+" 00:00:00";
			String endTime=year2+"-"+mons2+"-"+day2+" 00:00:00";
			ArrTurn arrTurn = arrTurnService.getArrTurnById(id);
			if(i==index){
				arrTurn.setStartTime(arrTurnList.get(i).getStartTime());
				arrTurn.setEndTime(DateUtil.str2Timestamp(endTime,DateUtil.DEFAULT_FORMAT1));
			}else{
				arrTurn.setStartTime(DateUtil.str2Timestamp(startTime,DateUtil.DEFAULT_FORMAT1));
				arrTurn.setEndTime(DateUtil.str2Timestamp(endTime,DateUtil.DEFAULT_FORMAT1));
			}
			arrTurnService.upDataArrTurnTime(arrTurn);
			
		}
		try {
			ControllerHelper.respOut(response, true);
		}catch (Exception e) {
			ControllerHelper.respOut(response, false);
		}
		return null;
	}
	@RequestMapping("/arrTurnOneController/arrturnRuleSum.htm")
	@ResponseBody
	public String arrTurnSumS(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		
		String li= request.getParameter("li");
		String  div= request.getParameter("div");
		String type= request.getParameter("type");
		String baseName =request.getParameter("baseName");
		String loginName =request.getParameter("loginName");
		model.put("loginName", loginName);
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		model.put("baseName", baseName);
		String id1= request.getParameter("ida");
		String roomName =request.getParameter("roomNamea");
		if(""!=id1&& null !=id1){
			try {
				ArrTurn arrTurn = arrTurnService.getArrTurnById(Integer.parseInt(id1));
				arrTurn.setRoomName(roomName);
				arrTurnService.upDataArrTurnRoom(arrTurn);
			} catch (Exception e) {
			}
		}
		return "true";
		
	}
	
	
	/**
	 * 单独添加
	 */
	@RequestMapping("/singleArrTurnCont/Insert.htm")
	public String singInsert(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String loginName =request.getParameter("loginName");
		String roomName =request.getParameter("roomName");
		String startTime =request.getParameter("startTime");
		startTime = startTime+" 00:00:00";
		String endTime= request.getParameter("endTime");
		endTime = endTime+" 23:59:00";
		ArrTurn arrTurn = new ArrTurn();
		try {
			User user = userService.getByNum(loginName);
			arrTurn.setLoginName(loginName);
			arrTurn.setRealName(user.getRealName());
			arrTurn.setRoomName(roomName);
			arrTurn.setStartTime(DateUtil.str2Timestamp(startTime, null));
			arrTurn.setEndTime(DateUtil.str2Timestamp(endTime, null));
			arrTurn.setHospitalId(user.getHospitalId());
			arrTurn.setBatch("1");
			arrTurn.setGrade(user.getGrade());
			arrTurn.setBasename(user.getBaseName());
			arrTurn.setTrainTime(user.getTrainTime());
			arrTurn.setCheckStatus(CHESTATUSIN);
			arrTurn.setHospitalId(user.getHospitalId());
			arrTurnService.addArrTurn(arrTurn);
			ControllerHelper.respOut(response, "true");
		} catch (Exception e) {
			ControllerHelper.respOut(response, "false");
		}
		return null;
	}
	
	@RequestMapping("/singleArrTurnCont/del.htm")
	public String delindex(HttpServletRequest request,HttpServletResponse response,ModelMap model ){
		String id1= request.getParameter("id");
		if(null!=id1 && ""!=id1){
			int id= Integer.parseInt(id1);
			try{
				arrTurnService.delArrTurnByid(id);
			} catch (Exception e) {
			}
		}
		return "redirect:/singleArrTurnCont/index.htm";
	}

	
	@RequestMapping("/JarrTurnCont/arrturnUser.htm")
	public String jindexSerch(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String gradParm= request.getParameter("grade");
		String grade = "2016"; //默认查询2016级的
		if(""!=gradParm && null != gradParm){
			grade= gradParm;
		}
		
		User teacher = getSession(request);
		String baseName = teacher.getBaseName();
		User userCon = new User();
		userCon.setBaseName(baseName);
		userCon.setGrade(Integer.parseInt(grade));
		List<User> userList  = userService.getByCondition(userCon);
		
		//查找科室
		List<ArrturnRule> baseRoomList = arrturnRuleService.getArrTurnRuleByBaseName(baseName);
		
		model.put("roomValues", baseRoomList);
		model.put("userList", userList);
		return "/arrTurnJ/jarrTurnUser";
	}

	/**
	 * 研究生轮转信息查询
	 */
	@RequestMapping("/JarrTurnCont/jlistindex.htm")
	public String jlistindex(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		User teacher = getSession(request);
		String baseName = teacher.getBaseName();
		//查找科室
		List<ArrturnRule> roomValues = getRoomValues(baseName);
		model.put("roomValues", roomValues);
		
		return "/arrTurnJ/jarrTurnQuery";
	}
	private User getSession(HttpServletRequest request) {
		Object obj= request.getSession().getAttribute("loginName");
		User teacher = new User();
		if(obj instanceof String){
			String loginName = (String) obj;
			teacher  = teacherService.getByNum(loginName);
		}
		return teacher;
	}
	@Autowired
	public UserService teacherService;
	
	
	public List<ArrturnRule> getRoomValues ( String baseName){
		List<ArrturnRule> roomValues =arrturnRuleService.getArrTurnRuleByBaseName(baseName);
		return roomValues;
		
	}
}
