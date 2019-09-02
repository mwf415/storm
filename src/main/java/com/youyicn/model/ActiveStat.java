package com.youyicn.model;

/**
 * @Description: 活动统计
 * @author: zhangxw   
 * @date: 2017年4月19日
 */
public class ActiveStat {
	
	String roomName;
	Integer status;//活动id
	Integer offiNum;//正式老师数量
	Integer stuNum;//学生数量
	Integer unOffiNum;//助教老师数量
	Integer fileNum;//上传文件数
	Integer activeNum;//活动次数
	Integer takeTime;
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOffiNum() {
		return offiNum;
	}
	public void setOffiNum(Integer offiNum) {
		this.offiNum = offiNum;
	}
	public Integer getStuNum() {
		return stuNum;
	}
	public void setStuNum(Integer stuNum) {
		this.stuNum = stuNum;
	}
	public Integer getUnOffiNum() {
		return unOffiNum;
	}
	public void setUnOffiNum(Integer unOffiNum) {
		this.unOffiNum = unOffiNum;
	}
	public Integer getFileNum() {
		return fileNum;
	}
	public void setFileNum(Integer fileNum) {
		this.fileNum = fileNum;
	}
	public Integer getActiveNum() {
		return activeNum;
	}
	public void setActiveNum(Integer activeNum) {
		this.activeNum = activeNum;
	}
	public Integer getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(Integer takeTime) {
		this.takeTime = takeTime;
	}
	
	
}
