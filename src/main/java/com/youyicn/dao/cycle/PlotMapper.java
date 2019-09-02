package com.youyicn.dao.cycle;

import java.util.List;

import com.youyicn.entity.cycle.Plot;

public interface PlotMapper {
	/**
	 * 根据基地查找教学计划
	 */
	public Plot getPlotByBase(String baseName);
	/**
	 * 添加教学计划
	 */
	public int addPlot(Plot plot);
	/**
	 * 删除教学计划
	 */
	public void delPlot(int plotId);
	/**
	 * 根据条件查询
	 */
	public List<Plot> getPlotByCon(Plot plot);
	/**
	 * 根据Id查找教学计划
	 */
	public Plot getPlotById(Integer plotId);
	/**
	 * 更新教学计划
	 */
	public void updatePlot(Plot plot);
}
