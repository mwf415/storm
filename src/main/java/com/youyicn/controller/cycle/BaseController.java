package com.youyicn.controller.cycle;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.youyicn.entity.cycle.Base;
import com.youyicn.service.cycle.BaseService;

@Controller
public class BaseController {
	
	private Logger logger = Logger.getLogger(BaseController.class);
	
	@Autowired
	private BaseService baseService;

	
	@RequestMapping("/baseController/rIndex.htm")
	public String queryindex(HttpServletRequest req, HttpServletResponse resp,ModelMap model){
		
		List<Base> baseRoomValues = baseService.queryAllBase();
		model.put("baseRoomValues", baseRoomValues);
		return "/addbr/baseindex";
	}	
	@RequestMapping("/baseController/queryBase.htm")
	public String queryBase(HttpServletRequest req, HttpServletResponse resp){
		List<Base> baseList = baseService.queryAllBase();
		String res = JSONArray.toJSONString(baseList);
		try {
			resp.getWriter().write(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
