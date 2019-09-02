package com.youyicn.controller.cycle.phone;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.youyicn.controller.cycle.ActivesController;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.Actives;
import com.youyicn.entity.cycle.ActivesUser;
import com.youyicn.entity.cycle.ArrturnRule;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.Room;
import com.youyicn.entity.cycle.ZiLiao;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ActivesService;
import com.youyicn.service.cycle.ActivesUserService;
import com.youyicn.service.cycle.ArrTurnService;
import com.youyicn.service.cycle.ArrturnRuleService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.service.cycle.ZLService;

@Controller
public class ActivesPhoneController {
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
		@Autowired
		public ZLService zlService;

		/**
		 * 首页 ，主要是用来渲染页面，和进行放置查询条件的
		 */
		@RequestMapping("/activesCont/phone/index.htm") 
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
//					SimpleDateFormat sdf = new SimpleDateFormat(
//							"yyyy-MM-dd HH:mm:ss");
//					Date date = new Date();
//					Calendar caStrat = Calendar.getInstance();
//					Calendar caEnd = Calendar.getInstance();
//					caStrat.setTime(date);
//					caStrat.set(Calendar.HOUR_OF_DAY, 01);
//					caEnd.setTime(date);
//					caEnd.set(Calendar.HOUR_OF_DAY, 23);
//					String startTime = sdf.format(caStrat.getTime());
//					String endTime = sdf.format(caEnd.getTime());
//					Timestamp ts = DateUtil.str2Timestamp(startTime, null);
//					Timestamp es = DateUtil.str2Timestamp(endTime, null);
//					actives.setSearchStart(ts);
//					actives.setSearchEnd(es);
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
}
