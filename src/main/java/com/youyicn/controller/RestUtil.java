package com.youyicn.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.youyicn.common.DateUtil;
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.service.cycle.ArrTurnService;

/**
 * 服务查询通用方法接口，供查询学生调用
 * @author Administrator
 *
 */
@Controller
public class RestUtil {
	
	@Autowired
	public ArrTurnService arrTurnService;
	/**
	 * 查找出科学员
	 */
	@RequestMapping(value="/restController/outRoom.htm")
	@ResponseBody
	public List<ArrTurn> getS(String time,String roomName,String baseName){
		Calendar c = Calendar.getInstance();
		if(StringUtils.isNotBlank(time)){
			String[] ym = time.split("-");
			c.set(Calendar.YEAR,Integer.parseInt(ym[0]));
			c.set(Calendar.MONTH,Integer.parseInt(ym[1])-1);
		}
		Date startTime = DateUtil.getStartTimeOfMonth(c.getTime());
		Date endTime = DateUtil.getEndTimeOfMonth(c.getTime());
		Map<String,Object> m = Maps.newHashMap();
		m.put("roomName", roomName);
		m.put("startTime", startTime);
		m.put("endTime", endTime);
    	
		List<ArrTurn> list = arrTurnService.getOutRoomByMap(m);
		for(ArrTurn arrTurn: list){
			arrTurn.setsTime(DateUtil.date2Str(arrTurn.getStartTime(), null));
			arrTurn.seteTime(DateUtil.date2Str(arrTurn.getEndTime(), null));
		}
		return list;
	}

}
