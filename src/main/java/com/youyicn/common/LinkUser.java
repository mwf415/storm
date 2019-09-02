package com.youyicn.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.youyicn.entity.User;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.RoomService;

@Controller
public class LinkUser {
	private static Logger logger = LoggerFactory.getLogger(LinkUser.class);
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;
	


    @RequestMapping(value="/link/roomlteacher.htm")
    @ResponseBody
    public List<User> teacherList(HttpServletRequest request,	HttpServletResponse response) {
        String roomName = request.getParameter("roomName");
    	try {
            if (!roomName.equals("")) {
            	List<User> userList =userService.getTByRoom(roomName);
            	return userList;
            } 
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.getMessage(), e);

        }
		return null;
    }
	
	
	
	 /**
     * 如果出错的话，response直接返回404
     */
    protected void renderJson(HttpServletResponse response, Object responseObject) {
        PrintWriter out = null;
        try {
            if (responseObject == null) {
                response.sendError(404);
                return;
            }
            String responseStr = JSONObject.toJSONString(responseObject);//将java对象转换为son对象
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");

            out = response.getWriter();
            out.append(responseStr);

            logger.debug("返回是：" + responseStr);
        } catch (IOException e) {
            logger.error(e.getMessage());
            logger.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
