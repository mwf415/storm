package com.youyicn.entity.cycle;


//积分规则，就是用户参加活动的规则，如入科教育多少积分，病例讨论多少积分
public class CyclePointRule {
	
	private int id;
	private int active_status;
	private int each_score;
	private String active_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getActive_status() {
		return active_status;
	}
	public void setActive_status(int active_status) {
		this.active_status = active_status;
	}
	public int getEach_score() {
		return each_score;
	}
	public void setEach_score(int each_score) {
		this.each_score = each_score;
	}
	public String getActive_name() {
		return active_name;
	}
	public void setActive_name(String active_name) {
		this.active_name = active_name;
	}
	
	
	

}
