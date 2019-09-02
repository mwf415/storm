package com.youyicn.controller.cycle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.common.DateUtil;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.JudgeT2s;
import com.youyicn.entity.cycle.Room;
import com.youyicn.entity.cycle.SelfSumUp;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ArrTurnService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.JudgeT2SServer;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.service.cycle.SelfSumUpService;
import com.youyicn.util.StringUtils;

/**
 * 老师和学生互评
 */
@Controller
public class JudgeController {
	public static final String STATUSIN ="1";
	public static final String STATUSNO ="0";
	public static final Integer IDENU =2;
	public static final Integer IDENT =1;
	
	@Autowired
	public  JudgeT2SServer judgeT2SServer;
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;

	
	@RequestMapping("/judgeController/index.htm")
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap model
			,String li ,String div ,String menuOrder){
		String pageNum = request.getParameter("pageIndex");
		int pageIndex = 1;
		if(null!=pageNum && !"".equals(pageNum) ){
			pageIndex = Integer.valueOf(pageNum);
		}
		
		JudgeT2s judgeT2sCon = new JudgeT2s();
		List <JudgeT2s> judgeT2sList = new ArrayList<JudgeT2s>();
		User userSession =getSession(request);
		HttpSession session =request.getSession();
		String	path = (String) session.getAttribute("path");
		String loginName = userSession.getLoginName();
		String type = request.getParameter("type");
		model.put("type", type);
		model.put("li", li);
		model.put("div", div);
		model.put("menuOrder", menuOrder);
		String baseName ="";
		String roomName ="";
		String teacherName = request.getParameter("teacherName");
		if(null!=teacherName && ""!= teacherName){
			
			teacherName= "%"+teacherName.trim()+"%";
			judgeT2sCon.setTeacherName(teacherName);
		}	
		String userName = request.getParameter("userName");
		if(null!=userName && ""!= userName){
			userName= "%"+userName.trim()+"%";
			judgeT2sCon.setUserName(userName);
		}	
		List<Base> baseValues =new ArrayList<Base>();
		List<Room> roomValues= new ArrayList<Room>();
		List<User> teacherList = new ArrayList<User>();
		if("h"==type || type.equals("h")){
			 baseValues = baseService.queryAllBase();
			 roomValues = roomService.queryAllRoom();
		}
		if("b"==type || type.equals("b")){
			baseName =userSession.getBaseName();
			teacherList = userService.getTByBase(baseName);
			judgeT2sCon.setBaseName(baseName);
			
		}
		if("r"==type || type.equals("r")){
			roomName = userSession.getRoomName();
			teacherList = userService.getTByRoom(roomName);
			judgeT2sCon.setRoomName(roomName);
		}
		if("t"==type || type.equals("t")){
			judgeT2sCon.setTeacherNum(loginName);
		}
		if("s"==type || type.equals("s")){
			judgeT2sCon.setLoginName(loginName);
		}
		PageHelper.startPage(pageIndex, 15);
		judgeT2sList =judgeT2SServer.getbyCon(judgeT2sCon);
		
		PageInfo<JudgeT2s> page = new PageInfo<JudgeT2s>(judgeT2sList);
		model.put("page", page);
		model.put("baseName", baseName);
		model.put("teacherList", teacherList);
		model.put("roomName", roomName);
		model.put("roomValues", roomValues);
		model.put("baseValues", baseValues);
		if("p".equals(path) || "p"==path){
			return "/phone/kaoping";
		}else{
			return "/judge/index";
		}
	}
	
	/**
	 * 老师对学生的评价
	 * @return
	 */
		
	@RequestMapping("/judgeController/edit.htm")
	public String add(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String id = request.getParameter("id");
		JudgeT2s jt2s = judgeT2SServer.getById(Integer.parseInt(id));
		model.put("jt2s", jt2s);
		
		return "/judge/add";
	}
	
	/**
	 * 老师对学生的评价提交
	 * @return
	 */
	@RequestMapping("/judgeController/sum.htm")
	public String sum(HttpServletRequest request,JudgeT2s jT2s,HttpServletResponse response,ModelMap model){
		jT2s.setCreateTime(DateUtil.date2Timestamp(new Date()));
		jT2s.setIsOver(1);
		try {
			judgeT2SServer.update(jT2s);
		} catch (Exception e) {
			System.out.println("错误"+e);
		}
		return "redirect:/judgeController/index.htm?li=li310&div=div_3&menuOrder=0&type=r";
	}
	/**
	 * 显示评价
	 * @return
	 */
	
	@RequestMapping("/judgeController/show.htm")
	public String show(HttpServletRequest request, String id,HttpServletResponse response,ModelMap model){
		if(null!=id && ""!=id){
			JudgeT2s jT2s = judgeT2SServer.getById(Integer.parseInt(id));
			model.put("jT2s", jT2s);
			return "/judge/view";
		}		
		return null;
	}
	@RequestMapping("/judgeController/showwithuser.htm")
	public String showwithuser(HttpServletRequest request, Integer id,HttpServletResponse response,ModelMap model,String loginName){
		if(StringUtils.NotNull(id)){
			JudgeT2s jT2s = judgeT2SServer.getByAcitvesIdAndUser(id, loginName);
			model.put("jT2s", jT2s);
			return "/judge/view";
		}		
		return null;
	}
	
	
	/**
	 * 学生写的自我总结。
	 */
	@RequestMapping("/selfSum/add.htm")
	public String add (HttpServletRequest request,HttpServletResponse response,ModelMap model ,Integer id,Integer judgeT2sId){
		User user = getSession(request);
		String loginName =user.getLoginName();
		SelfSumUp ssu = selfSumUpService.getByAcitvesIdAndUser(id, loginName);
		model.put("ssu", ssu);
		model.put("judgeT2sId",judgeT2sId);
		return "/judge/ssuadd";
	}
	
	@RequestMapping("/selfSum/sum.htm")
	public String sum(HttpServletRequest request,HttpServletResponse response,ModelMap model,SelfSumUp selfSumUp,Integer judgeT2sId){
		judgeT2SServer.updateSelfStatus(1, judgeT2sId);
		
		selfSumUp.setCreateTime(DateUtil.date2Timestamp(new Date()));
		selfSumUpService.update(selfSumUp);
		return "redirect:/judgeController/index.htm?li=li52&div=div_5&menuOrder=0&type=s";
	}
	
	@RequestMapping("/selfSum/show.htm")
	public String show(HttpServletRequest request, Integer id,HttpServletResponse response,ModelMap model,String loginName ){
		
		SelfSumUp ssu = selfSumUpService.getByAcitvesIdAndUser(id, loginName);
		model.put("ssu", ssu);
		return "/judge/ssuview";
	}
	
	
	//通用的配置都是放在最下面
	private User getSession(HttpServletRequest request) {
		Object obj= request.getSession().getAttribute("loginName");
		User teacher = new User();
		if(obj instanceof String){
			String loginName = (String) obj;
			teacher  = userService.getByNum(loginName);
		}
		return teacher;
	}
	@Autowired
	public UserService userService;
	
	@Autowired
	public ArrTurnService arrTurnService;
	

	@Autowired 
	public SelfSumUpService selfSumUpService;
	
}
