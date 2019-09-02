package com.youyicn.entity.cycle;

import org.springframework.stereotype.Component;

/**
 * 老师提问问题，学生回答问题的entity
 * @author Administrator
 *
 */
@Component
public class ActivesQuestUser {
	private int id;//问题id
	private Integer activesId;//教学查房编号
	private String questText;//问题	
	private String des;//评价 
	private String loginName;
	private String name;
	private int identityId;
	
	private ActivesQuest activesQuest;

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

	public String getQuestText() {
		return questText;
	}

	public void setQuestText(String questText) {
		this.questText = questText;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdentityId() {
		return identityId;
	}

	public void setIdentityId(int identityId) {
		this.identityId = identityId;
	}

	public ActivesQuest getActivesQuest() {
		return activesQuest;
	}

	public void setActivesQuest(ActivesQuest activesQuest) {
		this.activesQuest = activesQuest;
	}
	

}
