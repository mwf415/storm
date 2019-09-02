package com.youyicn.controller.cycle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.youyicn.entity.cycle.CyleNote;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ArrTurnService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.CyleNoteService;
import com.youyicn.service.cycle.JudgeT2SServer;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.service.cycle.SelfSumUpService;
import com.youyicn.util.StringUtils;

/**
 * 老师和学生互评
 */
@Controller
public class CyleNoteController {
	@Autowired
	public  JudgeT2SServer judgeT2SServer;
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;
	@Autowired
	public CyleNoteService cyleNoteService;

	
	@RequestMapping("/cyleNoteController/index.htm")
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap model
			,String li ,String div ,String menuOrder,String type){
		HttpSession session = request.getSession();
		session.setAttribute("li", li);
		session.setAttribute("div", div);
		session.setAttribute("menuOrder", menuOrder);
		session.setAttribute("type", type);
		model.put("type", type);
		model.put("li", li);
		model.put("div", div);
		model.put("menuOrder", menuOrder);
		User userSession =getSession(request);
		String arrTurnId = request.getParameter("arrTurnId");
		ArrTurn arrTunCon = new ArrTurn();
		List<CyleNote> cyleNoteList  = new ArrayList<CyleNote>();
		
		if("s".equals(type)){
			String roomName = request.getParameter("roomName");
			model.put("roomName", roomName);
			
			//把该学生的所有轮转科室在前台显示出来
			String loginName = userSession.getLoginName();
			arrTunCon.setLoginName(loginName);
			List <ArrTurn> arrTurnList = arrTurnService.getArrTurnByCon(arrTunCon);
			model.put("arrTurnList", arrTurnList);
			
			//如果选择了科室，那么就找这个科室下的学习记录
			if(StringUtils.NotNull(arrTurnId)){
				cyleNoteList = cyleNoteService.getCyleNoteByArrTurnID(Integer.parseInt(arrTurnId));
			}
			model.put("arrTurnId", arrTurnId);	
			model.put("cyleNoteList", cyleNoteList);
			return "/cylenote/index";
		}else if ("r".equals(type)){
			String time = request.getParameter("searchStart");
			String roomName = userSession.getRoomName();
			arrTunCon.setRoomName(roomName);
			if(StringUtils.NotNull(time)){
				arrTunCon.setSearchStart(DateUtil.str2TimestampD(time));
			}else{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Calendar rightNow = Calendar.getInstance();
				String searchtime = sdf.format(rightNow.getTime());
				arrTunCon.setSearchStart(DateUtil.str2TimestampM(searchtime));
			}
			List<ArrTurn> arrTrunList = arrTurnService.getThisMonthUser(arrTunCon);
			model.put("arrTrunList", arrTrunList);
			return "/cylenote/rindex";
		}
		
		return null;
		
	}
	
	@RequestMapping("/cyleNoteController/noteview.htm")
	public String show(HttpServletRequest request, Integer id,HttpServletResponse response,
			ModelMap model,String loginName,Integer arrTurnId ){
		List<CyleNote> cyleNoteList  = new ArrayList<CyleNote>();
		if(StringUtils.NotNull(arrTurnId)){
			cyleNoteList = cyleNoteService.getCyleNoteByArrTurnID(arrTurnId);
		}
		model.put("arrTurnId", arrTurnId);	
		model.put("cyleNoteList", cyleNoteList);
		return "/cylenote/box";
	}
	
	/**
	 * 
	 */
	@RequestMapping("/cyleNoteController/add.htm")
	public String add (HttpServletRequest request,HttpServletResponse response,ModelMap model ,Integer id,Integer judgeT2sId){
		User userSession =getSession(request);
		String loginName = userSession.getLoginName();
		ArrTurn arrTunCon = new ArrTurn();
		arrTunCon.setLoginName(loginName);
		List <ArrTurn> arrTurnList = arrTurnService.getArrTurnByCon(arrTunCon);
		model.put("arrTurnList", arrTurnList);
		
		return "/cylenote/add";
	}
	
	/**
	 * 填写提交
	 * @param request
	 * @param response
	 * @param model
	 * @param cyleNote
	 * @return
	 */
	@RequestMapping("/cyleNoteController/sum.htm")
	public String sum(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		
		
		
		String sicknum = request.getParameter("sicknum");
		String indate = request.getParameter("indate");
		String sickdes = request.getParameter("sickdes");
		String skillname= request.getParameter("skillname");
		String starttime = request.getParameter("starttime");
		String sickdetail= request.getParameter("sickdetail");
		String actrole= request.getParameter("actrole");
		
		CyleNote cyleNote= new CyleNote();
		cyleNote.setSicknum(sicknum);
		cyleNote.setIndate(DateUtil.str3Timestamp(indate, "yyyy-MM-dd HH:mm:ss"));
		cyleNote.setSickdes(sickdes);
		cyleNote.setSkillname(skillname);
		cyleNote.setStarttime(DateUtil.str3Timestamp(starttime, "yyyy-MM-dd HH:mm:ss"));
		cyleNote.setSickdetail(sickdetail);
		cyleNote.setActrole(actrole);
		
		String selectRoom = request.getParameter("selectRoom");
		String parm[] = selectRoom.split(":");
		String arrTrunIdString = parm[0];
		cyleNote.setArrturnid(Integer.parseInt(arrTrunIdString));
		
		cyleNoteService.add(cyleNote);
		HttpSession session = request.getSession();
		String li =(String) session.getAttribute("li");
		String div = (String) session.getAttribute("div");
		String menuOrder = (String) session.getAttribute("menuOrder");
		String type = (String) session.getAttribute("type");
		return "redirect:/cyleNoteController/index.htm?li="+li+"&div="+div+"&menuOrder="+menuOrder+"&type="+type;
	}
	
	/**
	 * 老师确认
	 * @return
	 */
	@RequestMapping("/cyleNoteController/update.htm")
	public String update(HttpServletRequest request,HttpServletResponse response,ModelMap model,CyleNote cyleNote){
		String selectRoom = request.getParameter("selectRoom");
		String parm[] = selectRoom.split(":");
		String arrTrunIdString = parm[0];
		cyleNote.setArrturnid(Integer.parseInt(arrTrunIdString));
		
		cyleNoteService.add(cyleNote);
		HttpSession session = request.getSession();
		String li =(String) session.getAttribute("li");
		String div = (String) session.getAttribute("div");
		String menuOrder = (String) session.getAttribute("menuOrder");
		String type = (String) session.getAttribute("type");
		return "redirect:/cyleNoteController/index.htm?li="+li+"&div="+div+"&menuOrder="+menuOrder+"&type="+type;
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
