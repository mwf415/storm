package com.youyicn.entity.cycle;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;
import com.youyicn.entity.User;

@Component
public class ArrTurn {
	private int arrTurnId;
	private String loginName;
	private String hospitalId;//工号
	private Integer grade; // 级别
	private String roomName;
	private String realName;
	private Timestamp startTime;
	private Timestamp endTime;
	private String teacherNum1;
	private String teacherName1;
	private String teacherName2;
	private String teacherNum2;
	private String basename; //基地名称
	private String batch; //批次  主要分为单个创建和一次性创建 0 批量，1 单独
	private String checkStatus;//是否通过审核   1 审核通过，0 暂时未通过
	private Timestamp searchStart;//用来实现统计的
	private Timestamp searchEnd;//用来实现统计
	private Integer trainTime;//培训时间，主要用来区分研究生和本科生的
	private String sTime;
	private String eTime;
	private String nextRoomName;
	
	private List <User> teacherList;
	
	
	

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public List<User> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<User> teacherList) {
		this.teacherList = teacherList;
	}

	public String getNextRoomName() {
		return nextRoomName;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public void setNextRoomName(String nextRoomName) {
		this.nextRoomName = nextRoomName;
	}

	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	public String geteTime() {
		return eTime;
	}

	public void seteTime(String eTime) {
		this.eTime = eTime;
	}

	

	public Integer getTrainTime() {
		return trainTime;
	}

	public void setTrainTime(Integer trainTime) {
		this.trainTime = trainTime;
	}

	public Timestamp getSearchStart() {
		return searchStart;
	}

	public void setSearchStart(Timestamp searchStart) {
		this.searchStart = searchStart;
	}

	public Timestamp getSearchEnd() {
		return searchEnd;
	}

	public void setSearchEnd(Timestamp searchEnd) {
		this.searchEnd = searchEnd;
	}

	public String getTeacherNum1() {
		return teacherNum1;
	}

	public void setTeacherNum1(String teacherNum1) {
		this.teacherNum1 = teacherNum1;
	}

	public String getTeacherNum2() {
		return teacherNum2;
	}

	public void setTeacherNum2(String teacherNum2) {
		this.teacherNum2 = teacherNum2;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getArrTurnId() {
		return arrTurnId;
	}

	public void setArrTurnId(int arrTurnId) {
		this.arrTurnId = arrTurnId;
	}

	
	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getTeacherName1() {
		return teacherName1;
	}

	public void setTeacherName1(String teacherName1) {
		this.teacherName1 = teacherName1;
	}

	public String getTeacherName2() {
		return teacherName2;
	}

	public void setTeacherName2(String teacherName2) {
		this.teacherName2 = teacherName2;
	}

	

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getBasename() {
		return basename;
	}

	public void setBasename(String basename) {
		this.basename = basename;
	}
	

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	

}
