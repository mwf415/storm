package com.youyicn.controller.cycle.arrturn;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.entity.cycle.ArrTurnDetailResult;
import com.youyicn.entity.cycle.ArrturnRule;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.Room;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ArrTurnService;
import com.youyicn.service.cycle.ArrturnRuleService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.util.ControllerHelper;
import com.youyicn.util.TimeUtils;
/**
 * 审核功能
 */
@Controller
public class ArrTurnCheckController {
	public Logger log= LoggerFactory.getLogger(ArrTurnController.class);
	public final static String CHECKSTATUSNO="0";
	public final static String CHECKSTATUSIN="1";
	public final static String BATCH0="0";//批次  0代表是批量
	public final static String BATCH1="1";//批次  1 代表是单个
	public final static int ONEDAY=1;
	public static final Integer LOWTYPE = 3;//本科生 
	public static final Integer HIGHTYPE=2; //研究生

	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;
	@Autowired
	public ArrTurnService arrTurnService;
	@Autowired
	public ArrturnRuleService arrTurnRuleService;
	@Autowired
	public ArrturnRuleService arrturnRuleService;
	@Autowired
	private UserService userService;
	@RequestMapping("/arrTurnCheckController/arrTurnIndex.htm")
	public String arrTurnCheckIndex(HttpServletRequest request,ModelMap model){
		List<Base> baseValues = baseService.queryAllBase();
		model.put("baseValues", baseValues);
		String checkStatus=request.getParameter("checkStatus");//这个主要是获得是否是已审核还是未审核
		String baseName =request.getParameter("baseName");
		String li= request.getParameter("li");
		String div= request.getParameter("div");
		String type= request.getParameter("type");
		model.put("checkStatus", checkStatus);
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		model.put("baseName", baseName);
		String grade=request.getParameter("grade");
		List<ArrTurn> arrTurnList=null;
		if(null!=checkStatus && !"".equals(checkStatus)&&null!=baseName && !"".equals(baseName)){
			model.put("grade", grade);
			ArrTurn arrTurnCon = new ArrTurn();
			if(null!=grade && "".equals(grade)){
				arrTurnCon.setGrade(Integer.parseInt(grade));
			}
			arrTurnCon.setBasename(baseName);
//			User userCon= new User();
//			userCon.setBaseName(baseName);
//			userCon.setGrade(grade);
//			arrTurnCon.setBasename(baseName);
//			arrTurnCon.setGrade(grade);
			if("2".equals(type)||"2"==type){
				arrTurnCon.setTrainTime(HIGHTYPE);
			}
			if("3".equals(type)||"3"==type){
				arrTurnCon.setTrainTime(LOWTYPE);
			}
			if ("0".equals(checkStatus)||"0"==checkStatus) {
				arrTurnCon.setCheckStatus(checkStatus);
				arrTurnCon.setBatch(BATCH0);//这个只查询批量的
				arrTurnCon.setLoginName(arrTurnCon.getLoginName());
				arrTurnList=arrTurnService.getArrTurnByArrTurn(arrTurnCon);
			}else{
				arrTurnCon.setCheckStatus(checkStatus);
				arrTurnCon.setBatch(BATCH0);//这个只查询批量的
				arrTurnCon.setLoginName(arrTurnCon.getLoginName());
				arrTurnList=arrTurnService.getArrTurnByCon(arrTurnCon);
			}
//			List<User> userList= userService.getByCondition(userCon);
//			List<ArrTurn> arrTurnList = new ArrayList<ArrTurn>();
//			Map<String,List<ArrTurn>> resultMap = new HashMap<String, List<ArrTurn>>();
//			if ("0".equals(checkStatus)||"0"==checkStatus) {//0是代表未审核
//				arrTurnCon.setCheckStatus(checkStatus);
//				for (User user : userList) {
//					arrTurnCon.setBatch(BATCH0);//这个只查询批量的
//					arrTurnCon.setLoginName(user.getLoginName());
//					arrTurnList=arrTurnService.getArrTurnByCon(arrTurnCon);
//					if(arrTurnList.size() > 0){
//						resultMap.put(user.getLoginName(), arrTurnList);
//					}
//				}
//			}
//			if("1".equals(checkStatus)||"1"== checkStatus){//1代表已审核
//				arrTurnCon.setCheckStatus(checkStatus);
//				for (User user : userList) {
//					arrTurnCon.setBatch(BATCH0);//这个只查询批量的
//					arrTurnCon.setLoginName(user.getLoginName());
//					arrTurnList=arrTurnService.getArrTurnByCon(arrTurnCon);
//					if(arrTurnList.size() > 0){
//						resultMap.put(user.getLoginName(), arrTurnList);
//					}
//				}
//			}
			model.put("resultMap", arrTurnList);
			
		}else{
			model.put("resultMap", null);
			
		}
		return "/arrTurnChe/arrTurnCheckIndex";
	}
	
	
	
	@RequestMapping("/arrTurnCheckController/detail.htm")
	@ResponseBody
	public String queryDetail(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String startTime = request.getParameter("startTime");
		String baseName = request.getParameter("baseName");
		List<ArrturnRule> baseRoomList = arrturnRuleService.getArrTurnRuleByBaseName(baseName);
		model.put("baseRoomList", baseRoomList);
		List<String> timeList = new ArrayList<String>();
		List<String> tmpTimeList = new ArrayList<String>();
		for (int i = 0; i <= 36; i++) {
			String tmpTime = TimeUtils.addMonth(startTime, String.valueOf(i), "yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat mat = new SimpleDateFormat("yyyy年MM月");
			String time = mat.format(new Date(TimeUtils.parseTime(tmpTime, "yyyy-MM-dd HH:mm:ss")));
			timeList.add(time);
			SimpleDateFormat mat2 = new SimpleDateFormat("yyyy-MM");
			String time2 = mat2.format(new Date(TimeUtils.parseTime(tmpTime, "yyyy-MM-dd HH:mm:ss")));
			tmpTimeList.add(time2);
		}
		model.put("timeList", timeList);
		ArrTurn at = new ArrTurn();
		at.setStartTime(Timestamp.valueOf(tmpTimeList.get(0)+"-01 00:00:00"));
		at.setEndTime(Timestamp.valueOf(tmpTimeList.get(tmpTimeList.size()-1)+"-31 00:00:00"));
		at.setBasename(baseName);
		List<ArrTurn> atList = arrTurnService.getArrTurn(at);  //所有的符合条件的记录
		
		Map<String, List<String>> nameMap = new HashMap<String, List<String>>();
		for (int i = 0; i < tmpTimeList.size(); i++) {
			List<String> nameList = new ArrayList<String>();
			for (int j = 0; j < baseRoomList.size(); j++) {
				List<ArrTurn> tmpAtList = new ArrayList<ArrTurn>();
				for (int k = 0; k < atList.size(); k++) {
					String rn = atList.get(k).getRoomName();
					Timestamp st = atList.get(k).getStartTime();
					Timestamp et = atList.get(k).getEndTime();
					Timestamp tmpTime = Timestamp.valueOf(tmpTimeList.get(i)+"-15 00:00:00");
					if(rn.equals(baseRoomList.get(j).getRoomName()) && tmpTime.compareTo(st) > 0 
							&& tmpTime.compareTo(et) < 0){
						tmpAtList.add(atList.get(k));
					}
				}
				if(tmpAtList.size() == 1){
					nameList.add(tmpAtList.get(0).getRealName());
				}else if(tmpAtList.size() <= 0){
					nameList.add("-");
				}else{
					String n = "";
					for (int k = 0; k < tmpAtList.size(); k++) {
						n = n+tmpAtList.get(k).getRealName()+"、";
					}
					n = n.substring(0,n.length()-1);
					nameList.add(n);
				}
			}
			nameMap.put(timeList.get(i), nameList);
		}
		Map<String, List<String>> sortMap = new TreeMap<String, List<String>>(
				new MapKeyComparator());
		sortMap.putAll(nameMap);
		model.put("nameMap", sortMap);
		ArrTurnDetailResult res = new ArrTurnDetailResult();
		res.setBaseRoomList(baseRoomList);
		res.setNameMap(sortMap);
		return JSON.toJSONString(res);
	}
	
	
	//审核不通过	
	@RequestMapping("/arrTureCheckCon/delete.htm")
	public String del(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String loginName = request.getParameter("loginName");
		
		ArrTurn arrTurnCon = new ArrTurn();
		arrTurnCon.setLoginName(loginName);
		arrTurnCon.setCheckStatus(CHECKSTATUSNO);
		try {
			arrTurnService.delByUserNum(arrTurnCon);//虽然byNum但是实际上是根据条件删除的
			ControllerHelper.respOut(response, "true");
		} catch (Exception e) {
			ControllerHelper.respOut(response, "false");
			System.out.println(e);
		}
		return null;
	}
	
	
	//审核通过
	@RequestMapping("/arrTureCheck/update.htm")
	public String update(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String loginName = request.getParameter("loginName");
		ArrTurn arrTurnCon = new ArrTurn();
		arrTurnCon.setLoginName(loginName);
		arrTurnCon.setCheckStatus(CHECKSTATUSNO);
		List<ArrTurn> arrTurnList = arrTurnService.getArrTurnByCon(arrTurnCon);
		try {
			for (ArrTurn arrTurn : arrTurnList) {
				arrTurn.setCheckStatus(CHECKSTATUSIN);
				arrTurnService.checkArrTurn(arrTurn);
			}
			ControllerHelper.respOut(response, "true");
		} catch (Exception e) {
			ControllerHelper.respOut(response, "false");
		}
		
		return null;
	}
	class MapKeyComparator implements Comparator<String>{

		@Override
		public int compare(String str1, String str2) {
			
			return str1.compareTo(str2);
		}
	}
	
}
	
	
