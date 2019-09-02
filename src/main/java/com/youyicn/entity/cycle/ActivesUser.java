package com.youyicn.entity.cycle;

public class ActivesUser {
	private Integer id;
	private String name;
	private String loginName;
	private Integer activesId;
	private Integer isIn;
	private Actives actives;
	private int identityId; // 1老师  2 学生 3  助理老师 

	private int status; // 4 入科教育，5 出科教育，6查房，7病例讨论， 8 讲座，9 训练 
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIdentityId() {
		return identityId;
	}
	public void setIdentityId(int identityId) {
		this.identityId = identityId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	
	
	public Integer getActivesId() {
		return activesId;
	}
	public void setActivesId(Integer activesId) {
		this.activesId = activesId;
	}
	public Integer getIsIn() {
		return isIn;
	}
	public void setIsIn(Integer isIn) {
		this.isIn = isIn;
	}
	public Actives getActives() {
		return actives;
	}
	public void setActives(Actives actives) {
		this.actives = actives;
	}
	
}
