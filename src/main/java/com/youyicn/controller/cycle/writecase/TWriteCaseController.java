package com.youyicn.controller.cycle.writecase;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.Room;
import com.youyicn.entity.cycle.WriteCase;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.service.cycle.WriteCaseServ;
import com.youyicn.util.PageBean;

@Controller
public class TWriteCaseController {


	private Logger logger = Logger.getLogger(TWriteCaseController.class);
	@Autowired
	public WriteCaseServ writeCaseServ;
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;
	@RequestMapping("/twriteCaseCont/writeCase.htm")//这个是从页面发送的，带有sitmas
	public String writeCase(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		
		getList(request, model);

		return "/writecase/tindex";
	}
	@RequestMapping("/twriteCaseCont/writeCaseindex.htm")// 这个重新发送的  没有sitmash
	public String writeCaseIndex(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		
		getList(request, model);

		return "/writecase/tindex";
	}

	private void getList(HttpServletRequest request, ModelMap model) {
		String pageNum = request.getParameter("pageIndex");
		String loginName = request.getParameter("loginName");
		String baseName = request.getParameter("baseName");
		int pageIndex = 1;
		if(null!=pageNum && !"".equals(pageNum) ){
			pageIndex = Integer.valueOf(pageNum);
		}
		PageHelper.startPage(pageIndex, 10);
		WriteCase writeCase =new WriteCase();
		if(null != loginName && !"".equals(loginName)){
			writeCase.setUserNum(loginName);
		}
		if(null != baseName && !"".equals(baseName)){
			writeCase.setBaseName(baseName);
		}
		List<WriteCase> writeCaseList= writeCaseServ.getWriteCaseByCon(writeCase); 
		//model.put("writeCaseList", writeCaseList);
		PageBean<WriteCase> page = new PageBean<WriteCase>(writeCaseList);
		model.put("page", page);
		List<Base> baseValues = baseService.queryAllBase();
		model.put("baseValues", baseValues);
	}
	
	private User getSession(HttpServletRequest request) {
		Object obj= request.getSession().getAttribute("loginName");
		User teacher = new User();
		if(obj instanceof String){
			String loginName = (String) obj;
			teacher  = UserService.getByNum(loginName);
		}
		return teacher;
	}

	@Autowired
	public UserService UserService;
	
}
