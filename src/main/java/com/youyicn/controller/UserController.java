package com.youyicn.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.entity.User;
import com.youyicn.entity.UserRole;
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.Room;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ArrTurnService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.service.cycle.UserRoleService;
import com.youyicn.util.ArrTurnExcelUtils;
import com.youyicn.util.ControllerHelper;
import com.youyicn.util.MD5Utils;
import com.youyicn.util.StudentExcelUtils;

@Controller
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	public UserService userService;
	@Autowired
	public ArrTurnService arrTurnService;

	@Autowired
	public UserRoleService userRoleService;
	
	
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;
	/**
	 *  这里主要是用来渲染页面的
	 */
	@RequestMapping("/user/index.htm")
	public String accountIndex(HttpServletRequest request,
			HttpServletResponse response, Integer menuOrder ,ModelMap model,String li ,String div,Integer type) {
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		HttpSession session = request.getSession();
		session.setAttribute("menuOrder", menuOrder);
		session.setAttribute("li", li);
		session.setAttribute("div", div);
		session.setAttribute("type", type);

		String pageNum = request.getParameter("pageIndex");
		String userName = request.getParameter("userName");
		String baseName = request.getParameter("baseName");
		String grade1 = request.getParameter("grade");
		String roomName = request.getParameter("roomName");
	
		int grade =0 ;
		if(null!= grade1 && ""!= grade1){
			grade = Integer.parseInt(grade1);
		}	
		model.put("userName", userName);
		int pageIndex = 1;
		
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
		user.setIdentityId(type);
		model.put("type", type);
		List<User> userList=userService.getByCondition(user); 
		PageInfo<User> page = new PageInfo<User>(userList);
		model.put("page", page);
		List<Base> baseValues = baseService.queryAllBase();
		List<Room> roomValues = roomService.queryAllRoom();
		model.put("baseValues", baseValues);
		model.put("roomValues", roomValues);
		
		return "/user/user_index";
	}
	/**
	 * 审核注册的用户
	 */
	@RequestMapping("/user/cheRegist.htm")
	public String check(HttpServletRequest request,HttpServletResponse response, Integer menuOrder ,ModelMap model,String li ,String div,Integer status){
		model.put("li", li);
		model.put("div", div);
		model.put("status", status);
		HttpSession session = request.getSession();
		session.setAttribute("menuOrder", menuOrder);
		session.setAttribute("li", li);
		session.setAttribute("div", div);
		
		
		List<User> userList = userService.getUnCheUser(status);
		model.put("userList", userList);
		
		return "/user/userChe";
		
	}
	
	
	@RequestMapping("/userController/userpass.htm")
	public String passUser(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer userId){
		
		User user = userService.getbyId(userId);
		user.setStatus(1);
		userService.updateUserStatus(user);	
		
		return "redirect:/user/cheRegist.htm?status=0";
	}

	/**
	 * 用户添加页面
	 */
//	@RequestMapping("/userContrller/user_add.htm")
//	public String addUser(HttpServletRequest request,HttpServletResponse response,ModelMap model){
//		List<Base> baseValues = baseService.queryAllBase();
//		List<Room> roomValues = roomService.queryAllRoom();
//		model.put("roomValues", roomValues);
//		model.put("baseValues", baseValues);
//		return "/user/user_add";
//		
//	}
	
	/**
	 * 添加用户提交
	 */
	@RequestMapping("/userContrller/user_submit.htm")
	@ResponseBody
	public int userSubmit(HttpServletRequest request,User user,HttpServletResponse response, ModelMap model){
		try {
			String password = user.getUserPwd();
			password = MD5Utils.md5(password);
			user.setUserPwd(password);
			user.setIsAt(0);
			user.setStatus(0);
			userService.insertSelective(user);
			int id = user.getId();
			if(1 ==user.getIdentityId()){
				UserRole ur =new UserRole();
				ur.setRid(8);
				ur.setUid(id);
				userRoleService.insert(ur);
			}
			return 1;
		}catch(Exception e){
			logger.info("添加用户错误"+e);
			return 0;
		}
	}
	
	/**
	 * 用户详情 
	 */
	@RequestMapping("/userController/showDetail.htm")
	public String userDetail(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String userId1= request.getParameter("userId");
		User user =new User();
		if(userId1!=""&&userId1!=null){
			Integer userId= Integer.parseInt(userId1);
			user= userService.getbyId(userId);
			model.put("user", user);
		}
		return "/user/userdetail";
	}
	
	/**
	 * 展示用户详情并可以修改 
	 */
	@RequestMapping("/userController/user_edit.htm")
	public String userEdit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String userId1=request.getParameter("userId");
		
		List<Base> baseValues = baseService.queryAllBase();
		List<Room> roomValues = roomService.queryAllRoom();
		model.put("baseValues", baseValues);
		model.put("roomValues", roomValues);
		
		User user= new User();
		if(userId1!=null&& userId1 !=null){
			Integer  userId= Integer.parseInt(userId1);
			user.setId(userId);
			user=userService.getbyId(userId);
			model.put("user", user);
		}	
		return "/user/user_edit";
	}
	
	/**
	 * 展示用户详情并可以修改 
	 */
	@RequestMapping("/userController/self_edit.htm")
	public String userEditSelf(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,String li ,String div) {
		model.put("li", li);
		model.put("div", div);
		HttpSession session = request.getSession(true);
		String loginName= (String) session.getAttribute("loginName");
		
		List<Base> baseValues = baseService.queryAllBase();
		List<Room> roomValues = roomService.queryAllRoom();
		model.put("baseValues", baseValues);
		model.put("roomValues", roomValues);
		
		User user= userService.getByNum(loginName);
		model.put("user", user);
		return "/user/self_edit";
	}
	
	
	
	@RequestMapping("/userController/self_update.htm")
	public String selfUpdate(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
	
		String loginName= request.getParameter("loginName");
		String userPwd= request.getParameter("userPwd");
		userPwd = MD5Utils.md5(userPwd);
		
		String baseName = request.getParameter("baseName");
		String roomName = request.getParameter("roomName");
		
		String email= request.getParameter("email");		
		User user= userService.getByNum(loginName);
			
		
		user.setUserPwd(userPwd);
		
		user.setBaseName(baseName);
		user.setRoomName(roomName);
		userService.editUser(user);			
		return "redirect:/userController/self_edit.htm?li=li03&div=div_0";			
	}
	
	
	/**
	 * 修改上传 
	 */
	@RequestMapping("/userController/user_update.htm")
	public String teacherUpdate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int userId= Integer.parseInt(request.getParameter("userId"));
		String loginName= request.getParameter("loginName");
		String userName= request.getParameter("userName");
		String userPass= request.getParameter("userPass");
		String sex= request.getParameter("sex");
		String address= request.getParameter("address");
		String gradSchool= request.getParameter("gradSchool");
		String grade= request.getParameter("grade");
		String status= request.getParameter("status");//身份类别
		String teacher2= request.getParameter("teacher2");//身份类别
		String teacher1= request.getParameter("teacher1");//身份类别
		String major= request.getParameter("major");
		String cardId= request.getParameter("cardId");
		String telPhone= request.getParameter("telPhone");
		String cellPhone= request.getParameter("cellPhone");
		String baseName = request.getParameter("baseName");
		String roomName = request.getParameter("roomName");
		String xuewei= request.getParameter("xuewei");
		String degree= request.getParameter("degree");
		String wechat= request.getParameter("wechat");
		String qq= request.getParameter("qq");
		String email= request.getParameter("email");
		String identityId = request.getParameter("identityId");
		
		
		User user= userService.getbyId(userId);
		try {
			user.setIdentityId(Integer.parseInt(identityId));
			//user.setUserId(userId);
			user.setRealName(userName);
			user.setUserNum(loginName);
			userPass= MD5Utils.md5(userPass);
			user.setUserPwd(userPass);
			
			user.setAddress(address);
			user.setGradSchool(gradSchool);
			if(null!= grade && ""!= grade){
				user.setGrade(Integer.parseInt(grade));
			}
			user.setStatus(Integer.parseInt(status));
			user.setTeacher1(teacher1);
			user.setTeacher2(teacher2);
			user.setMajor(major);
			user.setCardId(cardId);
			user.setTelPhone(telPhone);
			user.setCellPhone(cellPhone);
			user.setBaseName(baseName);
			user.setRoomName(roomName);
			user.setSex(sex);
			user.setEmail(email);
			user.setQq(qq);
			user.setWechat(wechat);
			user.setDegree(degree);
			user.setXuewei(xuewei);
			userService.editUser(user);
			ControllerHelper.respOut(response, true);
		} catch (Exception e) {
			ControllerHelper.respOut(response, false);
		}
	
		return null;
		
	}
	/**
	 *根据用户id删除用户 
	 */
	@RequestMapping("/userContrller/userdel.htm")
	public String delUser(HttpServletRequest request,HttpServletResponse response, ModelMap model,
			Integer menuOrder ,String li ,String div,Integer status){
		
		String id1= request.getParameter("userId");
		Integer id =Integer.parseInt(id1);
		try {
			userService.delUser(id);
		} catch (Exception e) {
			logger.info("删除用户失败"+e);
		}
		if("li10".equals(li)){			
			return "redirect:/user/index.htm?li=li10&div=div_1&type=1";	
		}else{
			return "redirect:/user/cheRegist.htm?li=li14&div=div_1&status=0";	
		}
	}
	
	@RequestMapping("/userContrller/insertStu_index.htm")
	public String insertStuIndex(HttpServletRequest request,String li ,String div,ModelMap model){
		model.put("li", li);
		model.put("div", div);
		return "/user/insertStu_index";
	}
	
	/**
	 * excel 导入用户
	 * @param request
	 * @param file
	 * @return
	 */	 
	@RequestMapping("/userContrller/insertAllStudents.htm")
	public String insertAllStudents(HttpServletRequest request, @RequestParam(value="file", required=false) MultipartFile file){
		StudentExcelUtils excelUtil = StudentExcelUtils.getInstance();
		List<User> studentList = null;
		try {
			File tmpFile = File.createTempFile("TEMPFILE" + System.currentTimeMillis(), ".xls");
			file.transferTo(tmpFile);
			String tmpPath = tmpFile.getAbsolutePath();
			studentList = excelUtil.readXls(tmpPath);
			for (User stu : studentList) {
				stu.setIsAt(0);
				stu.setStatus(1);
				userService.addUser(stu);
			}
			tmpFile.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/userContrller/insertStu_index.htm?li=li16&div=div_1&menuOrder=1";
	}
	
	@RequestMapping("/userContrller/insertarrTurn.htm")
	public String insertarrTurn(HttpServletRequest request, @RequestParam(value="file", required=false) MultipartFile file){
		ArrTurnExcelUtils excelUtil = ArrTurnExcelUtils.getInstance();
		List<ArrTurn> arrtList = null;
		try {
			File tmpFile = File.createTempFile("TEMPFILE" + System.currentTimeMillis(), ".xls");
			file.transferTo(tmpFile);
			String tmpPath = tmpFile.getAbsolutePath();
			arrtList = excelUtil.readXls(tmpPath);
			for (ArrTurn stu : arrtList) {
				arrTurnService.addArrTurn(stu);
			}
			tmpFile.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/userContrller/insertStu_index.htm?li=li16&div=div_1&menuOrder=1";
	}
	
	@RequestMapping("/userContrller/checkLoginName.htm")
	@ResponseBody
	public String checkLoginName(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String loginName = request.getParameter("loginName");
		User user = userService.getByNum(loginName);
		
		if(null!=user){
			return "0";
		}else {
			return "1";
		}
		
	}

	
	
}
