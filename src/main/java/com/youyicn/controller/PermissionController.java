package com.youyicn.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.youyicn.entity.Permission;
import com.youyicn.service.PermissionService;
import com.youyicn.util.ControllerHelper;
import com.youyicn.util.PageBean;

@Controller
public class PermissionController {
	
	private Logger logger = Logger.getLogger(PermissionController.class);
	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("/permissionController/add_permission.htm")
	public String add_permission(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.debug("test");
		return "/permission/addperm";
	}
	
	@RequestMapping("/permissionController/addperm.htm")
	public String addperm(HttpServletRequest request,ModelMap model,HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String perName = request.getParameter("perName");
		String perUrl = request.getParameter("perUrl");
		String perPid = request.getParameter("perPid");
		
		int a = 0;
		
		if(null != perUrl && !"".equals(perUrl)){
			Permission per = new Permission();
			per.setName(perName);
			per.setUrl(perUrl);
			per.setPid(perPid);
			a = permissionService.addPermission(per);
			if(a == 1){
				ControllerHelper.respOut(response, true);
			}else{
				ControllerHelper.respOut(response, false);
			}
			
		}
		return null;
	}
	
	@RequestMapping("/permissionController/tolistper.htm")
	public String tolistper(HttpServletRequest request,String li ,String div,ModelMap model,HttpServletResponse response){	
		model.put("li", li);
		model.put("div", div);
		String pageNum = request.getParameter("pageIndex");
		int pageIndex = 1;
		if(null!=pageNum && !"".equals(pageNum) ){
			pageIndex = Integer.valueOf(pageNum);
		}
		PageHelper.startPage(pageIndex, 15);
		List<Permission> list = permissionService.findAll();
		PageBean<Permission> page = new PageBean<Permission>(list);
		model.put("page", page);
		return "/permission/perlistresult";
	}
	
	@RequestMapping("/permissionController/perview.htm")
	public String roleview(HttpServletRequest request,ModelMap model,HttpServletResponse response){
		String perId = request.getParameter("perId");
		long id = 0;
		if(null != perId && !"".equals(perId)){
			id = Long.parseLong(perId);
		}
		Permission per = permissionService.findById(id);
		model.put("per", per);
		return "/permission/perview";
	}
	

	@RequestMapping("/permissionController/toupdateper.htm")
	public String toupdaterole(HttpServletRequest request,ModelMap model,HttpServletResponse response){
		String perId = request.getParameter("perId");
		long id = 0;
		if(null != perId && !"".equals(perId)){
			id = Long.parseLong(perId);
		}
		Permission per = permissionService.findById(id);
		model.put("per", per);
		return "/permission/updateper";
	}
	
	@RequestMapping("/permissionController/updateper.htm")
	public String updaterole(HttpServletRequest request,ModelMap model,HttpServletResponse response){
		String perId = request.getParameter("perId");
		//String perName = request.getParameter("perName");
		String perUrl = request.getParameter("perUrl");
		String perPid = request.getParameter("perPid");
		long id = 0;
		if(null != perId && !"".equals(perId)){
			id = Long.parseLong(perId);
		}
		Permission per = permissionService.findById(id);
		//per.setName(perName);
		per.setUrl(perUrl);
		per.setPid(perPid);
		try{
			permissionService.updateById(per);
			ControllerHelper.respOut(response, true);
		}catch(Exception e){
			ControllerHelper.respOut(response, false);
		}
		
		return null;
	}

	@RequestMapping("/permissionController/delper.htm")
	public String delrole(HttpServletRequest request,ModelMap model,HttpServletResponse response){
		String perId = request.getParameter("perId");
		long id = 0;
		if(null != perId && !"".equals(perId)){
			id = Long.parseLong(perId);
		}
		try{
			permissionService.deleteById(id);
			ControllerHelper.respOut(response, true);
		}catch(Exception e){
			ControllerHelper.respOut(response, false);
		}
		
		return null;
	}
	
	

}
