package com.youyicn.model;

/**
 * @Description: 科室统计
 * @author: zhangxw   
 * @date: 2017年4月19日
 */
public class RoomStat {
	String roomName;
	Integer teacherNum;//教师数量
	Integer inTrain;//在培
	Integer inRoom;//入科
	Integer outRoom;//出科
	Integer offiNum;//正式教师参加活动人次
	Integer unOffiNum;//非正式教师参加活动人次
	Integer takeTime;//活动总时长
	Integer activeNum;//活动次数
	Integer avgTime;//平均每次活动时间
	
	Integer inRoomTimes;
	Integer inRoomNum;
	
	Integer outRoomFnum;
	
	Integer checkRoomTimes;
	Integer checkRoomNum;
	Integer checkRoomFnum;
	
	Integer caseDiscTimes;
	Integer caseDiscNum;
	Integer caseDiscFnum;
	
	Integer techLectureTimes;
	Integer techLectureNum;
	Integer techLectureFnum;
	
	Integer practiceTimes;
	Integer practiceNum;
	Integer practiceFnum;
	
	Integer onDetyMount;//值班次数
	Integer mngMount;//管理病人数量
	Integer uploadFileCount;//上传文件
	
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Integer getTeacherNum() {
		return teacherNum;
	}
	public void setTeacherNum(Integer teacherNum) {
		this.teacherNum = teacherNum;
	}
	public Integer getInTrain() {
		return inTrain;
	}
	public void setInTrain(Integer inTrain) {
		this.inTrain = inTrain;
	}
	public Integer getInRoom() {
		return inRoom;
	}
	public void setInRoom(Integer inRoom) {
		this.inRoom = inRoom;
	}
	public Integer getOutRoom() {
		return outRoom;
	}
	public void setOutRoom(Integer outRoom) {
		this.outRoom = outRoom;
	}
	public Integer getOffiNum() {
		return offiNum;
	}
	public void setOffiNum(Integer offiNum) {
		this.offiNum = offiNum;
	}
	public Integer getUnOffiNum() {
		return unOffiNum;
	}
	public void setUnOffiNum(Integer unOffiNum) {
		this.unOffiNum = unOffiNum;
	}
	public Integer getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(Integer takeTime) {
		this.takeTime = takeTime;
	}
	public Integer getActiveNum() {
		return activeNum;
	}
	public void setActiveNum(Integer activeNum) {
		this.activeNum = activeNum;
	}
	public Integer getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(Integer avgTime) {
		this.avgTime = avgTime;
	}
	public Integer getInRoomTimes() {
		return inRoomTimes;
	}
	public void setInRoomTimes(Integer inRoomTimes) {
		this.inRoomTimes = inRoomTimes;
	}
	public Integer getInRoomNum() {
		return inRoomNum;
	}
	public void setInRoomNum(Integer inRoomNum) {
		this.inRoomNum = inRoomNum;
	}
	public Integer getCheckRoomTimes() {
		return checkRoomTimes;
	}
	public void setCheckRoomTimes(Integer checkRoomTimes) {
		this.checkRoomTimes = checkRoomTimes;
	}
	public Integer getCheckRoomNum() {
		return checkRoomNum;
	}
	public void setCheckRoomNum(Integer checkRoomNum) {
		this.checkRoomNum = checkRoomNum;
	}
	public Integer getCheckRoomFnum() {
		return checkRoomFnum;
	}
	public void setCheckRoomFnum(Integer checkRoomFnum) {
		this.checkRoomFnum = checkRoomFnum;
	}
	public Integer getCaseDiscTimes() {
		return caseDiscTimes;
	}
	public void setCaseDiscTimes(Integer caseDiscTimes) {
		this.caseDiscTimes = caseDiscTimes;
	}
	public Integer getCaseDiscNum() {
		return caseDiscNum;
	}
	public void setCaseDiscNum(Integer caseDiscNum) {
		this.caseDiscNum = caseDiscNum;
	}
	public Integer getCaseDiscFnum() {
		return caseDiscFnum;
	}
	public void setCaseDiscFnum(Integer caseDiscFnum) {
		this.caseDiscFnum = caseDiscFnum;
	}
	public Integer getTechLectureTimes() {
		return techLectureTimes;
	}
	public void setTechLectureTimes(Integer techLectureTimes) {
		this.techLectureTimes = techLectureTimes;
	}
	public Integer getTechLectureNum() {
		return techLectureNum;
	}
	public void setTechLectureNum(Integer techLectureNum) {
		this.techLectureNum = techLectureNum;
	}
	public Integer getTechLectureFnum() {
		return techLectureFnum;
	}
	public void setTechLectureFnum(Integer techLectureFnum) {
		this.techLectureFnum = techLectureFnum;
	}
	public Integer getPracticeTimes() {
		return practiceTimes;
	}
	public void setPracticeTimes(Integer practiceTimes) {
		this.practiceTimes = practiceTimes;
	}
	public Integer getPracticeNum() {
		return practiceNum;
	}
	public void setPracticeNum(Integer practiceNum) {
		this.practiceNum = practiceNum;
	}
	public Integer getPracticeFnum() {
		return practiceFnum;
	}
	public void setPracticeFnum(Integer practiceFnum) {
		this.practiceFnum = practiceFnum;
	}
	public Integer getOutRoomFnum() {
		return outRoomFnum;
	}
	public void setOutRoomFnum(Integer outRoomFnum) {
		this.outRoomFnum = outRoomFnum;
	}
	public Integer getOnDetyMount() {
		return onDetyMount;
	}
	public void setOnDetyMount(Integer onDetyMount) {
		this.onDetyMount = onDetyMount;
	}
	public Integer getMngMount() {
		return mngMount;
	}
	public void setMngMount(Integer mngMount) {
		this.mngMount = mngMount;
	}
	public Integer getUploadFileCount() {
		return uploadFileCount;
	}
	public void setUploadFileCount(Integer uploadFileCount) {
		this.uploadFileCount = uploadFileCount;
	}
	
}
