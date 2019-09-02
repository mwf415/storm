package com.youyicn.controller.cycle.arrturn;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyicn.common.DateUtil;
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
/**
 * 本科生轮转规则的controller
 */
@Controller
public class ArrTurnOneController {
	public static final Integer LOWTYPE=3;//本科生 
	public static final Integer HIGHTYPE=2; //研究生
	
	public final static int ONEDAY=1;
	public final static String CHECKSTATUS="0";

	public final static String BATCH0="0";//代表批量
	public final static String BATCH1="1";//代表单个
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;
	@Autowired
	public UserService userService;
	
	@Autowired
	public ArrTurnService arrTurnService;
	
	@Autowired
	public ArrturnRuleService arrTurnRuleService;
	
	@Autowired
	public ArrturnRuleService arrturnRuleService;
	
	@RequestMapping("/arrTurnOneController/arrturnindex.htm")
	public String arrTurnRuleIndex(HttpServletRequest req,ModelMap map){
		String li= req.getParameter("li");
		String  div= req.getParameter("div");
		String type= req.getParameter("type");
		List<Base> baseValues = baseService.queryAllBase();
		map.put("baseValues", baseValues);
		map.put("li", li);
		map.put("div", div);
		map.put("type", type);
		
		return "/arrTurnOne/arrTurnOneIndex";
	}
	@RequestMapping("/arrTurnOneController/add.htm")
	public String arrTurnRuleadd(HttpServletRequest req,ModelMap map){
		String li= req.getParameter("li");
		String  div= req.getParameter("div");
		String type= req.getParameter("type");
		String grade = req.getParameter("grade");
		map.put("grade", grade);
		map.put("li", li);
		map.put("div", div);
		map.put("type", type);
		String baseName = req.getParameter("baseName");
		
		List<ArrturnRule> baseRoomList = arrturnRuleService.getArrTurnRuleByBaseName(baseName);
		
		ArrturnRule arrTurnRuleCon = new ArrturnRule();
		arrTurnRuleCon.setBaseName(baseName);
		arrTurnRuleCon.setType(Integer.parseInt(type));
		List<ArrturnRule> atrList = arrTurnRuleService.getArrTurnByCon(arrTurnRuleCon);
		List<Base> baseValues = baseService.queryAllBase();
		
		map.put("baseName", baseName);
		map.put("baseValues", baseValues);
		map.put("atrList", atrList);
		map.put("baseRoomList", baseRoomList);
		//查找未安排的学员  本来这里可以没有的，但是后来需要就添加进去了；
		if(null!=grade && ""!= grade){
			//获取基地下本科生信息 某一年级的学生
			User userCon = new User();
			userCon.setBaseName(baseName);
			userCon.setTrainTime(Integer.parseInt(type));
			userCon.setGrade(Integer.parseInt(grade));
			List<User> userListTemp = userService.getByCondition(userCon);
			List<User> userList= new ArrayList<User>();
			userList .addAll(userListTemp);
			
			//这里主要是为了减去已经安排的学员
			ArrTurn arrTurnCon = new ArrTurn();
			arrTurnCon.setGrade(Integer.parseInt(grade));
			arrTurnCon.setBasename(baseName);
			arrTurnCon.setTrainTime(Integer.parseInt(type));
			//这里如果用户多的话，绝对受不了
			List<ArrTurn> arrTurnList = arrTurnService.getArrTurnByCon(arrTurnCon);
			Set<String> loginNameSet = new HashSet<String>();
			for (ArrTurn arrTurn : arrTurnList) {
				String loginName = arrTurnCon.getLoginName();
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
			map.put("userList", userList);
			
		}else{
			map.put("userList", null);
		}
		
		return "/arrTurnOne/arrTurnOneAdd";
	}
	
	
	/**
	 * 轮转规则入库
	 * @param req
	 * @param map
	 * @return
	 */
	@RequestMapping("/arrTurnOneController/insert.htm")
	@ResponseBody
	public String arrTurnRuleInsert(HttpServletRequest req,HttpServletResponse resp, ModelMap map){
		String li= req.getParameter("li");
		String  div= req.getParameter("div");
		String grade = req.getParameter("grade");
		String baseName =req.getParameter("baseName");
		String type = req.getParameter("type");
		map.put("li", li);
		map.put("div", div);
		map.put("type", type);
		map.put("baseName", baseName);
		map.put("grade", grade);
		
		//创建新的纪录，主要是获取名字，科室，和排列顺序，基地名称
		ArrturnRule rule = new ArrturnRule();
		String roomName = req.getParameter("roomName");
		String period = req.getParameter("period");
		String roomSort1 = req.getParameter("roomSort");
		if(""!=roomSort1 && null!=roomSort1){
			rule.setRoomSort(Integer.parseInt(roomSort1));
		}
		
		rule.setType(Integer.parseInt(type));
		rule.setBaseName(baseName);
		rule.setPeriod(period);
		rule.setRoomName(roomName);
		try {
			arrturnRuleService.insertRule(rule);
			ControllerHelper.respOut(resp, "true");
		} catch (Exception e) {
			e.printStackTrace();
			ControllerHelper.respOut(resp, "false");
		}
		return null;
	}
	
	@RequestMapping("/arrTurnOneController/edit.htm")
	public String edit(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String id1= request.getParameter("id");
		String li= request.getParameter("li");
		String  div= request.getParameter("div");
		String type= request.getParameter("type");
		String baseName =request.getParameter("baseName");
		model.put("baseName", baseName);
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		if(null!=id1 && ""!=id1){
			ArrturnRule arrTurnRule = arrturnRuleService.getArrTurnRuleById(Integer.parseInt(id1));
			model.put("arrTurnRule", arrTurnRule);
		}
		
		return "/arrTurnOne/arrTurnOneEdit";
	}	
	//删除
	@RequestMapping("/arrTurnOneController/arrturndel.htm")
	public String arrTurnRuledel(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String idp = request.getParameter("id");
		String li= request.getParameter("li");
		String  div= request.getParameter("div");
		String type= request.getParameter("type");
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		if(null != idp && !"".equals(idp)){
			int id = Integer.parseInt(idp);
			try {
				arrTurnRuleService.delArrTurnRule(id);
				ControllerHelper.respOut(response, "true");
			} catch (Exception e) {
				ControllerHelper.respOut(response, "false");
			}
		}
		return null;
	}
	//更新规则
	@RequestMapping("/arrTurnOneController/arrturnRuleUpdate.htm")
	public String update(HttpServletRequest request,HttpServletResponse response, ModelMap model){
		String li= request.getParameter("li");
		String  div= request.getParameter("div");
		String type= request.getParameter("type");
		String baseName =request.getParameter("baseName");
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		model.put("baseName", baseName);
		String id1= request.getParameter("idU");
		String period=request.getParameter("periodU");
		String roomSort= request.getParameter("roomSortU");
		if(""!=id1&& null !=id1){
			try {
				ArrturnRule arrTurnRule = arrturnRuleService.getArrTurnRuleById(Integer.parseInt(id1));
				if(""!=roomSort&& null !=roomSort){
					arrTurnRule.setRoomSort(Integer.parseInt(roomSort));
				}
				if(""!=period&& null !=period){
					arrTurnRule.setPeriod(period);
				}
				arrturnRuleService.upDateArrTurnRule(arrTurnRule);
				ControllerHelper.respOut(response, true);
			} catch (Exception e) {
				ControllerHelper.respOut(response, false);
			}
		}
		return null;
	}
	
	
	//创建学生的轮转信息  放到待审核里面
	@RequestMapping("/arrTurnOneController/ArrTurnSum.htm")
	public String sumArrTurn(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String userParm =request.getParameter("userParm");
		String baseName = request.getParameter("baseName");
		String startTime1= request.getParameter("startTime");
		String grade = request.getParameter("grade");
		String type =request.getParameter("type");//主要是区分研究生和本科生
		String li= request.getParameter("li");
		String  div= request.getParameter("div");
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		model.put("baseName", baseName);
		if(null!=userParm && ""!=userParm && null!= baseName & ""!=baseName){
			String user1[] =userParm.split(":");
			String loginName = user1[0];
			String userName =user1[1];
			ArrturnRule arrTurnRuleCon = new ArrturnRule();
			arrTurnRuleCon.setBaseName(baseName);
			arrTurnRuleCon.setType(Integer.parseInt(type));
			List<ArrturnRule> arrTurnRules = arrturnRuleService.getArrTurnByCon(arrTurnRuleCon);
			try {
				if(""!=startTime1&& null!=startTime1 && ""!=type &&null!=type){
					Timestamp startTime =DateUtil.str2Timestamp(startTime1, null);
					Timestamp endTime;
					User user =userService.getByNum(loginName);
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
						arrTurn.setRealName(userName);
						arrTurn.setLoginName(loginName);
						arrTurn.setEndTime(endTime);
						arrTurn.setHospitalId(user.getHospitalId());
						arrTurn.setBatch(BATCH0);
						arrTurn.setGrade(Integer.parseInt(grade));
						arrTurnService.addArrTurn(arrTurn);
						//为下一个循环做准备
						startTime = DateUtil.addday(endTime, ONEDAY);
					}
				}
				ControllerHelper.respOut(response, true);
			} catch (Exception e) {
				ControllerHelper.respOut(response, false);
			}
		}
		
		return null;
	}	
}
