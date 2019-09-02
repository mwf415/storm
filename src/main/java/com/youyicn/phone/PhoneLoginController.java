package com.youyicn.phone;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.dao.cycle.UserMapper;
import com.youyicn.dao.cycle.ActivesMapper;
import com.youyicn.entity.LoginSession;
import com.youyicn.entity.RolePermission;
import com.youyicn.entity.User;
import com.youyicn.entity.UserRole;
import com.youyicn.entity.cycle.Actives;
import com.youyicn.service.RolePermissionService;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ActivesService;
import com.youyicn.service.cycle.UserRoleService;
import com.youyicn.util.MD5Utils;
import com.youyicn.util.PageBean;

@Controller
public class PhoneLoginController {
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

	@RequestMapping(value = "wap/adminLogin.htm")
	public String adminLoginPage(HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
			throws UnsupportedEncodingException {
		String loginName = request.getParameter("loginName");
		String userPass = request.getParameter("userPass");
		userPass = MD5Utils.md5(userPass);
		User user = userService.getByNum(loginName);
		if (user != null && userPass.equals(user.getUserPwd())) {
			LoginSession <User> loginSession = new LoginSession<User>();
			loginSession.setObj(user);
			
			HttpSession session = request.getSession(true);
			session.setAttribute("loginName", loginName);
			loginSession.setUserType(user.getIdentityId()+"");
			List<UserRole> urs = userRoleService.findByUid(user.getId());	
			
			Map<String, Boolean> priMap = new HashMap<String, Boolean>();
			for(UserRole ur : urs){					
				 List<RolePermission> rps = rolePermissionService.findByRid(ur.getRid());
				 for(RolePermission rp : rps){						
					priMap.put(rp.getPid().toString(), true);//获取所有权限					
				 }	
				 if(ur.getRid()==4){
					 loginSession.setUserRole("t");
				 }
				 if(ur.getRid()==3){
					 loginSession.setUserRole("r");
				 }
				 if(ur.getRid()==2){
					 loginSession.setUserRole("b");
				 }
				 if(ur.getRid()==1){
					 loginSession.setUserRole("h");
				 }
			}
			loginSession.setPriMap(priMap);
			session.setAttribute("path", "p");
			session.setAttribute("loginSession", loginSession);
			session.setAttribute("loginName", user.getLoginName());
			return "redirect:/activesCont/index.htm";
		} else {// 验证码不正确
			return "redirect:/loginerror.html";
		}
	}

	// 退出登录
	@RequestMapping(value = "wap/loginout.htm")
	public String adminLoginOut(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		HttpSession session = request.getSession(true);
		Object obj = session.getAttribute("loginSession");
		if (obj != null) {// 清空session
			session.removeAttribute("loginSession");
			session.removeAttribute("loginName");
			obj = null;
		}

		return "redirect:/login.html";
	}
	
}
