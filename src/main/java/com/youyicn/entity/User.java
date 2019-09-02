package com.youyicn.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 2172801453551615424L;
	private int id;
	private String userNum; // 用户编号
	private String birthday; // 出生年月
	private String marry; // 婚姻状况
	private String icCard; // IC卡号
	private String political; // 政治面貌
	private String healthy; // 健康状况
	private String cardType; // 证件类型
	private String workUnit; // 原工作单位
	private String familyTel; // 家庭联系方式
	private String type; // 用户类型
	private String cardId;// 身份证号
	private String telPhone;// 单位电话
	private String teacher1;// 医院导师
	private String teacher2;// 大学导师
	private String qq;
	private String wechat;
	private Date licenTime;
	private String licenId;
	private String origin; // 籍贯
	private String loginName;
	private String realName;
	private String userPwd;
	private Integer identityId;// 用户身份，1：老师，2：学生
	private String sex;
	private Integer qualificationId;
	private Integer nation;
	private String cardNo; // 身份证号
	private Date birthTime;
	private Integer education;
	private String image;
	private String dept;
	private Byte isAdmin;
	private Integer userTypeId;
	private Byte isLogin;
	private String loginIp;
	private Integer departmentId;
	private Byte usbKey;
	private String moduleManager;
	private Integer status; // 状态 0 是注册，1 是正常
	private String hospitalId; // 医院id
	private String baseName;// 所在基地
	private String roomName;// 科室
	private String address;// 地址
	private String gradSchool;// 毕业院校
	private String major;
	private String degree;
	private String xuewei;
	private String certificationNum;
	private String cellPhone;
	private String staff; // 职称
	private Integer trainTime;
	private String email;
	private int grade;
	private Integer isAt;//是否安排arrTurn；
	
	public User() {
		super();
	}
	
	public User(int id) {
		super();
		this.id = id;
	}

	
	public Integer getIsAt() {
		return isAt;
	}

	public void setIsAt(Integer isAt) {
		this.isAt = isAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getMarry() {
		return marry;
	}

	public void setMarry(String marry) {
		this.marry = marry;
	}

	public String getIcCard() {
		return icCard;
	}

	public void setIcCard(String icCard) {
		this.icCard = icCard;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public String getHealthy() {
		return healthy;
	}

	public void setHealthy(String healthy) {
		this.healthy = healthy;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getFamilyTel() {
		return familyTel;
	}

	public void setFamilyTel(String familyTel) {
		this.familyTel = familyTel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Date getLicenTime() {
		return licenTime;
	}

	public void setLicenTime(Date licenTime) {
		this.licenTime = licenTime;
	}

	public String getLicenId() {
		return licenId;
	}

	public void setLicenId(String licenId) {
		this.licenId = licenId;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
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

	public Integer getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(Integer qualificationId) {
		this.qualificationId = qualificationId;
	}

	public Integer getNation() {
		return nation;
	}

	public void setNation(Integer nation) {
		this.nation = nation;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Date getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Byte getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Byte isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public Byte getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Byte isLogin) {
		this.isLogin = isLogin;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Byte getUsbKey() {
		return usbKey;
	}

	public void setUsbKey(Byte usbKey) {
		this.usbKey = usbKey;
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

	public String getCertificationNum() {
		return certificationNum;
	}

	public void setCertificationNum(String certificationNum) {
		this.certificationNum = certificationNum;
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

	public String getModuleManager() {
		return moduleManager;
	}

	public void setModuleManager(String moduleManager) {
		this.moduleManager = moduleManager == null ? null : moduleManager
				.trim();
	}
}