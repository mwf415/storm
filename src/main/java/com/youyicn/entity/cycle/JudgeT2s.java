package com.youyicn.entity.cycle;

import java.sql.Timestamp;

/**
 * 老师对学生的评价
 */
public class JudgeT2s {
	private Integer id;
	private int activesId;
	private Integer isOver;
	private Integer selfStatus;
	private String roomName;
	private String baseName;
	private String teacherNum;
	private String teacherName;
	private String userName;
	private String loginName;
	
	private Timestamp createTime;
	
	private Timestamp searchStart;
	private Timestamp searchEnd;
	
	
	private String ylws;//医疗文书规范程度
	private String zlnl;//诊疗能力
	private String zdce;//周到程度
	private String brpj; //病人评价，
	private String hspj;//护士评价
	private String fxyj;//风险预见
	
	private String bzs;//病种数
	private String cfrz;//查房认真程度
	private String ylcz;//医疗操作能力
	private String cjtl;//参加讨论发言
	private String gtxz;//沟通协作
	private String zbjb;//值班交班
	
	private String llkh;//理论考核
	
	private String dlglbrsl;//独立管理病人数量
	private String cyglbrsl;//参与管理病人数量
	private String zbcs;//值班次数
	private String cqjl;//出勤记录
	
	
	
	public Integer getSelfStatus() {
		return selfStatus;
	}
	public void setSelfStatus(Integer selfStatus) {
		this.selfStatus = selfStatus;
	}
	public int getActivesId() {
		return activesId;
	}
	public void setActivesId(int activesId) {
		this.activesId = activesId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	
	
	public Integer getIsOver() {
		return isOver;
	}
	public void setIsOver(Integer isOver) {
		this.isOver = isOver;
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
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getTeacherNum() {
		return teacherNum;
	}
	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	

	public String getYlws() {
		return ylws;
	}
	public void setYlws(String ylws) {
		this.ylws = ylws;
	}
	public String getZlnl() {
		return zlnl;
	}
	public void setZlnl(String zlnl) {
		this.zlnl = zlnl;
	}
	public String getZdce() {
		return zdce;
	}
	public void setZdce(String zdce) {
		this.zdce = zdce;
	}
	public String getBrpj() {
		return brpj;
	}
	public void setBrpj(String brpj) {
		this.brpj = brpj;
	}
	public String getHspj() {
		return hspj;
	}
	public void setHspj(String hspj) {
		this.hspj = hspj;
	}
	public String getFxyj() {
		return fxyj;
	}
	public void setFxyj(String fxyj) {
		this.fxyj = fxyj;
	}
	public String getBzs() {
		return bzs;
	}
	public void setBzs(String bzs) {
		this.bzs = bzs;
	}
	public String getCfrz() {
		return cfrz;
	}
	public void setCfrz(String cfrz) {
		this.cfrz = cfrz;
	}
	public String getYlcz() {
		return ylcz;
	}
	public void setYlcz(String ylcz) {
		this.ylcz = ylcz;
	}
	public String getCjtl() {
		return cjtl;
	}
	public void setCjtl(String cjtl) {
		this.cjtl = cjtl;
	}
	
	public String getGtxz() {
		return gtxz;
	}
	public void setGtxz(String gtxz) {
		this.gtxz = gtxz;
	}
	public String getZbjb() {
		return zbjb;
	}
	public void setZbjb(String zbjb) {
		this.zbjb = zbjb;
	}
	public String getLlkh() {
		return llkh;
	}
	public void setLlkh(String llkh) {
		this.llkh = llkh;
	}
	public String getDlglbrsl() {
		return dlglbrsl;
	}
	public void setDlglbrsl(String dlglbrsl) {
		this.dlglbrsl = dlglbrsl;
	}
	public String getCyglbrsl() {
		return cyglbrsl;
	}
	public void setCyglbrsl(String cyglbrsl) {
		this.cyglbrsl = cyglbrsl;
	}
	public String getZbcs() {
		return zbcs;
	}
	public void setZbcs(String zbcs) {
		this.zbcs = zbcs;
	}
	public String getCqjl() {
		return cqjl;
	}
	public void setCqjl(String cqjl) {
		this.cqjl = cqjl;
	}
	
	
	
}
