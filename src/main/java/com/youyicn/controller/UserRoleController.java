package com.youyicn.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youyicn.entity.Permission;
import com.youyicn.entity.RolePermission;
import com.youyicn.entity.User;
import com.youyicn.entity.UserRole;
import com.youyicn.entity.cycle.PermissionVO;
import com.youyicn.entity.cycle.Role;
import com.youyicn.entity.cycle.RoleVO;
import com.youyicn.service.PermissionService;
import com.youyicn.service.RolePermissionService;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.RoleService;
import com.youyicn.service.cycle.UserRoleService;
import com.youyicn.util.ControllerHelper;

@Controller
public class UserRoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RolePermissionService rolePermissionService;
	
	private Logger logger = Logger.getLogger(UserRoleController.class);
	
	@RequestMapping("/userRoleController/add_role.htm")
	public String roleIndex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		//logger.debug("test");
		return "/role/addrole";
	}
	
	@RequestMapping("/userRoleController/addrole.htm")
	public String addRole(HttpServletRequest request,ModelMap model,HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String roleName = request.getParameter("roleName");
		String roleDes = request.getParameter("roleDes");
		String roleNum = request.getParameter("roleNum");
		
		int a = 0;
		
		if(null != roleName && !"".equals(roleName)){
			Role role = new Role();
			role.setName(roleName);
			role.setDes(roleDes);
			role.setType(roleNum);
			a = roleService.addRole(role);
			if(a == 1){
				ControllerHelper.respOut(response, true);
			}else{
				ControllerHelper.respOut(response, false);
			}
			
		}
		return null;
	}
	/**
	 * 权限管理列表
	 * @param response
	 * @return
	 */
	@RequestMapping("/userRoleController/tolistrole.htm")
	public String toRoleList(HttpServletRequest request,ModelMap model,String li ,String div,HttpServletResponse response){	
		model.put("li", li);
		model.put("div", div);
		List<Role> list = roleService.findAll();
		model.put("listRole", list);
		return "/role/rolelistresult";
	}
	
	@RequestMapping("/userRoleController/roleview.htm")
	public String roleview(HttpServletRequest request,ModelMap model,String li ,String div,HttpServletResponse response){
		model.put("li", li);
		model.put("div", div);
		String roleId = request.getParameter("roleId");
		long id = 0;
		if(null != roleId && !"".equals(roleId)){
			id = Long.parseLong(roleId);
		}
		Role role = roleService.findById(id);
		model.put("role", role);
		return "/role/roleview";
	}
	
	///userRoleController/toupdaterole.htm
	
	@RequestMapping("userRoleController/toupdaterole.htm")
	public String toupdaterole(HttpServletRequest request,ModelMap model,String li ,String div,HttpServletResponse response){
		model.put("li", li);
		model.put("div", div);
		String roleId = request.getParameter("roleId");
		long id = 0;
		if(null != roleId && !"".equals(roleId)){
			id = Long.parseLong(roleId);
		}
		Role role = roleService.findById(id);
		model.put("role", role);
		return "/role/updaterole";
	}
	
	///userRoleController/updaterole.htm
	@RequestMapping("userRoleController/updaterole.htm")
	public String updaterole(HttpServletRequest request,ModelMap model, String li ,String div,HttpServletResponse response){
		model.put("li", li);
		model.put("div", div);
		String roleId = request.getParameter("roleId");
		String roleDes = request.getParameter("roleDes");
		long id = 0;
		if(null != roleId && !"".equals(roleId)){
			id = Long.parseLong(roleId);
		}
		Role role = roleService.findById(id);
		role.setDes(roleDes);
		try{
			roleService.updateById(role);
			ControllerHelper.respOut(response, true);
		}catch(Exception e){
			ControllerHelper.respOut(response, false);
		}
		
		return null;
	}
	
	///userRoleController/delrole.htm
	@RequestMapping("/userRoleController/delrole.htm")
	public String delrole(HttpServletRequest request,ModelMap model,String li ,String div,HttpServletResponse response){
		model.put("li", li);
		model.put("div", div);
		String roleId = request.getParameter("roleId");
		long id = 0;
		if(null != roleId && !"".equals(roleId)){
			id = Long.parseLong(roleId);
		}
		try{
			roleService.deleteById(id);
			ControllerHelper.respOut(response, true);
		}catch(Exception e){
			ControllerHelper.respOut(response, false);
		}
		
		return null;
	}
	
	//角色对应权限
	@RequestMapping("/userRoleController/toassignprivilege.htm")
	public String toAssignPrivilegeRole(HttpServletRequest request,ModelMap model) {
		String roleId = request.getParameter("roleId");
		int rid = 0;
		if(null != roleId && !"".equals(roleId)){
			rid = Integer.valueOf(roleId);
		}
		Role r = roleService.findById(new Long(rid));
		model.put("roleName", r.getName());
		model.put("roleId", rid);
		return "/role/assignprivilegetorole";
	}
	

	//人员对应角色
	@RequestMapping("/userController/toassignprivilege.htm")
	public String toAssignPrivilege(HttpServletRequest request,ModelMap model) {
		String userId = request.getParameter("userId");
		int tid = 0;
		if(null != userId && !"".equals(userId)){
			tid = Integer.valueOf(userId);
		}
		User t = userService.getbyId(tid);
		model.put("userId", userId);
		model.put("teacherName", t.getRealName());
		return "/teacher/assignprivilegetorole";
	}
	/**
	 *  角色列表
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/teacherController/listPrivilege.htm")
	public String listPrivilege(HttpServletRequest request,ModelMap model,HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String userId = request.getParameter("userId");
		List<RoleVO> vos = new ArrayList<RoleVO>();
		List<Role> roles = roleService.findAll();
		//取出所有已分配角色
		List<UserRole> urList =  null;
		if(null != userId && !"".equals(userId)){
			urList = userRoleService.findByUid(Long.valueOf(userId));
		}
		for(Role role : roles){
			RoleVO vo = new RoleVO();
			vo.setId((int)role.getId());
			vo.setName(role.getName());
			vo.setOpen(true);
			vo.setpId(0);
			
			for(int i=0;i<urList.size();i++){
				UserRole ur = urList.get(i);
				if(ur.getRid() == role.getId()){
					vo.setChecked(true);
				}
			}
			vos.add(vo);
		}
		//{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
		String json = JSONArray.fromObject(vos).toString();
		
		try {
			response.getWriter().print(json);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	@RequestMapping("/userRoleController/listPrivilege.htm")
	public String listPrivilege(HttpServletRequest request,ModelMap model,String li ,String div,HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String roleId = request.getParameter("roleId");
		model.put("li", li);
		model.put("div", div);
		List<Permission> perlist = permissionService.findAll();
		List<RolePermission> rplists = null;
		if(null != roleId && !"".equals(roleId) ){
			rplists = rolePermissionService.findByRid(Long.valueOf(roleId));
		}
		
		//TODO
		List<PermissionVO> perVO = new ArrayList<PermissionVO>();
		for(Permission per : perlist){
			PermissionVO vo = new PermissionVO();
			vo.setName(per.getName());
			vo.setId(Integer.valueOf(per.getUrl()));
			vo.setpId(Integer.valueOf(per.getPid()));
			vo.setOpen(true);
			for(int i=0;i<rplists.size();i++){
				RolePermission rp = rplists.get(i);
				/*if(rp.getPid() == Long.valueOf(per.getUrl())){
					vo.setChecked(true);
				}*/
				if(rp.getPid().toString().equals(per.getUrl())){
					vo.setChecked(true);
				}
			}
			perVO.add(vo);
		}
		//{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
		String json = JSONArray.fromObject(perVO).toString();
		try {
			response.getWriter().print(json);
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}

		return null;
	}
	
	@RequestMapping("/userRoleController/assignprivilegetorole.htm")
	public String assignprivilegetorole(HttpServletRequest request,ModelMap model,HttpServletResponse response){
		String userId = request.getParameter("userId");
		String param = request.getParameter("param");
		int tid = 0;
		if(null != userId && !"".equals(userId)){
			try {					
				tid = Integer.valueOf(userId);
				//删除所有已分配角色
				userRoleService.deleteByUid(tid);
			
				String[] strarray = null;
					if(null != param && !"".equals(param)){
						strarray=param.split(","); 
						for(int i=0;i<strarray.length;i++){
							UserRole ur = new UserRole();
							ur.setUid(tid);
							ur.setRid(Integer.parseInt(strarray[i]));
							userRoleService.insert(ur);
						}
						ControllerHelper.respOut(response, true);
					}else{
						ControllerHelper.respOut(response, true);
					}	
				} catch (Exception e) {
					ControllerHelper.respOut(response, false);
				}
				
		}else{
			ControllerHelper.respOut(response, false);
		}		
		return null;
	
	}
	
	
}
