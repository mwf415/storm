package com.youyicn.controller.cycle;

import java.io.IOException;
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

import com.youyicn.entity.User;
import com.youyicn.entity.cycle.Actives;
import com.youyicn.entity.cycle.ActivesQuest;
import com.youyicn.entity.cycle.ActivesQuestUser;
import com.youyicn.entity.cycle.ActivesUser;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ActivesQuestService;
import com.youyicn.service.cycle.ActivesQuestUserService;
import com.youyicn.service.cycle.ActivesService;
import com.youyicn.service.cycle.ActivesUserService;

@Controller
public class ActivesQuestController {
	public Logger log = LoggerFactory.getLogger(ActivesQuestController.class);
	public static final Integer from_t=0; //问题   ：如果是老师  用0 
	public static final Integer from_u=1;//如果是学生  用1
	public static final Integer ISIN=1; //问题   ：如果是老师  用0 
	public static final Integer NOIN=0; //问题   ：如果是老师  用0 
	@Autowired
	public ActivesQuestService questService;
	
	@RequestMapping("/questController/ansActivesQuest.htm")
	public String getActivesQuest(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String lecNum = request.getParameter("randomNum");
		ActivesQuest quest = questService.getActivesQuestById(Integer.parseInt(lecNum));
		model.put("quest", quest);
		return "/quest/questans";
	}
	
	//老师的的问题添加
	@RequestMapping("/questController/add.htm")
	public String qusetadd(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer activesId){
		HttpSession session = request.getSession();
		Integer status =(Integer) session.getAttribute("status");
		String type =(String) session.getAttribute("type");
		model.put("type", type);
		model.put("status", status);
		List<ActivesQuest> questList= questService.getActivesQuestByActivesId(activesId);
		model.put("activesId", activesId);
		model.put("questList", questList);
		return "/quest/questAdd";
	}
	//老师添加教学查房问题提交
	@RequestMapping("/questController/qusetsum.htm")
	public String qusetsum(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer activesId) throws IOException{
		HttpSession session = request.getSession();
		Integer menuOrder= (Integer) session.getAttribute("menuOrder");
		String li =(String) session.getAttribute("li");
		String div =(String) session.getAttribute("div");
		String type =(String) session.getAttribute("type");
		String questTxt= request.getParameter("questTxt");
		ActivesQuest quest =new ActivesQuest();
		try {
			quest.setActivesId(activesId);
			quest.setQuestTxt(questTxt);
			questService.addActivesQuest(quest);
		} catch (Exception e) {
			log.info("添加问题出错"+e);
		}
		return "redirect:/activesCont/index.htm?li="+li+"&div="+div+"&menuOrder="+menuOrder+"&type="+type;
	}
	
	
	/**
	 * 教师问题
	 * @throws IOException 
	 */
	@RequestMapping("/questController/qusetdel.htm")
	public String questView(HttpServletResponse response,HttpServletRequest request,ModelMap model,Integer activesId) throws IOException{
		HttpSession session = request.getSession();
		Integer menuOrder= (Integer) session.getAttribute("menuOrder");
		String li =(String) session.getAttribute("li");
		String div =(String) session.getAttribute("div");
		String type =(String) session.getAttribute("type");
		try {
			questService.delActivesQuestByActivesId(activesId);
		} catch (Exception e) {
			log.info("添加问题出错"+e);
		}
		return "redirect:/activesCont/index.htm?li="+li+"&div="+div+"&menuOrder="+menuOrder+"&type="+type;
	}
	/**
	 * 学生回答问题
	 */
	@RequestMapping("/questCont/qusetans.htm")
	public String quesAns(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer activesId){
		String li = request.getParameter("li");
		String div = request.getParameter("div");
		model.put("li", li);
		model.put("div", div);
		String status = request.getParameter("status");
		model.put("status", status);
		List<ActivesQuest> ActivesQuestList = questService.getActivesQuestByActivesId(activesId);
		ActivesQuest ActivesQuest=new ActivesQuest();
		if (ActivesQuestList.size()>0){
			ActivesQuest= ActivesQuestList.get(0);
		}
		model.put("quest", ActivesQuest);
		model.put("activesId", activesId);
		
		return "/quest/questans";
		
	}
	/**
	 * 学生提交回答问题的答案
	 * @throws IOException 
	 */
	
	@RequestMapping("/questController/ansSum.htm")
	public String quesAnsSum(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer activesId) throws IOException{
		HttpSession session = request.getSession();
		Integer menuOrder= (Integer) session.getAttribute("menuOrder");
		Integer status = (Integer) session.getAttribute("status");
		String li =(String) session.getAttribute("li");
		String div =(String) session.getAttribute("div");
		String type =(String) session.getAttribute("type");
		
		User user =getuserSession(request);
		String questTxt =request.getParameter("questTxt");
		ActivesQuestUser aqu = new ActivesQuestUser();
		aqu.setActivesId(activesId);
		aqu.setIdentityId(2);
		aqu.setLoginName(user.getLoginName());
		aqu.setName(user.getRealName());
		aqu.setQuestText(questTxt);
		activesQuestUserService.add(aqu);
		
		activesService.updateIsInById(activesId);
		ActivesUser ac = new ActivesUser();
		
		ac.setActivesId(activesId);
		ac.setName(user.getRealName());
		ac.setLoginName(user.getLoginName());
		ac.setIdentityId(2);
		ac.setIsIn(1);
		ac.setStatus(status);
		activesUserService.add(ac);
		//查询问题，并把它显示出来
		return "redirect:/activesCont/index.htm?li="+li+"&div="+div+"&menuOrder="+menuOrder+"&type="+type;
	}

	@RequestMapping("/questCont/questSee.htm")
	public String questSee(HttpServletResponse response,HttpServletRequest request ,ModelMap model,Integer activesId ,String loginName){
		if(null==loginName || "".equals(loginName)){
			User user = getuserSession(request);
			loginName = user.getLoginName();
		}
		ActivesQuestUser activesQuestUser = activesQuestUserService.getActivesQuestUser(loginName, activesId);
		
		model.put("activesQuestUser", activesQuestUser);
		return "/quest/ansshow";
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
	@Autowired
	public ActivesService activesService;

	@Autowired
	public ActivesUserService activesUserService;
	@Autowired
	public ActivesQuestUserService activesQuestUserService;
}
