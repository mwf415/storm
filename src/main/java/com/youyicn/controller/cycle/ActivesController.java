package com.youyicn.controller.cycle;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.youyicn.common.DateUtil;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.Actives;
import com.youyicn.entity.cycle.ActivesUser;
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.entity.cycle.ArrturnRule;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.CountUtil;
import com.youyicn.entity.cycle.Dops;
import com.youyicn.entity.cycle.JudgeT2s;
import com.youyicn.entity.cycle.Room;
import com.youyicn.entity.cycle.SelfSumUp;
import com.youyicn.entity.cycle.Soap;
import com.youyicn.entity.cycle.SumScore;
import com.youyicn.entity.cycle.ZiLiao;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ActivesService;
import com.youyicn.service.cycle.ActivesUserService;
import com.youyicn.service.cycle.ArrTurnService;
import com.youyicn.service.cycle.ArrturnRuleService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.DopsService;
import com.youyicn.service.cycle.JudgeT2SServer;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.service.cycle.SelfSumUpService;
import com.youyicn.service.cycle.SoapService;
import com.youyicn.service.cycle.SumScoreService;
import com.youyicn.service.cycle.ZLService;
import com.youyicn.util.ControllerHelper;


@Controller
public class ActivesController {
	/*** 这里6 教学查房，7 教学讲座，8 病例讨论，9 操作训练，5 出科考试   4入科  3是其他*/
	public static final Integer isOver0 = 0;// 这里用来实现学生添加自己是否参加 0
	public static final Integer isOver1 = 1;// 这里用来实现学生添加自己是否参加 0
	public Logger log = LoggerFactory.getLogger(ActivesController.class);
	@Autowired
	public UserService userService;
	@Autowired
	public ActivesService activesService;
	@Autowired
	public ActivesUserService activesUserService;
	@Autowired
	public ArrturnRuleService arrturnRuleService;
	@Autowired
	public ArrTurnService arrTurnService;
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;

	/**
	 * 首页 ，主要是用来渲染页面，和进行放置查询条件的
	 */
	@RequestMapping("/activesCont/index.htm") 
	public String activesIndex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, String teachernum,
			String li, String div, Integer menuOrder, String type) {
		/* 这里是根据登录用户身份获得 */

		Integer status = 6; // 既可以获得教学查房，也可以获得病历讨论
		if (null != li && !"".equals(li)) {
			status = Integer.parseInt(li.substring(li.length() - 1));
		}
		User userSession = getUserSession(request);
		String loginName = userSession.getLoginName();
		String pageNum = request.getParameter("pageIndex");
		model.put("menuOrder", menuOrder);
		int pageIndex = 1;
		if (null != pageNum && !"".equals(pageNum)) {
			pageIndex = Integer.valueOf(pageNum);
		}
		model.put("type", type);
		model.put("li", li);
		model.put("div", div);
		model.put("pageIndex", pageIndex);
		model.put("status", status);
		HttpSession session = request.getSession();
		session.setAttribute("status", status);
		session.setAttribute("menuOrder", menuOrder);
		session.setAttribute("li", li);
		session.setAttribute("div", div);
		session.setAttribute("type", type);
		String path = (String) session.getAttribute("path");
		List<Actives> activesList = new ArrayList<Actives>();
		String searchStart = request.getParameter("searchStart");
		String searchEnd = request.getParameter("searchEnd");
		Actives actives = new Actives();
		actives.setStatus(status);
		if (null != searchStart && !"".equals(searchStart)) {
			actives.setSearchStart(DateUtil.str3Timestamp(searchStart, null));
			model.put("searchStart", searchStart);
		}
		if (null != searchEnd && !"".equals(searchEnd)) {
			actives.setSearchEnd(DateUtil.str3Timestamp(searchEnd, null));
			model.put("searchEnd", searchEnd);
		}

		String roomName = "";
		String baseName = "";
		List<ArrturnRule> baseRoomList = new ArrayList<ArrturnRule>();
		PageHelper.startPage(pageIndex, 15);
		if ("t" == type || "t".equals(type)) {
			List<ActivesUser> auList = activesUserService.getByUserLoginName(
					loginName, status);
			PageInfo<ActivesUser> page = new PageInfo<ActivesUser>(auList);
			model.put("page", page);
			if ("p".equals(path) || "p" == path) {
				return "/phone/actives/self";
			} else {
				return "/actives/self";
			}
		}
		if ("s" == type || "s".equals(type)) {
			String isIn= request.getParameter("isIn");
			if("1".equals(isIn)|| "1"==isIn){// 查看参加过得
				
				List<ActivesUser> auList = activesUserService.getByUserLoginName(loginName, status);
				PageInfo<ActivesUser> page = new PageInfo<ActivesUser>(auList);
				model.put("isIn", 0);
				model.put("page", page);
				if ("p".equals(path) || "p" == path) {
					return "/phone/actives/self";
				} else {
					return "/actives/self";
				}
			} else {
				/*** 这一部分复杂，查看所有的未参加的列表 如果已经参加，就不列出了，如果没有参加 就列出来 */
				// 设置学生只显示今天的
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				Calendar caStrat = Calendar.getInstance();
				Calendar caEnd = Calendar.getInstance();
				caStrat.setTime(date);
				caStrat.set(Calendar.HOUR_OF_DAY, 01);
				caEnd.setTime(date);
				caEnd.set(Calendar.HOUR_OF_DAY, 23);
				String startTime = sdf.format(caStrat.getTime());
				String endTime = sdf.format(caEnd.getTime());
				Timestamp ts = DateUtil.str2Timestamp(startTime, null);
				Timestamp es = DateUtil.str2Timestamp(endTime, null);
				actives.setSearchStart(ts);
				actives.setSearchEnd(es);
				actives.setStatus(status);
				activesList = activesService.getByCon(actives);

				List<Actives> activesListResult = new ArrayList<Actives>();
				activesListResult.addAll(activesList);

				if (activesList.size() > 0) {
					for (Actives actTemp : activesList) {
						int activeId = actTemp.getId();
						ActivesUser au = activesUserService.getByUserIn(
								loginName, activeId);
						if (null != au) {
							activesListResult.remove(actTemp);
						}
					}
				}
				activesList.clear();
				activesList.addAll(activesListResult);
				model.put("isIn", 1);// 这个主要为了参加和不参加的页面显示，如果是选择这个，那么列表页要显示确认参加按钮
				PageInfo<Actives> page = new PageInfo<Actives>(activesList);
				model.put("page", page);
				if ("p".equals(path) || "p" == path) {
					return "/phone/actives/index";
				} else {
					return "/actives/index";

				}
			}
		}

		if ("h" == type || "h".equals(type)) {
			baseName = request.getParameter("baseName");
			roomName = request.getParameter("roomName");
			if (null != roomName && "" != roomName) {
				actives.setRoomName(roomName);
			}
			if (null != baseName && "" != baseName) {
				actives.setBaseName(baseName);
			}

		}
		if ("b" == type || "b".equals(type)) {
			baseName = userSession.getBaseName();
			baseRoomList = arrturnRuleService
					.getArrTurnRuleByBaseName(baseName);
			actives.setBaseName(baseName);
		}
		if ("r" == type || "r".equals(type)) { // 只有科室在这个时候才会要求入科资料
			roomName = userSession.getRoomName();
			if (4 == status) { // 入科教育会显示资料
				ZiLiao zl1 = new ZiLiao();
				zl1.setRoomName(roomName);
				ZiLiao zl = zlService.getZLByRoomName(zl1);
				if (zl != null) {
					model.put("zl", zl);
				}
			}
			actives.setRoomName(roomName);
		}

		activesList = activesService.getByCon(actives);
		PageInfo<Actives> page = new PageInfo<Actives>(activesList);
		model.put("page", page);
		List<Base> baseValues = baseService.queryAllBase();
		List<Room> roomValues = roomService.queryAllRoom();

		model.put("roomName", roomName);
		model.put("baseName", baseName);
		model.put("teachernum", teachernum);
		model.put("baseValues", baseValues);
		model.put("roomValues", roomValues);
		model.put("baseRoomList", baseRoomList);

		if ("p".equals(path) || "p" == path) {
			return "/phone/actives/index";
		} else {
			return "/actives/index";
		}
	}

	/**
	 * 添加
	 */
	@RequestMapping("/activesController/add.htm")
	public String lecAdd(HttpServletRequest request,HttpServletResponse response, ModelMap model, String div) {
		User userSession = getUserSession(request);
		HttpSession session = request.getSession();
		Integer status = (Integer) session.getAttribute("status");
		String type = request.getParameter("type");
		model.put("status", status);
		model.put("type", type);
		model.put("div", div);
		String roomName = userSession.getRoomName();
		List<User> teacherList = userService.getTByRoom(roomName);
		if (4 == status && "r".equals(type)) {
			/** 目的是为了获取这一个月将要来的学员，如果没有，可以提示不创建； */
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			ArrTurn arr = new ArrTurn();
			Calendar rightNow = Calendar.getInstance();
			rightNow.set(Calendar.HOUR_OF_DAY, 13);
			String searchtime = sdf.format(rightNow.getTime());
			arr.setSearchStart(DateUtil.str2TimestampM(searchtime));
			arr.setRoomName(roomName);
			List<ArrTurn> arrTurnList = arrTurnService.getThisMonthUser(arr);
			model.put("arrTurnList", arrTurnList);
		} else {
			List<Room> roomValues = roomService.queryAllRoom();
			model.put("roomValues", roomValues);
			String baseName = userSession.getBaseName();
			List<ArrturnRule> baseRoomList = arrturnRuleService
					.getArrTurnRuleByBaseName(baseName);
			model.put("baseRoomList", baseRoomList);
		}
		model.put("roomName", roomName);
		model.put("teacherList", teacherList);
		return "/actives/add";
	}

	/**
	 * 提交 教学活动
	 * 
	 */
	@RequestMapping("/activesController/sum.htm")
	public String activesAddSum(HttpServletResponse response,
			HttpServletRequest request, ModelMap model, Integer takeTime,
			String type, String title, String roomName,
			String teacherNum,// teacherNum 是编号和名字都有
			String sickPerson, String sickNum, String address,
			String startTime, String div, String[] userP, String[] teacherP) {
		User userSession = getUserSession(request);
		HttpSession session = request.getSession();
		String div_last = div.substring(div.length() - 1);
		Integer menuOrder = (Integer) session.getAttribute("menuOrder");
		Integer status = (Integer) session.getAttribute("status");
		
		Actives actives = new Actives();
		ActivesUser activesUser = new ActivesUser();
		activesUser.setStatus(status);
		String[] uteacher = teacherNum.split(":");//主要的老师
		String kgNum = uteacher[0];
		String kgName = uteacher[1];

		String baseName = "";
		if ("b".equals(type)) {
			roomName = request.getParameter("roomName");
			baseName = userSession.getBaseName();
			actives.setOrg(userSession.getBaseName());
		}
		if ("h".equals(type)) {
			roomName = request.getParameter("roomName");
			actives.setOrg("院方");
		}
		if ("r".equals(type)) {
			roomName = userSession.getRoomName();
			actives.setOrg(userSession.getRoomName());
		}
		try {
			actives.setSickPerson(sickPerson);
			actives.setSickNum(sickNum);
			actives.setBaseName(baseName);
			actives.setTitle(title);
			actives.setStartTime(DateUtil.str2TimestampM(startTime));
			actives.setCreateTime(DateUtil.date2Timestamp(new Date()));
			actives.setRoomName(roomName);
			actives.setTakeTime(takeTime);
			actives.setStatus(status);
			actives.setIsOver(isOver0);
			actives.setAddress(address);
			actives.setCreater(userSession.getRealName());
			activesService.add(actives);
			int activesId = actives.getId();
			activesUser.setActivesId(activesId);

			if (5 == status || 4 == status) {
				Dops dops = new Dops();
				dops.setKgName(kgName);
				dops.setKgNum(kgNum);
				dops.setActivesId(activesId);
				dops.setRoomName(roomName);
				dops.setBaseName(baseName);

				Soap soap = new Soap();
				soap.setKgName(kgName);
				soap.setKgNum(kgNum);
				soap.setActivesId(activesId);
				soap.setRoomName(roomName);
				soap.setBaseName(baseName);

				SumScore sumScore = new SumScore();
				sumScore.setTeacherName(kgName);
				sumScore.setTeacherNum(kgNum);
				sumScore.setActivesId(activesId);
				sumScore.setRoomName(roomName);

				JudgeT2s j2s = new JudgeT2s();
				j2s.setActivesId(activesId);
				j2s.setTeacherName(kgName);
				j2s.setTeacherNum(kgNum);
				j2s.setBaseName(baseName);
				j2s.setRoomName(roomName);

				SelfSumUp ssu = new SelfSumUp();
				ssu.setActivesId(activesId);
				//处理学生的数据
				for (String userNameAndNum : userP) {
					String[] user = userNameAndNum.split(":");
					String loginName = user[0];
					String userName = user[1];
					if (5 == status) {
						dops.setKsName(userName);
						dops.setKsNum(loginName);
						dops.setFlag("0");
						dopsService.insert(dops);

						soap.setKsName(userName);
						soap.setKsNum(loginName);
						soap.setFlag("0");
						soap.setRoomName(roomName);
						soap.setBaseName(baseName);
						soapService.insert(soap);

						sumScore.setUserName(userName);
						sumScore.setUserNum(loginName);
						sumScoreService.addSumScore(sumScore);

						j2s.setLoginName(loginName);
						j2s.setUserName(userName);
						judgeT2SServer.insertJudgeT2s(j2s);

						ssu.setLoginName(loginName);
						selfSumUpService.insert(ssu);

					}
					activesUser.setLoginName(loginName);
					activesUser.setName(userName);
					activesUser.setIdentityId(2);
					activesUserService.add(activesUser);
				}
				//处理其他老师的
				if (null != teacherP && teacherP.length > 0) { // 添加关联表中的老师数据
					for (String user : teacherP) {
						String[] ut = user.split(":");
						String loginName = ut[0];
						String userName = ut[1];
						activesUser.setLoginName(loginName);
						activesUser.setName(userName);
						activesUser.setIdentityId(1);
						activesUserService.add(activesUser);
					}
				}

			}
			//无论什么活动，都要有个主考官
			activesUser.setLoginName(kgNum);// 添加关联表中的主考官数据
			activesUser.setName(kgName);
			activesUser.setActivesId(activesId);
			activesUser.setIdentityId(1);
			activesUserService.add(activesUser);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("添加查房安排问题" + e);
		}
		return "redirect:/activesCont/index.htm?li=li" + div_last + status
				+ "&div=div_" + div_last + "&menuOrder=" + menuOrder + "&type="
				+ type;
	}

	/**
	 * 删除
	 */
	@RequestMapping("/activesController/del.htm")
	public String activesDel(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Integer id,
			String type, String div) {
		HttpSession session = request.getSession();
		Integer status = (Integer) session.getAttribute("status");
		if (id != null) {
			try {
				activesService.delById(id);
				activesUserService.delByactivesId(id);
				sumScoreService.delByActivesId(id);
				judgeT2SServer.delByActivesId(id);
				selfSumUpService.delByActivesId(id);
				dopsService.delByActivesId(id);
				soapService.delByActivesId(id);
			} catch (Exception e) {
				log.info("删除讲座失败：" + e);
			}
		}
		return "redirect:/activesCont/index.htm?li=li6" + status + "&div="
				+ div + "&menuOrder=0&type=" + type;

	}

	/**
	 * 涉及到老师对这个讲座的评价了。
	 */
	@RequestMapping("/activesCont/addlecDes.htm")
	public String addlecdes(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		HttpSession session = request.getSession();
		String status = (String) session.getAttribute("status");
		String activesId = request.getParameter("activesId");
		model.put("activesId", activesId);
		if ("8".equals(status)) {
			return "/actives/adddes";
		}
		if ("9".equals(status)) {
			return "/practice/adddes";
		}
		return null;
	}

	@RequestMapping("/activesCont/sumdes.htm")
	public String lecDesSum(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Integer activesId) {
		String des = request.getParameter("lecDes");
		HttpSession session = request.getSession();
		String status = (String) session.getAttribute("status");
		Actives actives = new Actives();
		if (activesId != null) {
			actives = activesService.getById(activesId);
			actives.setDes(des);
			activesService.updateIsInById(activesId);
			log.info("添加评论成功");
		}
		if ("8".equals(status)) {
			return "redirect:/activesCont/index.htm?li=li48&div=div_4&menuOrder=0&type=t";

		}
		if ("9".equals(status)) {
			return "redirect:/activesCont/index.htm?li=li49&div=div_4&menuOrder=0&type=t&proj=0";
		}
		return null;
	}

	// 这个详情，每个都需要有，方便查询和统计哪个学生参加了。
	@RequestMapping("/activesCont/show.htm")
	public String inLearnshow(HttpServletRequest request,
			HttpServletResponse response, Integer menuOrder, ModelMap model,
			String li, String div, Integer activesId) {
		model.put("li", li);
		model.put("div", div);
		List<ActivesUser> auList = activesUserService.getUserByactivesId(
				activesId, 2);
		// 这个主要是为了区分教学查房，病例讨论等功能。如果是教学查房和病历讨论，就显示学生的问题
		HttpSession session = request.getSession();
		Integer status = (Integer) session.getAttribute("status");
		model.put("status", status);
		PageInfo<ActivesUser> page = new PageInfo<ActivesUser>(auList);
		model.put("page", page);
		return "/actives/detail";
	}

	// 更新isIn
	@RequestMapping("/inLearn/updateIsIn.htm")
	public String updateIsInById(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Integer activesId)
			throws IOException {
		User user = getUserSession(request);
		try {
			if (null != activesId && !"".equals(activesId)) {
				ActivesUser ac = activesUserService.getByUserIn(
						user.getLoginName(), activesId);
				ac.setId(1);
				activesUserService.update(ac);
				ControllerHelper.respOut(response, true);
			}
		} catch (Exception e) {
			ControllerHelper.respOut(response, false);
		}
		return null;
	}

	/**
	 * 查找学员
	 */
	@RequestMapping(value="/outexam/searchS.htm")
	@ResponseBody
	public void getS(HttpServletResponse response,HttpServletRequest request){
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
		String time = request.getParameter("time");
		User userSession = getUserSession(request);
		String roomName =userSession.getRoomName();
		List<ArrTurn> list = arrTurnService.getChangeUserByTime(time, roomName,null);
		String rNum = JSON.toJSONString(list);
		try {
			response.getWriter().write(rNum.toString());
			response.getWriter().close();  
			response.getWriter().flush();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}  
	}

	@RequestMapping("/inLearn/ziliao_detail.htm")
	public String ziliaoDetail(HttpServletRequest request, String type,
			HttpServletResponse response, ModelMap model, String roomName)
			throws Exception {

		if (type == "r") {
			User user = getUserSession(request);
			roomName = user.getRoomName();
		}

		ZiLiao zl1 = new ZiLiao();
		zl1.setRoomName(roomName);
		ZiLiao zl = zlService.getZLByRoomName(zl1);

		model.put("zl", zl);

		return "/actives/ziliaoDetail";
	}

	@RequestMapping("/inLearn/ziliao_add.htm")
	public String ziliaoAdd(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		User teacher = getUserSession(request);
		String roomName = teacher.getRoomName();

		ZiLiao zl1 = new ZiLiao();
		zl1.setRoomName(roomName);
		ZiLiao zl = zlService.getZLByRoomName(zl1);

		if (zl != null) {
			model.put("content", zl.getContent());
		}

		return "/actives/ziliaoAdd";
	}

	@RequestMapping("/inLearn/ziliaoUpdate.htm")
	public String ziliaoUpdate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		User teacher = getUserSession(request);
		String roomName = teacher.getRoomName();
		String contentTxt = request.getParameter("contentTxt");

		try {
			ZiLiao zl1 = new ZiLiao();
			zl1.setRoomName(roomName);
			ZiLiao zl = zlService.getZLByRoomName(zl1);
			if (zl == null) {
				zl = new ZiLiao();
				zl.setRoomName(roomName);
				zl.setContent(contentTxt);
				zlService.addZL(zl);
			} else {
				zl.setContent(contentTxt);
				zlService.updateZL(zl);
			}

			ControllerHelper.respOut(response, true);

		} catch (Exception e) {
			e.printStackTrace();
			ControllerHelper.respOut(response, false);
		}

		return null;
	}

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
	public ArrturnRuleService arrTrunRuleService;
	@Autowired
	public DopsService dopsService;
	@Autowired
	public SoapService soapService;
	@Autowired
	public ZLService zlService;
	@Autowired
	public SumScoreService sumScoreService;

	@Autowired
	public SelfSumUpService selfSumUpService;

	@Autowired
	public JudgeT2SServer judgeT2SServer;

}
