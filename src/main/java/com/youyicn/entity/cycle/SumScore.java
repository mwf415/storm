package com.youyicn.entity.cycle;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

/**
 * 总汇总
 */
@Component
public class SumScore implements Serializable{
	private static final long serialVersionUID = 1L;

	private int activesId;
	private int id;
	private int isOver;
	private String userNum;
	private String userName;
	private String roomName;
	private String teacherName;//指导老师
	private String teacherNum;//指导老师
	private String createName;//创建人
	private String createNum;//创建人
	
	private String absenceMount;//缺席天数1
	private String onDetyMount;//值班次数2
	private String sickMount;//学习病种和例数3
	private String mngMount;//管理病人数量4
	private Integer uploadFileCount;//书写病例数量
	private String writeQuality;//病例书写质量5
	private String operateMount;//操作技能种数6
	private String thinking;//临床思维7
	private String virtues;//品德8
	private String teachAbility;//教学能力9
	
	private String learnAmount;//各种形式学习多少次10
	private String science;//科研情况11
	private String mistake;//医疗差错；12
	private String mini_cex;//mini考试13
	private String theoryExam;//理论考试14
	private String practiceExam;//临床时间能力考试15
	private String soap;//soap打分16
	private String nurseDes;//护士打分；17
	private String sickDes;//病人打分18
	private Timestamp createTime;	//创建时间

	
	private Timestamp searchStart;
	private Timestamp searchEnd;
	
	private String theoryscore; 
	
	
	
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
	public int getIsOver() {
		return isOver;
	}
	public void setIsOver(int isOver) {
		this.isOver = isOver;
	}
	public int getActivesId() {
		return activesId;
	}
	public void setActivesId(int activesId) {
		this.activesId = activesId;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getTheoryscore() {
		return theoryscore;
	}
	public void setTheoryscore(String theoryscore) {
		this.theoryscore = theoryscore;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getCreateNum() {
		return createNum;
	}
	public void setCreateNum(String createNum) {
		this.createNum = createNum;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherNum() {
		return teacherNum;
	}
	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAbsenceMount() {
		return absenceMount;
	}
	public void setAbsenceMount(String absenceMount) {
		this.absenceMount = absenceMount;
	}
	public String getOnDetyMount() {
		return onDetyMount;
	}
	public void setOnDetyMount(String onDetyMount) {
		this.onDetyMount = onDetyMount;
	}
	public String getSickMount() {
		return sickMount;
	}
	public void setSickMount(String sickMount) {
		this.sickMount = sickMount;
	}
	public String getMngMount() {
		return mngMount;
	}
	public void setMngMount(String mngMount) {
		this.mngMount = mngMount;
	}
	public String getWriteQuality() {
		return writeQuality;
	}
	public void setWriteQuality(String writeQuality) {
		this.writeQuality = writeQuality;
	}
	public String getOperateMount() {
		return operateMount;
	}
	public void setOperateMount(String operateMount) {
		this.operateMount = operateMount;
	}
	public String getThinking() {
		return thinking;
	}
	public void setThinking(String thinking) {
		this.thinking = thinking;
	}
	public String getVirtues() {
		return virtues;
	}
	public void setVirtues(String virtues) {
		this.virtues = virtues;
	}
	public String getTeachAbility() {
		return teachAbility;
	}
	public void setTeachAbility(String teachAbility) {
		this.teachAbility = teachAbility;
	}
	public String getLearnAmount() {
		return learnAmount;
	}
	public void setLearnAmount(String learnAmount) {
		this.learnAmount = learnAmount;
	}
	public String getScience() {
		return science;
	}
	public void setScience(String science) {
		this.science = science;
	}
	public String getMistake() {
		return mistake;
	}
	public void setMistake(String mistake) {
		this.mistake = mistake;
	}
	public String getMini_cex() {
		return mini_cex;
	}
	public void setMini_cex(String mini_cex) {
		this.mini_cex = mini_cex;
	}
	public String getTheoryExam() {
		return theoryExam;
	}
	public void setTheoryExam(String theoryExam) {
		this.theoryExam = theoryExam;
	}
	public String getPracticeExam() {
		return practiceExam;
	}
	public void setPracticeExam(String practiceExam) {
		this.practiceExam = practiceExam;
	}
	public String getSoap() {
		return soap;
	}
	public void setSoap(String soap) {
		this.soap = soap;
	}
	public String getNurseDes() {
		return nurseDes;
	}
	public void setNurseDes(String nurseDes) {
		this.nurseDes = nurseDes;
	}
	public String getSickDes() {
		return sickDes;
	}
	public void setSickDes(String sickDes) {
		this.sickDes = sickDes;
	}
	public Integer getUploadFileCount() {
		return uploadFileCount;
	}
	public void setUploadFileCount(Integer uploadFileCount) {
		this.uploadFileCount = uploadFileCount;
	}
	
	
}
