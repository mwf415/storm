package com.youyicn.controller.cycle.arrturn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youyicn.entity.cycle.ArrturnRule;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.Room;
import com.youyicn.service.cycle.ArrturnRuleService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.util.ControllerHelper;
/**
 * 本科生轮转规则的controller
 */
@Controller
public class ArrTurnRuleController {
	public static final Integer LOWTYPE=3;//本科生 
	public static final Integer HIGHTYPE=2; //研究生
	
	
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;
	
	@Autowired
	public ArrturnRuleService arrTurnRuleService;
	@Autowired
	public ArrturnRuleService arrturnRuleService;
	
	@RequestMapping("/arrTurnController/arrturnRule_Index.htm")
	public String arrTurnRuleIndex(HttpServletRequest req,ModelMap map){
		String li= req.getParameter("li");
		String div= req.getParameter("div");
		String type= req.getParameter("type");
		
		String baseName = req.getParameter("baseName");
		List<Base> baseValues = baseService.queryAllBase();
		List<Room> roomValues = roomService.queryAllRoom();
		if(null!=baseName && ""!=baseName){
			ArrturnRule arrTurnRuleCon = new ArrturnRule();
			arrTurnRuleCon.setBaseName(baseName);
			arrTurnRuleCon.setType(Integer.parseInt(type));
			List<ArrturnRule> atrList = arrTurnRuleService.getArrTurnByCon(arrTurnRuleCon);
			map.put("atrList", atrList);
		}
		map.put("baseName", baseName);
		map.put("baseValues", baseValues);
		map.put("roomValues", roomValues);
		map.put("li", li);
		map.put("div", div);
		map.put("type", type);
		
		return "/arrTurnRule/arrTurnRuleIndex";
	}
	/**
	 * 轮转规则入库
	 * @param req
	 * @param map
	 * @return
	 */
	@RequestMapping("/arrTurnOneController/arrturnRuleInsertone.htm")
	public String arrTurnRuleInsert(HttpServletRequest req,HttpServletResponse resp, ModelMap map){
		String li= req.getParameter("li");
		String  div= req.getParameter("div");
		
		ArrturnRule rule = new ArrturnRule();
		String roomName = req.getParameter("roomName");
		String period = req.getParameter("period");
		String roomSort1 = req.getParameter("roomSort");
		if(""!=roomSort1 && null!=roomSort1){
			rule.setRoomSort(Integer.parseInt(roomSort1));
		}
		String baseName =req.getParameter("baseName");
		String type = req.getParameter("type");
		if(type=="3"||type.equals("3")){
			rule.setType(LOWTYPE);
		}
		if(type=="2"||type.equals("2")){
			rule.setType(HIGHTYPE);
		}
		rule.setBaseName(baseName);
		rule.setPeriod(period);
		rule.setRoomName(roomName);
		try {
			arrturnRuleService.insertRule(rule);
			map.put("baseName", baseName);
			map.put("li", li);
			map.put("div", div);
			map.put("type", type);
			ControllerHelper.respOut(resp, "true");
		} catch (Exception e) {
			e.printStackTrace();
			ControllerHelper.respOut(resp, "false");
		}
		return null;
	}
//	@RequestMapping("/arrTurnRuleOneController/editone.htm")
//	public String edit(HttpServletRequest request,HttpServletResponse response,ModelMap model){
//		String id1= request.getParameter("id");
//		String li= request.getParameter("li");
//		String  div= request.getParameter("div");
//		String type= request.getParameter("type");
//		String baseName =request.getParameter("baseName");
//		model.put("baseName", baseName);
//		model.put("li", li);
//		model.put("div", div);
//		model.put("type", type);
//		if(null!=id1 && ""!=id1){
//			ArrturnRule arrTurnRule = arrturnRuleService.getArrTurnRuleById(Integer.parseInt(id1));
//			model.put("arrTurnRule", arrTurnRule);
//		}
//		
//		return "/arrTurnRule/arrTurnRuleEdit";
//	}	
	//删除
	@RequestMapping("/arrTurnOneController/arrturnRuledelone.htm")
					 
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
	//跟新规则
	@RequestMapping("/arrTurnRuleController/arrturnRuleupdate.htm")
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
	
	
	//重新排序和修改时间
		@RequestMapping("/arrTurnRuleController/sortChange.htm")
		public String changeSort(HttpServletRequest request,HttpServletResponse response,ModelMap model){
			String li= request.getParameter("li");
			String  div= request.getParameter("div");
			String type= request.getParameter("type");
			String baseName =request.getParameter("baseName");
			model.put("li", li);
			model.put("div", div);
			model.put("type", type);
			model.put("baseName", baseName);
			ArrturnRule arrTurnRuleCon = new ArrturnRule();
			arrTurnRuleCon.setBaseName(baseName);
			arrTurnRuleCon.setType(Integer.parseInt(type));
			List<ArrturnRule> arrTurnRuleList = arrTurnRuleService.getArrTurnByCon(arrTurnRuleCon);
			try {
				for (ArrturnRule arrturnRule : arrTurnRuleList) {
					String id1 =arrturnRule.getId()+"";
					String roomSort = request.getParameter(id1);
					String period =request.getParameter(id1+"p");
					
					ArrturnRule arrTurnRule = arrTurnRuleService.getArrTurnRuleById(Integer.parseInt(id1));
					arrTurnRule.setPeriod(period);
					arrTurnRule.setRoomSort(Integer.parseInt(roomSort));
					arrTurnRuleService.upDateArrTurnRule(arrTurnRule);
				}
				ControllerHelper.respOut(response, true);
			} catch (Exception e) {
				ControllerHelper.respOut(response, false);
			}
			return null;
		}
	
}
