package com.youyicn.entity.cycle;

import java.io.Serializable;
import java.util.Date;

public class Soap  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7987544150536279793L;
	/**
	 * 
	 */
	
	private int id;
	private int activesId;
	private String roomName;
	private String baseName;
	
	private String kgName;
	private String kgNum;
	private Date gradeDate;
	private String gradeTime;
	private String ksName;
	private String ksNum;
	private String ksIdentity;
	private String khaddress;
	private String khmudi;
	private String blh;
	private String bage;
	private String bsex;
	private String bfh;
	private String bch;	
	private String complexDegree;
	private String mtjq;
	private String tgjc;
	private String zysy;
	private String lcpd;
	private String zxjy;
	private String zznl;
	private String ztlc;
	
	//新
	private String aztlc;
	private String bztlc;
	private String zjzl;
	private String ljwt;
	private String wtjh;
	private String jgsf;	
	private String apjc;
	private String zd;
	private String cz;
	private String ywzl;
	private String jkjy;
	private String jmsf;
	
	private String kgmy;
	private String k1;
	private String ksmy;
	private String k2;
	private String fkyd;
	private String wcjh;
	private String gcTime;
	private String hkTime;
	private String score;
	
	private String flag;//是否考试  1已经考试、0未考试
	
	
	
	public int getActivesId() {
		return activesId;
	}
	public void setActivesId(int activesId) {
		this.activesId = activesId;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKgName() {
		return kgName;
	}
	public void setKgName(String kgName) {
		this.kgName = kgName;
	}
	public String getKgNum() {
		return kgNum;
	}
	public void setKgNum(String kgNum) {
		this.kgNum = kgNum;
	}
	public Date getGradeDate() {
		return gradeDate;
	}
	public void setGradeDate(Date gradeDate) {
		this.gradeDate = gradeDate;
	}
	public String getGradeTime() {
		return gradeTime;
	}
	public void setGradeTime(String gradeTime) {
		this.gradeTime = gradeTime;
	}
	public String getKsName() {
		return ksName;
	}
	public void setKsName(String ksName) {
		this.ksName = ksName;
	}
	public String getKsNum() {
		return ksNum;
	}
	public void setKsNum(String ksNum) {
		this.ksNum = ksNum;
	}
	public String getKsIdentity() {
		return ksIdentity;
	}
	public void setKsIdentity(String ksIdentity) {
		this.ksIdentity = ksIdentity;
	}
	public String getKhaddress() {
		return khaddress;
	}
	public void setKhaddress(String khaddress) {
		this.khaddress = khaddress;
	}
	public String getKhmudi() {
		return khmudi;
	}
	public void setKhmudi(String khmudi) {
		this.khmudi = khmudi;
	}
	public String getBlh() {
		return blh;
	}
	public void setBlh(String blh) {
		this.blh = blh;
	}
	public String getBage() {
		return bage;
	}
	public void setBage(String bage) {
		this.bage = bage;
	}
	public String getBsex() {
		return bsex;
	}
	public void setBsex(String bsex) {
		this.bsex = bsex;
	}
	public String getBfh() {
		return bfh;
	}
	public void setBfh(String bfh) {
		this.bfh = bfh;
	}
	public String getBch() {
		return bch;
	}
	public void setBch(String bch) {
		this.bch = bch;
	}
	public String getComplexDegree() {
		return complexDegree;
	}
	public void setComplexDegree(String complexDegree) {
		this.complexDegree = complexDegree;
	}
	public String getMtjq() {
		return mtjq;
	}
	public void setMtjq(String mtjq) {
		this.mtjq = mtjq;
	}
	public String getTgjc() {
		return tgjc;
	}
	public void setTgjc(String tgjc) {
		this.tgjc = tgjc;
	}
	public String getZysy() {
		return zysy;
	}
	public void setZysy(String zysy) {
		this.zysy = zysy;
	}
	public String getLcpd() {
		return lcpd;
	}
	public void setLcpd(String lcpd) {
		this.lcpd = lcpd;
	}
	public String getZxjy() {
		return zxjy;
	}
	public void setZxjy(String zxjy) {
		this.zxjy = zxjy;
	}
	public String getZznl() {
		return zznl;
	}
	public void setZznl(String zznl) {
		this.zznl = zznl;
	}
	public String getZtlc() {
		return ztlc;
	}
	public void setZtlc(String ztlc) {
		this.ztlc = ztlc;
	}
	public String getAztlc() {
		return aztlc;
	}
	public void setAztlc(String aztlc) {
		this.aztlc = aztlc;
	}
	public String getBztlc() {
		return bztlc;
	}
	public void setBztlc(String bztlc) {
		this.bztlc = bztlc;
	}
	public String getZjzl() {
		return zjzl;
	}
	public void setZjzl(String zjzl) {
		this.zjzl = zjzl;
	}
	public String getLjwt() {
		return ljwt;
	}
	public void setLjwt(String ljwt) {
		this.ljwt = ljwt;
	}
	public String getWtjh() {
		return wtjh;
	}
	public void setWtjh(String wtjh) {
		this.wtjh = wtjh;
	}
	public String getJgsf() {
		return jgsf;
	}
	public void setJgsf(String jgsf) {
		this.jgsf = jgsf;
	}
	public String getApjc() {
		return apjc;
	}
	public void setApjc(String apjc) {
		this.apjc = apjc;
	}
	public String getZd() {
		return zd;
	}
	public void setZd(String zd) {
		this.zd = zd;
	}
	public String getCz() {
		return cz;
	}
	public void setCz(String cz) {
		this.cz = cz;
	}
	public String getYwzl() {
		return ywzl;
	}
	public void setYwzl(String ywzl) {
		this.ywzl = ywzl;
	}
	public String getJkjy() {
		return jkjy;
	}
	public void setJkjy(String jkjy) {
		this.jkjy = jkjy;
	}
	public String getJmsf() {
		return jmsf;
	}
	public void setJmsf(String jmsf) {
		this.jmsf = jmsf;
	}
	public String getKgmy() {
		return kgmy;
	}
	public void setKgmy(String kgmy) {
		this.kgmy = kgmy;
	}
	public String getK1() {
		return k1;
	}
	public void setK1(String k1) {
		this.k1 = k1;
	}
	public String getKsmy() {
		return ksmy;
	}
	public void setKsmy(String ksmy) {
		this.ksmy = ksmy;
	}
	public String getK2() {
		return k2;
	}
	public void setK2(String k2) {
		this.k2 = k2;
	}
	public String getFkyd() {
		return fkyd;
	}
	public void setFkyd(String fkyd) {
		this.fkyd = fkyd;
	}
	public String getWcjh() {
		return wcjh;
	}
	public void setWcjh(String wcjh) {
		this.wcjh = wcjh;
	}
	public String getGcTime() {
		return gcTime;
	}
	public void setGcTime(String gcTime) {
		this.gcTime = gcTime;
	}
	public String getHkTime() {
		return hkTime;
	}
	public void setHkTime(String hkTime) {
		this.hkTime = hkTime;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	

}
