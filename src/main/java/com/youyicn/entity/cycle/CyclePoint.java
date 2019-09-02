package com.youyicn.entity.cycle;

import java.util.Date;

public class CyclePoint {

	private int id;
	private int user_id;
	private Date create_time;
	private int score;
	private int active_id;
	private int active_status;
	private String status_name;
	
	


	
	
	
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public int getActive_status() {
		return active_status;
	}
	public void setActive_status(int active_status) {
		this.active_status = active_status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getActive_id() {
		return active_id;
	}
	public void setActive_id(int active_id) {
		this.active_id = active_id;
	}
	
	
	
}
