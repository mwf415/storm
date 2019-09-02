package com.youyicn.controller.cycle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youyicn.common.DateUtil;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ArrTurnService;
import com.youyicn.service.cycle.UserRoleService;

/**
 * 这个事用来做老师带显示的
 * @author Administrator
 *
 */
@Controller
public class TeacheringController {
	private Logger logger = Logger.getLogger(TeacheringController.class);
	public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	@Autowired
	private ArrTurnService arrTurnService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	//老师的带教统计
	@RequestMapping("/teacheringController/index.htm")
	public String accTeacherindex (HttpServletResponse response,HttpServletRequest request,ModelMap model,String li ,String div ,Integer menuOrder ){
		
		User userSession = getTeacherSession(request);
		if(userSession.getIdentityId()==2){
			return "redirect:/singleArrTurnCont/index.htm?li=li50&div=div_5&menuOrder=5&type=s";
		}else{
			String userNum = userSession.getLoginName();
			ArrTurn arrTurnCon = new ArrTurn();
			arrTurnCon.setTeacherNum1(userNum);
			String searchStart = request.getParameter("searchStart");
			if(null !=searchStart && !"".equals(searchStart)){
				arrTurnCon.setSearchStart(DateUtil.str3Timestamp(searchStart, null));
			}else{
				Calendar rightNow = Calendar.getInstance();
				rightNow.set(Calendar.HOUR_OF_DAY, 13);
				String searchtime = sdf.format(rightNow.getTime());
				arrTurnCon.setSearchStart(DateUtil.str2TimestampM(searchtime));
			}
			
			List<ArrTurn> arrTurnList = arrTurnService.getThisMonthUser(arrTurnCon);
			model.put("li", li);
			model.put("div", div);
			model.put("menuOrder", menuOrder);
			model.put("arrTurnList", arrTurnList);
			return "/teachering/index";
		}
	}
	
	//通用的配置都是放在最下面
	private User getTeacherSession(HttpServletRequest request) {
		Object obj= request.getSession().getAttribute("loginName");
		User teacher = new User();
		if(obj instanceof String){
			String userNum = (String) obj;
			teacher  = userService.getByNum(userNum);
		}
		return teacher;
	}
	
	
}
