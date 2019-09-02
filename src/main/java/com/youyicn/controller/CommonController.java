package com.youyicn.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.Room;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.RoomService;


@Controller
public class CommonController {
	@Autowired
	public BaseService baseService;
	@Autowired
	public RoomService roomService;
	
	@RequestMapping("/commonController/add.htm")
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap model,String li,String div){
		model.put("li", li);
		model.put("div", div);
	
		List<Base> baseList= baseService.queryAllBase();
		List<Room> roomList = roomService.queryAllRoom();
		model.put("roomList", roomList);
		model.put("baseList", baseList);
		return "common/index";
	}
	
	@RequestMapping("/commonController/addBase.htm")
	public String addBase(HttpServletRequest request,HttpServletResponse response,ModelMap model,String baseName,Integer baseNum){
		try {
			if(null!=baseName && ""!= baseName){
				Base base= new Base();
				base.setBaseNum(baseNum);
				base.setValue(baseName);
				baseService.addBase(base);
			}
		} catch (Exception e) {
			
		}
		
		return "redirect:/commonController/add.htm?li=li16&div=div_1&menuOrder=1";
	} 
	@RequestMapping("/commonController/addRoom.htm")
	public String addroom(HttpServletRequest request,HttpServletResponse response,ModelMap model,String roomName ,Integer roomNum){
		System.out.println("aaaaaaa");
		try {
			if(null!=roomName && ""!= roomName){
				Room room= new Room();
				room.setRoomNum(roomNum);
				room.setValue(roomName);
				roomService.addRoom(room);
			}
		} catch (Exception e) {
			
		}
		
		
		return "redirect:/commonController/add.htm?li=li16&div=div_1&menuOrder=1";
	} 
	
	/**
	 * @return
	 */
	//删除科室和基地
	@RequestMapping("/commonController/del.htm")
	public String del(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer type,Integer id){
		try {
			if(type ==0 ){
				baseService.del(id);
			}
			else if(type ==1 ){
				roomService.del(id);
			}
		} catch (Exception e) {			
		}	
		
		return "redirect:/commonController/add.htm?li=li16&div=div_1&menuOrder=1";
	} 
	
	//修改科室和基地
	//删除科室和基地
	@RequestMapping("/commonController/update.htm")
	public String update(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer type,Integer id){
		try {
			if(type ==0 ){
				model.put("type", type);
				Base base =baseService.getById(id);
				model.put("entity", base);
			}
			else if(type ==1 ){
				model.put("type", type);
				Room room =roomService.getById(id);
				model.put("entity", room);
			}
		} catch (Exception e) {			
		}	
		
		return "/common/update";
	} 
	
	
	
	@RequestMapping(value = "base/list.htm")
	@ResponseBody
	public String baseList(){
		List<Base> baseList = baseService.queryAllBase();

		String baseStr = JSONObject.toJSONString(baseList);
		
		return baseStr;
		
	}
	@RequestMapping(value = "room/list.htm")
	@ResponseBody
	public String roomList(){
		List<Room> roomList = roomService.queryAllRoom();
		
		String roomStr = JSONObject.toJSONString(roomList);
		
		return roomStr;
	}
	
	
	
}
