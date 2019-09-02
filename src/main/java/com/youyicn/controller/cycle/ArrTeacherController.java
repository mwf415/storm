package com.youyicn.controller.cycle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.youyicn.common.DateUtil;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.Actives;
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.Room;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ArrTurnService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.util.ControllerHelper;
import com.youyicn.util.PageBean;
import com.youyicn.util.StringUtils;
@Controller
public class ArrTeacherController {
	public static final String CHESTATUS = "1";// 这里显示的都是审核通过的轮转
	public Logger log = LoggerFactory.getLogger(ArrTeacherController.class);
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;
	@RequestMapping("/arrTeacherCont/arrTeacherindex.htm")
	public String arrTeacherIndex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String type = request.getParameter("type");
		String li = request.getParameter("li");
		String div = request.getParameter("div");
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		String date = request.getParameter("searchStart");
		ArrTurn arrTurnCon = new ArrTurn();
	
		
		if ("r" == type || "r".equals(type)) {// 科室指定老师
			String roomName = getRoomSession(request);
			User teacherCon = new User();
			teacherCon.setRoomName(roomName);
			List<User> teacherList = userService.getByCondition(teacherCon);
			model.put("teacherList", teacherList);
			String teacherNum = request.getParameter("teachernum");

			String pageNum = request.getParameter("pageIndex");
			int pageIndex = 1;
			if (null != pageNum && !"".equals(pageNum)) {
				pageIndex = Integer.valueOf(pageNum);
			}
			PageHelper.startPage(pageIndex, 40);
			List<ArrTurn> arrturnList = new ArrayList<ArrTurn>();
			arrTurnCon.setRoomName(roomName);
			arrTurnCon.setCheckStatus(CHESTATUS);
			if (StringUtils.NotNull(date)) {
				Date date1 = format.parse(date);
				Calendar c = new GregorianCalendar();// 获取当前月第一天：
				c.setTime(date1);
				c.add(Calendar.MONTH, 0);
				c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
				String first = format.format(c.getTime());
				first = first + " 15:00:00";
				System.out.println("===============first:" + first);

				Calendar ca =  new GregorianCalendar(); // 获取当前月最后一天
				ca.setTime(date1);
				
				ca.set(Calendar.DAY_OF_MONTH,ca.getActualMaximum(Calendar.DAY_OF_MONTH));
				arrTurnCon.setSearchStart(DateUtil.str2TimestampM(first));
			} else {
				Calendar rightNow = Calendar.getInstance();
				rightNow.set(Calendar.HOUR_OF_DAY, 13);
				String searchtime = sdf.format(rightNow.getTime());
				arrTurnCon.setSearchStart(DateUtil.str2TimestampM(searchtime));
			}
			arrturnList = arrTurnService.getThisMonthUser(arrTurnCon);
			PageBean<ArrTurn> page = new PageBean<ArrTurn>(arrturnList);
			model.put("page", page);
			return "/arrTeacher/arrTeacherIndex";
		}
		// 这里是院方来安排老师
		if ("h".equals(type) || "h" == type) {
			List<Room> roomValues = roomService.queryAllRoom();
			model.put("roomValues", roomValues);
			String roomName = request.getParameter("roomName"); // 获得自己想要的科室
			String pageNum = request.getParameter("pageIndex");
			int pageIndex = 1;
			if (null != pageNum && !"".equals(pageNum)) {
				pageIndex = Integer.valueOf(pageNum);
			}
			if ("" == roomName || null == roomName) {
				PageHelper.startPage(pageIndex, 15);
				PageBean<ArrTurn> page = new PageBean<ArrTurn>(null);
				model.put("page", page);
				return "/arrTeacher/arrTeacherIndexh";
			} else {
				model.put("roomName", roomName);
				String grade = request.getParameter("grade");
				model.put("grade", grade);

				PageHelper.startPage(pageIndex, 15);
				List<ArrTurn> arrturnList = new ArrayList<ArrTurn>();
				if (null != date || !"".equals(date)) {
					Date date1 = format.parse(date);
					Calendar c = new GregorianCalendar();// 获取当前月第一天：
					c.setTime(date1);
					c.add(Calendar.MONTH, 0);
					c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
					String first = format.format(c.getTime());
					first = first + " 01:00:00";
					System.out.println("===============first:" + first);

					Calendar ca =  new GregorianCalendar(); // 获取当前月最后一天
					ca.setTime(date1);
					
					ca.set(Calendar.DAY_OF_MONTH,
							ca.getActualMaximum(Calendar.DAY_OF_MONTH));
					arrTurnCon.setSearchStart(DateUtil.str2TimestampM(first));
				}
				PageBean<ArrTurn> page = new PageBean<ArrTurn>(arrturnList);
				model.put("page", page);
				return "/arrTeacher/arrTeacherIndexh";
			}
		}
		return null;
	}

	@RequestMapping("/arrTeacherCont/arrTeachersum.htm")
	public String arrTeacherSum(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String id1 = request.getParameter("arrTurnId");
		String searchStart = request.getParameter("searchStart");
		String type = request.getParameter("type");
		String li = request.getParameter("li");
		String div = request.getParameter("div");
		String searchEnd = request.getParameter("searchEnd");
		String roomName = request.getParameter("roomName");
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		model.put("searchStart", searchStart);
		model.put("searchEnd", searchEnd);
		model.put("roomName", roomName);

		if (null != id1 && "" != id1) {
			ArrTurn arrTurn = arrTurnService.getArrTurnById(Integer
					.parseInt(id1));
			String t1 = id1 + "t1";
			String t2 = id1 + "t2";
			String teacherparm1 = request.getParameter(t1);
			String teacherparm2 = request.getParameter(t2);
			if ("" == teacherparm1 && "" == teacherparm2) {
				ControllerHelper.respOut(response, false);
			} else {
				if (null != teacherparm1 && "" != teacherparm1) {
					String teacher1[] = teacherparm1.split(":");
					String teacherNum1 = teacher1[0];
					String teacherName1 = teacher1[1];
					arrTurn.setTeacherNum1(teacherNum1);
					arrTurn.setTeacherName1(teacherName1);
				}
				if (null != teacherparm2 && "" != teacherparm2) {
					String teacher2[] = teacherparm2.split(":");
					String teacherNum2 = teacher2[0];
					String teacherName2 = teacher2[1];
					arrTurn.setTeacherNum2(teacherNum2);
					arrTurn.setTeacherName2(teacherName2);
				}
				arrTurnService.upDataArrTurn(arrTurn);
				ControllerHelper.respOut(response, true);
			}
		}
		return null;
	}

	// 通用的配置都是放在最下面
	private String getRoomSession(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("loginName");
		User teacher = new User();
		String roomName = "";
		if (obj instanceof String) {
			String loginName = (String) obj;
			teacher = userService.getByNum(loginName);
			roomName = teacher.getRoomName();
		}
		return roomName;
	}

	@Autowired
	public UserService userService;
	@Autowired
	public ArrTurnService arrTurnService;
	
	
}
