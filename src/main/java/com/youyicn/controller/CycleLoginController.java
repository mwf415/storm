package com.youyicn.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.youyicn.dao.cycle.ActivesMapper;
import com.youyicn.dao.cycle.UserMapper;
import com.youyicn.entity.LoginSession;
import com.youyicn.entity.RolePermission;
import com.youyicn.entity.User;
import com.youyicn.entity.UserRole;
import com.youyicn.entity.cycle.Actives;
import com.youyicn.service.RolePermissionService;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ActivesService;
import com.youyicn.service.cycle.UserRoleService;
import com.youyicn.util.CookieUtils;
import com.youyicn.util.JedisUtils;
import com.youyicn.util.JedisUtils.SortSet;
import com.youyicn.util.JedisUtils.Strings;
import com.youyicn.util.ListUtils;
import com.youyicn.util.MD5Utils;

@Controller
public class CycleLoginController {
	private static final int SESSION_EXPIRE=1800;
	
	@Autowired
	private UserService userService;

	@Autowired
	private RolePermissionService rolePermissionService;

	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ActivesMapper activesMapper;

	@Autowired
	private ActivesService activesService;


	@RequestMapping(value = "adminLogin.htm")
	public String adminLoginPage(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws UnsupportedEncodingException {
		String loginNameL = request.getParameter("loginNameL");
		String userPwdL = request.getParameter("userPwdL");

		userPwdL = MD5Utils.md5(userPwdL);
		User user = userService.getByNum(loginNameL);
		if (user != null && userPwdL.equals(user.getUserPwd()) && user.getStatus()==1) {
			LoginSession<User> loginSession = new LoginSession<User>();
			loginSession.setObj(user);

			HttpSession session = request.getSession(true);
			session.setAttribute("loginName", loginNameL);
			loginSession.setUserType(user.getIdentityId() + "");
			List<UserRole> urs = userRoleService.findByUid(user.getId());

			Map<String, Boolean> priMap = new HashMap<String, Boolean>();
			if (urs.size() > 0) {
				for (UserRole ur : urs) {
					List<RolePermission> rps = rolePermissionService.findByRid(ur.getRid());
					for (RolePermission rp : rps) {
						priMap.put(rp.getPid().toString(), true);// 获取所有权限
					}

					if (ur.getRid() == 8||ur.getRid()==9) {
						loginSession.setUserRole("t");
					}
					if (ur.getRid() == 6||ur.getRid()==7) {
						loginSession.setUserRole("r");
					}
					if (ur.getRid() == 4||ur.getRid()==5) {
						loginSession.setUserRole("b");
					}
					if (ur.getRid() == 1||ur.getRid()==2||ur.getRid()==3) {
						loginSession.setUserRole("h");
					}
				}
			} else {
				loginSession.setUserRole("s");
			}
			
			loginSession.setPriMap(priMap);
			session.setAttribute("loginSession", loginSession);
			session.setAttribute("loginName", user.getLoginName());
			session.setAttribute("realName", user.getRealName());
			
			/**
			 * 处理单点的登陆
			 */
			String token = UUID.randomUUID().toString();
			
			JedisUtils jedisUtil =JedisUtils.getInstance();
			JedisUtils.Strings strings=jedisUtil.new Strings();  
			strings.setEx("REDIS_SESSION_KEY:" + token,SESSION_EXPIRE, loginNameL);
			
			CookieUtils.setCookie(request, response, "TT_TOKEN", token);
						
			return "redirect:/index/indexlist.htm";
		} else {// 验证码不正确
			return "redirect:/loginerror.html";
		}
	}
	
	// 退出登录
	@RequestMapping(value = "loginout.htm")
	public String adminLoginOut(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		HttpSession session = request.getSession(true);
		
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		
		JedisUtils jedisUtil =JedisUtils.getInstance();
		JedisUtils.SortSet sortSet=jedisUtil.new SortSet();  
		sortSet.zrem("REDIS_SESSION_KEY:" +token);
		
		CookieUtils.deleteCookie(request, response, "TT_TOKEN");
		
		Object obj = session.getAttribute("loginSession");
		if (obj != null) {// 清空session
			session.removeAttribute("loginSession");			
			session.removeAttribute("loginName");
			obj = null;
		}

		return "redirect:/login.html";
	}



	//未来7天的任务，
	@RequestMapping("/index/indexlist.htm")
	public String indexlist(HttpServletRequest request, ModelMap model) {

		HttpSession session = request.getSession();
		String loginName = (String) session.getAttribute("loginName");
		model =getActivesInForture(model);
		model.put("realName", (String)session.getAttribute("realName"));
		
		model.put("loginName", loginName);
		int techNum = 0, stuNum = 0;
		techNum = userMapper.countNum(1);
		stuNum = userMapper.countNum(2);
		model.put("techNum", techNum);
		model.put("stuNum", stuNum);
	

		return "/index/index";
	}

	/**
	 * 未来7天的活动
	 * @param request
	 * @return
	 */
	
	private ModelMap  getActivesInForture(ModelMap model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		String searchStart = sdf.format(calendar.getTime());
		searchStart = "'" + searchStart + " 00:00:00" + "'";
		calendar.add(calendar.DATE, 7);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天
		String searchEnd = sdf.format(calendar.getTime());
		searchEnd = "'" + searchEnd + " 00:00:00" + "'";
		List<Actives> actList = activesService.getIndexResult(searchStart, searchEnd);
		
		List<Actives> list3 = new ArrayList(), list4 = new ArrayList(), list5 = new ArrayList(),
				list6 = new ArrayList(), list7 = new ArrayList(), list8 = new ArrayList(), list9 = new ArrayList();

		Map<Integer, List<Actives>> map2 = new HashMap<Integer, List<Actives>>();
		ListUtils.listGroup2Map(actList, map2, Actives.class, "getStatus");// 输入方法名
		for (Integer key : map2.keySet()) {
			switch (key) {
			case 3:				
				list3 = map2.get(key);
				break;
			case 4:
				list4 = map2.get(key);
				break;
			case 5:
				list5 = map2.get(key);
				break;
			case 6:
				list6 = map2.get(key);
				break;
			case 7:
				list7 = map2.get(key);
				break;
			case 8:
				list8 = map2.get(key);
				break;
			case 9:
				list9 = map2.get(key);
				break;
			}
		}
		int count3=0, count6=0,count7=0,count8=0,count9=0,count5=0,count4=0;
		model.put("count3", list3.size());
		model.put("count4", list4.size());
		model.put("count5", list5.size());
		model.put("count6", list6.size());
		model.put("count7", list7.size());
		model.put("count8", list8.size());
		model.put("count9", list9.size());

		PageInfo<Actives> page6 = new PageInfo<Actives>(list6);
		PageInfo<Actives> page7 = new PageInfo<Actives>(list7);
		PageInfo<Actives> page8 = new PageInfo<Actives>(list8);
		PageInfo<Actives> page9 = new PageInfo<Actives>(list9);
		PageInfo<Actives> page5 = new PageInfo<Actives>(list5);

		PageInfo<Actives> page3 = new PageInfo<Actives>(list3);

		PageInfo<Actives> page4 = new PageInfo<Actives>(list4);
	
		model.put("page4", page4);
		model.put("page3", page3);
		model.put("page5", page5);
		model.put("page6", page6);
		model.put("page7", page7);
		model.put("page8", page8);
		model.put("page9", page9);
		
		return model;
	}
		

}
