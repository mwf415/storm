package com.youyicn.controller.cycle;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.youyicn.entity.LoginSession;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.Message;
import com.youyicn.entity.cycle.Room;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.MessageService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.util.PageBean;


@Controller
public class MessageController {
	Logger log = LoggerFactory.getLogger(MessageController.class);
	@Autowired
	private MessageService messageService;
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;
	/**
	 * 首页进入 过滤页面
	 */
	@RequestMapping("/messageController/message_index.htm")
	public String messageindex(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		
		return "/message/message_index";
	}
	/**
	 * 添加功能，自动跳转到添加页面
	 */
	@RequestMapping("/messageController/message_add.htm")
	public String messageadd(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		// 专业基地遍历存储		
		List<Base> baseValues = baseService.queryAllBase();
		List<Room> roomValues = roomService.queryAllRoom();
		model.put("baseValues", baseValues);
		
		model.put("roomValues", roomValues);
		return "/message/message_add";
	}
	/**
	 * 添加消息提交
	 */
	@RequestMapping("/messageController/message_addsubmit.htm")
	@ResponseBody
	public String messageaddSubmit(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String loginName = (String) request.getSession().getAttribute("loginName");
		LoginSession loginSession = (LoginSession) request.getSession().getAttribute("loginSession");
		String senderName = null;
		if(loginSession.getObj() instanceof User){
			senderName = ((User)loginSession.getObj()).getRealName();
		}else if(loginSession.getObj() instanceof User){
			senderName = ((User)loginSession.getObj()).getRealName();
		}
		String title = request.getParameter("title");
		String receiver = request.getParameter("receiver");
		String un = request.getParameter("loginName");
		String content = request.getParameter("content");
		Message msg = new Message();
		msg.setMessageTitle(title);
		msg.setMessageTxt(content);
		String finalReceiver = null;
		if(!("".equals(receiver)) && receiver != null){
			finalReceiver = receiver;
		}
		if(!("".equals(un)) && un != null){
			finalReceiver = un;
		}
		msg.setReceiver(finalReceiver);
		msg.setSender(loginName);
		msg.setSenderName(senderName);
		msg.setCreateTime(new Timestamp(System.currentTimeMillis()));
		int count = messageService.addMessage(msg);
		return String.valueOf(count);
	}
	
	/**
	 * 查询已发送的消息列表
	 */
	@RequestMapping("/messageController/messagelist_index.htm")
	public String messageList(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String pageNum = request.getParameter("pageIndex");
		int pageIndex = 1;
		if(null!=pageNum && !"".equals(pageNum) ){
			pageIndex = Integer.valueOf(pageNum);
		}
		PageHelper.startPage(pageIndex, 10);
		String loginName = (String) request.getSession().getAttribute("loginName");
		User user = new User();
		user.setUserNum(loginName);
		List<Message> msgList = messageService.findMessageByUser(user);
		PageBean<Message> page = new PageBean<Message>(msgList);
		model.put("page", page);
		model.put("userMessages", msgList);
		return "/message/messagelistindex";
	}
	/**
	 * 查询已接收的消息列表
	 */
	@RequestMapping("/messageController/messageReceivelist_index.htm")
	public String messageReceiveList(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String pageNum = request.getParameter("pageIndex");
		int pageIndex = 1;
		if(null!=pageNum && !"".equals(pageNum) ){
			pageIndex = Integer.valueOf(pageNum);
		}
		PageHelper.startPage(pageIndex, 10);
		LoginSession loginSession = (LoginSession) request.getSession().getAttribute("loginSession");
		String loginName = (String) request.getSession().getAttribute("loginName");
		String roomName = null;
		if(loginSession.getObj() instanceof User){
			User t = (User) loginSession.getObj();
			roomName = t.getRoomName();
		}else if(loginSession.getObj() instanceof User){
			User u = (User) loginSession.getObj();
			roomName = u.getRoomName();
		}
		List<Message> msgList = messageService.findMessageByRoomName(roomName,loginName);
		PageBean<Message> page = new PageBean<Message>(msgList);
		model.put("page", page);
		model.put("userMessages", msgList);
		return "/message/messagelistindex";
	}
}
