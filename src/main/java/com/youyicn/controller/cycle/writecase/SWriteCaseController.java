package com.youyicn.controller.cycle.writecase;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.youyicn.common.DateUtil;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.Room;
import com.youyicn.entity.cycle.WriteCase;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.service.cycle.WriteCaseServ;
import com.youyicn.util.ControllerHelper;
import com.youyicn.util.PageBean;

@Controller
public class SWriteCaseController {


	private Logger logger = Logger.getLogger(SWriteCaseController.class);
	@Autowired
	public WriteCaseServ writeCaseServ;
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;
	@RequestMapping("/swriteCaseCont/writeCaseindex.htm")
	public String writeCaseIndex(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		
		String pageNum = request.getParameter("pageIndex");
		String loginName = request.getParameter("loginName");
		int pageIndex = 1;
		if(null!=pageNum && !"".equals(pageNum) ){
			pageIndex = Integer.valueOf(pageNum);
		}
		PageHelper.startPage(pageIndex, 10);
		WriteCase writeCase =new WriteCase();
		if(null != loginName && !"".equals(loginName)){
			writeCase.setUserNum(loginName);
		}

		List<WriteCase> writeCaseList= writeCaseServ.getWriteCaseByCon(writeCase); 
		//model.put("writeCaseList", writeCaseList);
		PageBean<WriteCase> page = new PageBean<WriteCase>(writeCaseList);
		model.put("page", page);
		
		List<Base> baseValues = baseService.queryAllBase();
		model.put("baseValues", baseValues);

		return "/writecase/sindex";

		      
	}
	@RequestMapping("/swriteCaseCont/writecaseadd.htm")
	public String writeCaseAdd(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		
		
		
		return "/writecase/sadd";
	}
	@RequestMapping("/swriteCaseCon/writeCasesubmit.htm")
	public String writeCaseSum(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		User user = getuserSession(request);
		String illName  = request.getParameter("illName");
		String illSex  = request.getParameter("illSex");
		String illAge  = request.getParameter("illAge");
		String illRoom = request.getParameter("illRoom");
		String illBedNum  = request.getParameter("illBedNum");
		String illNum  = request.getParameter("illNum");
		
		
		String shortAdvice  = request.getParameter("shortAdvice");
		String  longAdvice = request.getParameter("longAdvice");
		String caseText  = request.getParameter("caseText");
		
		WriteCase writeCase= new WriteCase();
		try {
			writeCase.setName(user.getLoginName());
			writeCase.setUserNum(user.getLoginName());
			
			writeCase.setIllName(illName);
			writeCase.setIllNum(illNum);
			writeCase.setIllSex(illSex);
			writeCase.setIllBedNum(illBedNum);
			writeCase.setIllRoom(illRoom);
			writeCase.setIllAge(illAge);
			
			writeCase.setShortAdvice(shortAdvice);
			writeCase.setLongAdvice(longAdvice);
			writeCase.setCaseText(caseText);
			writeCase.setBaseName(user.getBaseName());
			writeCase.setCreateTime(DateUtil.date2Timestamp(new Date()));
			
			int a =writeCaseServ.insertWriteCase(writeCase);
			ControllerHelper.respOut(response, true);
		} catch (Exception e) {
			ControllerHelper.respOut(response, false);
			logger.info("添加书写病例出现问题"+e);
		}
		
		return null;
	}
	
	@RequestMapping("/writeCase/writeCaseDel.htm")
	public String delTeacher(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String id1= request.getParameter("id");
		if(null != id1 && !"".equals(id1)){
			int id = Integer.parseInt(id1);
			try {
				writeCaseServ.delWriteCase(id);
				ControllerHelper.respOut(response, "true");
			} catch (Exception e) {
				e.printStackTrace();
				ControllerHelper.respOut(response, "false");
			}
		}
	
		return null;
	}
	
	//通用的配置都是放在最下面
	private User getuserSession(HttpServletRequest request) {
		Object obj= request.getSession().getAttribute("loginName");
		User user = new User();
		if(obj instanceof String){
			String loginName = (String) obj;
			user  = userService.getByNum(loginName);
		}
		return user;
	}
	
	@Autowired
	public UserService userService;
	
}
