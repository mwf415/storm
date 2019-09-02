package com.youyicn.controller.cycle;



import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.youyicn.common.DateUtil;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.Plot;
import com.youyicn.entity.cycle.Room;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.PlotService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.util.ControllerHelper;
/**
 * 教学计划，是国家规定的文档格式的。
 */
@Controller
public class PlotController {
	public Logger log = LoggerFactory.getLogger(PlotController.class);
	@Autowired
	public PlotService plotService;
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;
	//  渲染页面
	@RequestMapping("/plotController/plot_index.htm")
	public String Plotindex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,String li,String div) {
		model.put("li", li);
		model.put("div", div);
		
		String type = request.getParameter("type");
		Plot Plot =new Plot();
		List<Plot> plotList = plotService.getPlotByCon( Plot);
		PageInfo<Plot> page = new PageInfo<Plot>(plotList);
		model.put("page", page);
		if("h"==type ||"h" .equals(type)){
			return "/plot/plotIndex";
		}else {
			return "/plot/splotIndex";
		}
		
	}
	
	/**
	 * 添加教学计划
	 */
	@RequestMapping("/plotController/plotAdd.htm")
	public String PlotAdd(HttpServletRequest request,HttpServletResponse response,ModelMap model	){
		List<Base> baseValues = baseService.queryAllBase();
		List<Room> roomValues = roomService.queryAllRoom();
		model.put("baseList", baseValues);
		
		return "/plot/plotAdd";
		
	}
	/**
	 * 提交教学计划
	 */
	@RequestMapping("/plotController/plotSum.htm")
	public String plotSum(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String baseName= request.getParameter("baseName");
		String plotTxt= request.getParameter("plotTxt");
		try {
			if(baseName!=null&& plotTxt!=null){
				Plot plot = new Plot();
				plot.setBaseName(baseName);
				plot.setPlotTxt(plotTxt);
				plot.setCreateTime(DateUtil.date2Timestamp(new Date()));
				int a = plotService.addplot(plot);
				ControllerHelper.respOut(response, true);
			}
		} catch (Exception e) {
			ControllerHelper.respOut(response, false);
		}
		return null;
		
	}
	/**
	 * 删除
	 */
	@RequestMapping("/plotController/delPlot.htm")
	public String delPlot(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String plotId1= request.getParameter("plotId");
		if(plotId1!=null&& plotId1!=""){
			Integer plotId= Integer.parseInt(plotId1);
			plotService.delplot(plotId);
		}
		if(null != plotId1 && !"".equals(plotId1)){
			int plotId = Integer.parseInt(plotId1);
			try {
				plotService.delplot(plotId);
				ControllerHelper.respOut(response, "true");
			} catch (Exception e) {
				ControllerHelper.respOut(response, "false");
			}
		}
		 return null;
		

	}
	/**
	 * 详情页面
	 */
	@RequestMapping("/plotController/plotDetail.htm")
	public String plotDetail(HttpServletResponse response,HttpServletRequest request ,ModelMap model){
		String plotId1= request.getParameter("plotId");
		if(plotId1!=null&& plotId1!=""){
			Integer plotId = Integer.parseInt(plotId1);
			Plot plot =plotService.getPlotById(plotId);
			model.put("plot", plot);
		}
		return "/plot/plotDetail";
	}
	/**
	 * 修改页面
	 */
	@RequestMapping("/plotController/editPlot.htm")
	public String editPlot(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String plotId1= request.getParameter("plotId");
		if(plotId1!=null&& plotId1!=""){
			Integer plotId = Integer.parseInt(plotId1);
			Plot plot= plotService.getPlotById(plotId);
			model.put("plot", plot);
		}
		return "/plot/editPlot";
	}
	/**
	 * 修改提交页面
	 */
	@RequestMapping("/plotController/plotEditSum.htm")
	public String editPlotSum(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String plotId1 = request.getParameter("plotId");
		Integer plotId =null;
		if(plotId1!=null && plotId1!=""){
			plotId = Integer.parseInt(plotId1);
		}
		String plotTxt= request.getParameter("plotTxt");
		
		if(plotTxt!=null&& plotTxt!=null){
			Plot plot = new Plot();
			plot.setPlotId(plotId);
			plot.setPlotTxt(plotTxt);
			plot.setModifyTime(DateUtil.date2Timestamp(new Date()));
			plotService.updatePlot(plot);
		}
		return null;
	}
	
}

