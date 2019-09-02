package com.youyicn.entity.cycle;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;
/**
 * 教学计划，是文本格式
 * @author Administrator
 *
 */
@Component
public class Plot {
	private int plotId;
	private String baseName;	
	private String plotTxt;
	private Timestamp createTime;		
	private Timestamp modifyTime;
	
	
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getPlotTxt() {
		return plotTxt;
	}
	public void setPlotTxt(String plotTxt) {
		this.plotTxt = plotTxt;
	}
	public String getBaseName() {
		return baseName;
	}
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}
	public int getPlotId() {
		return plotId;
	}
	public void setPlotId(int plotId) {
		this.plotId = plotId;
	}


	
}
