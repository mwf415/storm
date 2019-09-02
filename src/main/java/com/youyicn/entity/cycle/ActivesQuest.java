package com.youyicn.entity.cycle;

import org.springframework.stereotype.Component;

/**
 * 老师提问问题，学生回答问题的entity
 * @author Administrator
 *
 */
@Component
public class ActivesQuest {
	private int id;//问题id
	private Integer activesId;//教学查房编号
	private String questTxt;//问题	
	private String des;//评价 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Integer getActivesId() {
		return activesId;
	}
	public void setActivesId(Integer activesId) {
		this.activesId = activesId;
	}
	public String getQuestTxt() {
		return questTxt;
	}
	public void setQuestTxt(String questTxt) {
		this.questTxt = questTxt;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	


}
