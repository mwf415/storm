package com.youyicn.entity;

import java.io.Serializable;
import java.util.Date;

public class UserOLD implements Serializable {
	private static final long serialVersionUID = 2172801453551615424L;
	
	private int uid;
	private String loginName;
	private String hospitalId; // 医院id	
	private String realName;
	private String userPwd;
	private String userNum; // 用户编号
	private String birthday; // 出生年月
	private String workUnit; // 原工作单位
	private String cardId;// 身份证号
	private String telPhone;// 单位电话
	private String teacher1;// 医院导师
	private String teacher2;// 大学导师
	private String qq;
	private String wechat;
	private Integer identityId;// 用户身份，1：老师，2：学生
	private String sex;
	private Date birthTime;
	private Byte isLogin;
	private Integer status; // 状态
	private Integer baseId;// 所在基地
	private Integer roomId;// 科室
	private String address;// 地址
	private String gradSchool;// 毕业院校
	private String major;
	private String degree;
	private String xuewei;
	private String cellPhone;
	private String staff; // 职称
	private Integer trainTime;
	private String email;
	private int grade;
	private Integer isAt;//是否安排arrTurn；
	
	
	
	private String roomName;
	private String baseName;
	
	
	
	
	public String getRoomName() {
		return roomName;
	}


	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


	public String getBaseName() {
		return baseName;
	}


	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}


	
	
	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getTeacher1() {
		return teacher1;
	}

	public void setTeacher1(String teacher1) {
		this.teacher1 = teacher1;
	}

	public String getTeacher2() {
		return teacher2;
	}

	public void setTeacher2(String teacher2) {
		this.teacher2 = teacher2;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public Integer getIdentityId() {
		return identityId;
	}

	public void setIdentityId(Integer identityId) {
		this.identityId = identityId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	
	public Date getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
	}

	public Byte getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Byte isLogin) {
		this.isLogin = isLogin;
	}



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}


	public Integer getBaseId() {
		return baseId;
	}

	public void setBaseId(Integer baseId) {
		this.baseId = baseId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGradSchool() {
		return gradSchool;
	}

	public void setGradSchool(String gradSchool) {
		this.gradSchool = gradSchool;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getXuewei() {
		return xuewei;
	}

	public void setXuewei(String xuewei) {
		this.xuewei = xuewei;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public Integer getTrainTime() {
		return trainTime;
	}

	public void setTrainTime(Integer trainTime) {
		this.trainTime = trainTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Integer getIsAt() {
		return isAt;
	}

	public void setIsAt(Integer isAt) {
		this.isAt = isAt;
	}
	
	

	
	
}