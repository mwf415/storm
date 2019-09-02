package com.youyicn.service.cycle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.PlotMapper;
import com.youyicn.entity.cycle.Plot;
import com.youyicn.service.cycle.PlotService;

@Service("plotService")
public class PlotServiceImp implements PlotService {
	
	private Logger log = LoggerFactory.getLogger(PlotServiceImp.class);

	@Resource
	public PlotMapper plotMapper;

	public Plot getPlotByBase(String baseName) {
		// TODO Auto-generated method stub
		return null;
	}

	public int addplot(Plot plot) {
		int a =plotMapper.addPlot(plot);
		return a;
	}

	public void delplot(int plotId) {
		try {
			plotMapper.delPlot(plotId);
		} catch (Exception e) {
//			e.printStackTrace();
			log.error("删除"+e);
		}
		
	}

	@Override
	public List<Plot> getPlotByCon(Plot plot) {
		List<Plot> plotList= plotMapper.getPlotByCon(plot);
		return plotList;
	}

	@Override
	public Plot getPlotById(Integer plotId) {
		Plot plot =plotMapper.getPlotById(plotId);
		return plot;
	}

	@Override
	public void updatePlot(Plot plot) {
		try {
			plotMapper.updatePlot(plot);
		} catch (Exception e) {
			
		}
		
	}

	

}
