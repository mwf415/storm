package com.youyicn.controller.cycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.youyicn.common.DateUtil;
import com.youyicn.entity.cycle.ActivesSingle;
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.CyleNote;
import com.youyicn.entity.cycle.Room;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.ActivesService;
import com.youyicn.service.cycle.ArrTurnService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.CyleNoteService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.util.StringUtils;
import com.youyicn.util.WordExportUtils;

/**
 * 缁熻瀛︾敓鐨勫姛鑳�
 */
@Controller
public class CountSController {
	@Autowired
	public UserService userService;
	@Autowired
	public ArrTurnService arrTurnService;
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;
	@Autowired
	private ActivesService activesService;


	@RequestMapping("/countSController/index.htm" )
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap model,String searchStart,String type,String li,String div ){
		model.put("li", li);
		model.put("div", div);
		String pageNum = request.getParameter("pageIndex");
		int pageIndex = 1;
		if (null != pageNum && !"".equals(pageNum)) {
			pageIndex = Integer.valueOf(pageNum);
		}
		String baseName = request.getParameter("baseName");
		String roomName = request.getParameter("roomName");
		
		List<Base> baseValues = baseService.queryAllBase();
		List<Room> roomValues = roomService.queryAllRoom();
		List<ArrTurn> list = new ArrayList<ArrTurn>();
		PageHelper.startPage(pageIndex, 15);
		
		if(StringUtils.NotNull(searchStart)){
			list =arrTurnService.getChangeUserByTime(searchStart, roomName,baseName);
		}else{//濡傛灉鏈夊�煎氨鏍规嵁鏃ユ湡鏉ユ煡璇紝濡傛灉娌℃湁鏃ユ湡锛屽氨鑾峰彇鏈湀鐨�
			searchStart = DateUtil.getDate();
			list =arrTurnService.getChangeUserByTime(searchStart, roomName,baseName);
		}
		PageInfo<ArrTurn> page = new PageInfo<ArrTurn>(list);
		model.put("roomNamed", roomName);
		model.put("baseNamed", baseName);
		model.put("baseValues", baseValues);
		model.put("roomValues", roomValues);
		model.put("type", type);//濡傛灉鏄煡璇oom  椤甸潰涓嬫媺妗嗗氨鍙樉绀簉oom鐨刲ist  濡傛灉鏄痓ase鐨勶紝鍙樉绀篵ase鐨刲ist
		model.put("page", page);
		model.put("searchStarted", searchStart);
		
		return "/counts/index";
	}
	
	
	@RequestMapping("/countSController/detail.htm" )
	public String detail(HttpServletRequest request,HttpServletResponse response,ModelMap model,
			String loginName,String startTime,String endTime,Integer arrTurnId ){
		startTime= startTime+" 00:00:00";
		endTime= endTime+" 23:59:55";
		Map<String,Object> m = Maps.newHashMap();
		m.put("loginName", loginName);
		m.put("startTime", startTime);
		m.put("endTime", endTime);
    	
		List<ActivesSingle> actList3 = activesService.getByLoginStartTimeEndTime(3, m);
		List<ActivesSingle> actList4 = activesService.getByLoginStartTimeEndTime(4, m);
		List<ActivesSingle> actList5 = activesService.getByLoginStartTimeEndTime(5, m);
		List<ActivesSingle> actList6 = activesService.getByLoginStartTimeEndTime(6, m);
		List<ActivesSingle> actList7 = activesService.getByLoginStartTimeEndTime(7, m);
		List<ActivesSingle> actList8 = activesService.getByLoginStartTimeEndTime(8, m);
		List<ActivesSingle> actList9 = activesService.getByLoginStartTimeEndTime(9, m);
		model.put("actList3", actList3);
		model.put("actList4", actList4);
		model.put("actList5", actList5);
		model.put("actList6", actList6);
		model.put("actList7", actList7);
		model.put("actList8", actList8);
		model.put("actList9", actList9);
		List<CyleNote> cyleNoteList  = new ArrayList<CyleNote>();
		if(StringUtils.NotNull(arrTurnId)){
			cyleNoteList = cyleNoteService.getCyleNoteByArrTurnID(arrTurnId);
		}
		model.put("arrTurnId", arrTurnId);	
		model.put("loginName", loginName);
		model.put("cyleNoteList", cyleNoteList);
		return "/counts/box";
	}
	
	/**
	 * 瀵煎嚭鍑虹瀛︾敓鐨別xcel
	 */
	@RequestMapping("/countSController/word.htm" )
	public void excel1(HttpServletRequest request,HttpServletResponse response,ModelMap model ,
			Integer arrTurnId,String loginName,String startTime,String endTime){
		startTime= startTime+" 00:00:00";
		endTime= endTime+" 23:59:55";
		//鑾峰彇瀛︾敓鐨勮疆杞�
		ArrTurn arrTurn = arrTurnService.getArrTurnById(arrTurnId);	
		//鑾峰彇瀛︾敓鐨勪功鍐欐棩蹇�
		List<CyleNote> cyleNoteList  = new ArrayList<CyleNote>();
		if(StringUtils.NotNull(arrTurnId)){
			cyleNoteList = cyleNoteService.getCyleNoteByArrTurnID(arrTurnId);
		}
		Map<String,Object> m = Maps.newHashMap();
		m.put("loginName", loginName);
		m.put("startTime", startTime);
		m.put("endTime", endTime);
	  
		
		
		List<ActivesSingle> ac3 = activesService.getByLoginStartTimeEndTime(3, m);
		List<ActivesSingle> ac6 = activesService.getByLoginStartTimeEndTime(6, m);
		List<ActivesSingle> ac7 = activesService.getByLoginStartTimeEndTime(7, m);
		List<ActivesSingle> ac8 = activesService.getByLoginStartTimeEndTime(8, m);
		List<ActivesSingle> ac9 = activesService.getByLoginStartTimeEndTime(9, m);
	    
	    
	    Map<String, Object> beanParams = new HashMap<String, Object>();   
	    
	    beanParams.put("cyleNoteList", cyleNoteList);  
	    beanParams.put("arrTurn", arrTurn); 
	    beanParams.put("ac3", ac3); 
	    beanParams.put("ac6", ac6); 
	    beanParams.put("ac7", ac7); 
	    beanParams.put("ac8", ac8); 
	    beanParams.put("ac9", ac9); 
	    WordExportUtils.writeResponse(request, response, WordExportUtils.WORD_2003, arrTurn.getLoginName(), "student.ftl", beanParams);  
	}
	
	
	
	@Autowired
	public CyleNoteService cyleNoteService;
	
	
}
