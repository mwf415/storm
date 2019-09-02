package com.youyicn.service.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.Plot;
/**
 * 教学计划，文本格式
 * @author Administrator
 *
 */
@Service
public interface PlotService {
	/**
	 * 根据基地查找教学计划
	 */
	public Plot getPlotByBase(String baseName);
	/**
	 * 添加教学计划
	 */
	public int addplot(Plot plot);
	/**
	 * 删除教学计划
	 */
	public void delplot(int plotId);
	/**
	 * 根据教学计划，查找教学计划，是为了显示全部；
	 */
	public List<Plot> getPlotByCon(Plot plot);
	/**
	 * 根据教学计划Id查找教学计划
	 */
	public Plot getPlotById(Integer plotId);
	/**
	 * 更新教学计划
	 */
	public void updatePlot(Plot plot);
}
