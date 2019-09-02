package com.youyicn.controller.cycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyicn.entity.User;
import com.youyicn.entity.cycle.BookRoom;
import com.youyicn.entity.cycle.ClassRoom;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.BookRoomService;
import com.youyicn.service.cycle.ClassRoomService;

@Controller
public class ClassRoomController {
	public Logger log = LoggerFactory.getLogger(ActivesController.class);
	
	@Autowired
	public UserService userService;
	@Autowired
	public ClassRoomService classRoomService;
	@Autowired
	public BookRoomService bookRoomService;
	@RequestMapping("/getBookRoom/index.htm")
	public String index(HttpServletRequest request,HttpServletResponse response, ModelMap model){
		User userSession = getUserSession(request);
		String loginName = userSession.getLoginName();
		if(loginName!=null && !"".equals(loginName)){
			List<ClassRoom> classList=classRoomService.queryAllRoom();
			model.put("classList", classList);
			return "/phone/bookRoom";
		}
		return null;
		
	}
	@RequestMapping("/getBookRoom/index_xq.htm")
	@ResponseBody
	public List<BookRoom> getBookRoom(HttpServletRequest request,HttpServletResponse response, ModelMap model){
			List<ClassRoom> classList=classRoomService.queryAllRoom();
			List<BookRoom> bookList=null;
			List<BookRoom> bList=new ArrayList<BookRoom>();
			if(classList!=null && classList.size()>0){
				for(ClassRoom room :classList){
					String roomName=room.getRoomName();
					bookList=bookRoomService.queryByName(roomName);
					bList.addAll(bookList);
				}
			}
			return bList;
	}
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
