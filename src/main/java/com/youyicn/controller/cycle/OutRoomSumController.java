package com.youyicn.controller.cycle;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.common.DateUtil;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.Room;
import com.youyicn.entity.cycle.SumScore;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.service.cycle.SumScoreService;
import com.youyicn.util.ControllerHelper;
import com.youyicn.util.PageBean;
/**
 * 学生出科成绩
 * @author admin
 *
 */
@Controller
public class OutRoomSumController {
	Logger log = LoggerFactory.getLogger(OutRoomSumController.class);
	@Autowired
	public RoomService roomService;
	
	
	/**
	 * 首页  主要是页面和选择三种的哪一个
	 */
	@RequestMapping("/outRoomCont/outroomindex.htm")
	public String outRoomIndex(HttpServletRequest request,HttpServletResponse response,ModelMap model,
			String li,String div,String type,Integer menuOrder){
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		model.put("menuOrder", menuOrder);

		HttpSession session = request.getSession();
		session.setAttribute("menuOrder", menuOrder);
		session.setAttribute("li", li);
		session.setAttribute("div", div);
		session.setAttribute("type", type);
		
		User user = getUserSession(request);
		String pageNum = request.getParameter("pageIndex");
		int pageIndex = 1;
		if(null!=pageNum && !"".equals(pageNum) ){
			pageIndex = Integer.valueOf(pageNum);
		}
		String searchStart = request.getParameter("searchStart");
		String searchEnd = request.getParameter("searchEnd");
		SumScore sumScoreCon = new SumScore();
		List<Room> roomValues = roomService.queryAllRoom();
		if (null != searchStart && !"".equals(searchStart)) {
			sumScoreCon.setSearchStart(DateUtil.str3Timestamp(searchStart, null));
			model.put("searchStart", searchStart);
		}
		if (null != searchEnd && !"".equals(searchEnd)) {
			sumScoreCon.setSearchEnd(DateUtil.str3Timestamp(searchEnd, null));
			model.put("searchEnd", searchEnd);
		}
		String roomName = request.getParameter("roomName");
		if(null!=roomName && !"".equals(roomName)){
			model.put("roomName", roomName);
			sumScoreCon.setRoomName(roomName);
			
		}
		PageHelper.startPage(pageIndex, 15);
		if("h".endsWith(type)){
			model.put("roomValues", roomValues);
		}
		
		if("b".endsWith(type)){
			roomName = user.getRoomName();
			sumScoreCon.setRoomName(roomName);
		}
		if("r".endsWith(type)){
			roomName = user.getRoomName();
			sumScoreCon.setRoomName(roomName);
		}
		if("t".endsWith(type)){
			String teacherNum = user.getLoginName();
			sumScoreCon.setTeacherNum(teacherNum);
		}
		if("s".endsWith(type)){
			String loginName = user.getLoginName();
			sumScoreCon.setUserNum(loginName);
		}
		model.put("type", type);
		List<SumScore> sumScoreList = sumScoreService.getSumScore(sumScoreCon);
		PageInfo<SumScore> page = new PageInfo<SumScore>(sumScoreList);
		model.put("page", page);
		return "/outroom/index";
		
	}
	//详情
	@RequestMapping("/outRoomCont/outroomXq.htm")
	public String outRoomXq(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String id=request.getParameter("id");
		SumScore sumScore = sumScoreService.getSumScoreById(Integer.parseInt(id));
		model.put("sumScore", sumScore);
		return "/outroom/toutRoomXq";
	}
	
	/**
	 * 评分总汇总列表
	 */
	@RequestMapping("/sumScoreCont/sumScoreList.htm")
	public String sumScoreList(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		User teacherSession = getUserSession(request);
		String roomName = teacherSession.getRoomName();
		String pageNum = request.getParameter("pageIndex");
		int pageIndex = 1;
		if(null!=pageNum && !"".equals(pageNum) ){
			pageIndex = Integer.valueOf(pageNum);
		}
		PageHelper.startPage(pageIndex, 15);
		SumScore sumScoreCon = new SumScore();
		sumScoreCon.setRoomName(roomName);
		List<SumScore> sumScoreList = sumScoreService.getSumScore(sumScoreCon);
		PageBean<SumScore> page = new PageBean<SumScore>(sumScoreList);
		model.put("page", page);
		
		return "/outroom/rsumscorelist";
	}
	/**
	 * 添加教学汇总评分
	 */
	@RequestMapping("/rsumScoreCont/sumScoreadd.htm")
	public String sumScoreAdd(HttpServletRequest request,HttpServletResponse response,ModelMap model,String div,String li){
		model.put("li", li);
		model.put("div", div);
		
		return "/outroom/sumscoreadd";
	}	
	/**
	 * 提交教学汇总评分
	 */
	@RequestMapping("/rsumScoreCont/sumScoresum.htm")
	public String sumScoreSum(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		User teacherSession = getUserSession(request);
		String createName = teacherSession.getRealName();
		String createNum = teacherSession.getUserNum();
		String roomName = teacherSession.getRoomName();
		
		/**
		 * 获取数据
		 */
		SumScore sumScore= new SumScore();
		String loginName = request.getParameter("loginName");
		String userName="";
		if(""!=loginName && null !=loginName){
			userName = userService.getByNum(loginName).getRealName();
		}
		String teacherNum = request.getParameter("teacherNum");
		String teacherName="";
		if(""!=teacherNum && null !=teacherNum){
			teacherName = userService.getByNum(teacherNum).getRealName();
		}
		String absenceMount = request.getParameter("absenceMount");
		String onDetyMount  = request.getParameter("onDetyMount");
		String sickMount = request.getParameter("sickMount");
		String mngMount = request.getParameter("mngMount");
		String writeQuality = request.getParameter("writeQuality");
		String operateMount = request.getParameter("operateMount");
		String thinking = request.getParameter("thinking");
		String virtues = request.getParameter("virtues");
		String teachAbility = request.getParameter("teachAbility");
		String learnAmount = request.getParameter("learnAmount");
		String science = request.getParameter("science");
		String mistake = request.getParameter("mistake");
		String nurseDes = request.getParameter("nurseDes");
		String sickDes = request.getParameter("sickDes");
		String theoryscore = request.getParameter("theoryscore");
		
		try {
			sumScore.setTheoryscore(theoryscore);
			sumScore.setCreateName(createName);
			sumScore.setCreateNum(createNum);
			sumScore.setUserName(userName);
			sumScore.setUserNum(loginName);
			sumScore.setTeacherName(teacherName);
			sumScore.setTeacherNum(teacherNum);
			sumScore.setRoomName(roomName);
			sumScore.setAbsenceMount(absenceMount);
			sumScore.setOnDetyMount(onDetyMount);
			sumScore.setSickMount(sickMount);
			sumScore.setMngMount(mngMount);
			sumScore.setWriteQuality(writeQuality);
			sumScore.setOperateMount(operateMount);
			sumScore.setThinking(thinking);
			sumScore.setVirtues(virtues);
			sumScore.setTeachAbility(teachAbility);
			sumScore.setLearnAmount(learnAmount);
			sumScore.setScience(science);
			sumScore.setMistake(mistake);
			sumScore.setNurseDes(nurseDes);
			sumScore.setSickDes(sickDes);
			sumScore.setCreateTime(DateUtil.date2Timestamp(new Date()));
			sumScore.setIsOver(1);
			sumScoreService.addSumScore(sumScore);
			ControllerHelper.respOut(response, true);
		} catch (Exception e) {
			log.info("插入汇总评分失败:"+e);
			ControllerHelper.respOut(response, false);
		}
		return null;
	}	
	
	
	@RequestMapping("/rsumScoreCont/updatesum.htm")
	public String updateSum(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		User teacherSession = getUserSession(request);
		HttpSession session = request.getSession();
		Integer menuOrder = (Integer) session.getAttribute("menuOrder");
		String li = (String) session.getAttribute("li");
		String div = (String) session.getAttribute("div");
		String type=request.getParameter("type");
		
		String createName = teacherSession.getRealName();
		String createNum = teacherSession.getUserNum();
		String id = request.getParameter("id");
		
		/**
		 * 获取数据
		 */
		SumScore sumScore= sumScoreService.getSumScoreById(Integer.parseInt(id));
		String absenceMount = request.getParameter("absenceMount");
		String onDetyMount  = request.getParameter("onDetyMount");
		String sickMount = request.getParameter("sickMount");
		String mngMount = request.getParameter("mngMount");
		String writeQuality = request.getParameter("writeQuality");
		String operateMount = request.getParameter("operateMount");
		String thinking = request.getParameter("thinking");
		String virtues = request.getParameter("virtues");
		String teachAbility = request.getParameter("teachAbility");
		String learnAmount = request.getParameter("learnAmount");
		String science = request.getParameter("science");
		String mistake = request.getParameter("mistake");
		String nurseDes = request.getParameter("nurseDes");
		String sickDes = request.getParameter("sickDes");
		String theoryscore = request.getParameter("theoryscore");
		
		try {
			sumScore.setTheoryscore(theoryscore);
			sumScore.setCreateName(createName);
			sumScore.setCreateNum(createNum);
			sumScore.setAbsenceMount(absenceMount);
			sumScore.setOnDetyMount(onDetyMount);
			sumScore.setSickMount(sickMount);
			sumScore.setMngMount(mngMount);
			sumScore.setWriteQuality(writeQuality);
			sumScore.setOperateMount(operateMount);
			sumScore.setThinking(thinking);
			sumScore.setVirtues(virtues);
			sumScore.setTeachAbility(teachAbility);
			sumScore.setLearnAmount(learnAmount);
			sumScore.setScience(science);
			sumScore.setMistake(mistake);
			sumScore.setNurseDes(nurseDes);
			sumScore.setSickDes(sickDes);
			sumScore.setCreateTime(DateUtil.date2Timestamp(new Date()));
			
			sumScoreService.updateSumScore(sumScore);
		} catch (Exception e) {
			log.info("插入汇总评分失败:"+e);
		}
		return "redirect:/outRoomCont/outroomindex.htm?li="+li+"&div="+ div+"&menuOrder="+menuOrder+"&type="+type;
	}	
	/**
	 * 科室删除总评分
	 */
	@RequestMapping("/rsumScoreCont/sumScoredel.htm") 
	public String sumScoredel(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String id1 =request.getParameter("id");
		if(null!=id1 && !"".equals(id1)){
			Integer id = Integer.parseInt(id1);
			try {
				sumScoreService.delSumScore(id);
				ControllerHelper.respOut(response, true);
				
			} catch (Exception e) {
				log.info("删除汇总评分失败:"+e);
				ControllerHelper.respOut(response, false);
				
			}
		}
		return null;
	}	
	
	@RequestMapping("/outRoomSum/update.htm") 
	public String  update(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String id = request.getParameter("id");
		String type=request.getParameter("type");
		model.put("type", type);
		if(""!=id && null!=id){
			SumScore SumScore =sumScoreService.getSumScoreById(Integer.parseInt(id));
			model.put("sumScore", SumScore);
			return "/outroom/update";
		}else{
			return null;
		}
		
		
	}
	
	
	
	/**
	 * 通过session获取科室名字
	 */
	// 通用的配置都是放在最下面
	private User getUserSession(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("loginName");
		User user = new User();
		if (obj instanceof String) {
			String loginName = (String) obj;
			user = userService.getByNum(loginName);
		}
		return user;
	}
	@Autowired
	public UserService userService;
	@Autowired
	public SumScoreService sumScoreService;

}
