package com.youyicn.controller;

import java.io.UnsupportedEncodingException;
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
import com.youyicn.dao.cycle.ActivesMapper;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.Actives;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.Room;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.util.MD5Utils;
import com.youyicn.util.PageBean;

@Controller
public class UserInfoController {
	public Logger log = LoggerFactory.getLogger(UserInfoController.class);
	 
	@RequestMapping("/userinfo/index.htm")
	public String userInfoIndex(HttpServletResponse response,HttpServletRequest request,ModelMap model,String type,String li,String div) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		List<Base> baseValues = baseService.queryAllBase();
		List<Room> roomValues = roomService.queryAllRoom();
		
		model.put("baseValues", baseValues);
		model.put("roomValues", roomValues);
		
		String grade = request.getParameter("grade");
		String baseId =request.getParameter("baseId");
		String roomId = request.getParameter("roomId"); 
		String userName = request.getParameter("userName");
		String pageNum = request.getParameter("pageIndex");
		int pageIndex = 1;
		if(null!=pageNum && !"".equals(pageNum) ){
			pageIndex = Integer.valueOf(pageNum);
		}
		PageHelper.startPage(pageIndex, 15);
		User userCon = new User();
		
		if(!"".equals(grade)&&  !"undefined".equals(grade) && null != grade){
			userCon.setGrade(Integer.parseInt(grade));
		}
		
		if(!"".equals(userName)&& null != userName){
			userCon.setRealName("%"+ userName+"%");
		}
		userCon.setIdentityId(Integer.parseInt(type));
		model.put("userName", userName);
		model.put("type", type);
		model.put("li", li);
		model.put("div", div);
		List<User> userList = userService.getByCondition(userCon);
		PageBean<User> page = new PageBean<User>(userList);
		model.put("page", page);
		return "/info/user";
	}
	
	
	@RequestMapping("/userinfo/changepwd.htm")
	public String changePwd(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		HttpSession session = request.getSession();
		String loginName = (String) session.getAttribute("loginName");
		User user = userService.getByNum(loginName);
		model.put("user", user);
		return "/info/changepwd";
	}
	@RequestMapping("/userinfo/changepwdsum.htm")
	public String changePwdsum(HttpServletRequest request,HttpServletResponse response,ModelMap model ,String loginName,String userPwd){
		userPwd = MD5Utils.md5(userPwd);
		
		User user = userService.getByNum(loginName);
		user.setUserPwd(userPwd);
		userService.editUser(user);
		return "redirect:/index/indexlist.htm";
	}
	
	@Autowired
	public UserService userService;
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;
	@Autowired
	private ActivesMapper activesMapper;
	
	 
}
