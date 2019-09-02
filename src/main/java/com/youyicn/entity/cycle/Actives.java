package com.youyicn.entity.cycle;

import java.sql.Timestamp;
import java.util.List;

/**
 * 入科教育4，出科考试5，教学查房6，病例讨论7，教学讲座8，操作训练9
 */
public class Actives {
	private int id;
	private String title;//讲座标题   疾病名称
	private String text;
	private Timestamp createTime;//创建时间
	private Timestamp startTime;//开始时间；
	private Timestamp endTime;//结束时间；
	private String baseName;
	private String roomName;
	private String address;
	private Integer status;// 8 讲座，9 操作训练   
	private Timestamp searchStart;
	private Timestamp searchEnd;
	private String sickPerson;//病人姓名
	private String sickNum;//病案号
	private Integer takeTime;//花费时间
	private Integer isOver; //是否结束
	private String org;//来源  1 院级，2  基地，3 科室
	private String des;//完成情况
	private String creater;//创建人
	private String ext02;
	private String ext01; // 这个是出科考试的学员
	private Integer fileNum;//统计的上传文件的个数

	private Integer userNum;//统计有多少学生参加
	private String teacherName; //为统计显示老师的名字
	
	
	
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	private List<ActivesUser> activesUsers;
	
	public Integer getUserNum() {
		return userNum;
	}
	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}
	public Integer getFileNum() {
		return fileNum;
	}
	public void setFileNum(Integer fileNum) {
		this.fileNum = fileNum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public String getBaseName() {
		return baseName;
	}
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getSickPerson() {
		return sickPerson;
	}
	public void setSickPerson(String sickPerson) {
		this.sickPerson = sickPerson;
	}
	public String getSickNum() {
		return sickNum;
	}
	public void setSickNum(String sickNum) {
		this.sickNum = sickNum;
	}
	public Integer getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(Integer takeTime) {
		this.takeTime = takeTime;
	}
	public String getExt01() {
		return ext01;
	}
	public void setExt01(String ext01) {
		this.ext01 = ext01;
	}
	public String getExt02() {
		return ext02;
	}
	public void setExt02(String ext02) {
		this.ext02 = ext02;
	}
	public Integer getIsOver() {
		return isOver;
	}
	public void setIsOver(Integer isOver) {
		this.isOver = isOver;
	}
	
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public List<ActivesUser> getActivesUsers() {
		return activesUsers;
	}
	public void setActivesUsers(List<ActivesUser> activesUsers) {
		this.activesUsers = activesUsers;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
}
