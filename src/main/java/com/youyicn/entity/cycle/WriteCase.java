package com.youyicn.entity.cycle;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

/**
 * 书写病例
 * @author Administrator
 *
 */
@Component
public class WriteCase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String illName;
	private String illSex;
	private String illAge;
	private String illRoom;
	private String illBedNum;
	private String illNum;

	private String loginName;
	private String baseName;
	private String userName;
	private String shortAdvice;//临时医嘱
	private String longAdvice;//长期医嘱
	private String caseText;//书写病例
	
	private Timestamp createTime;
	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getCaseText() {
		return caseText;
	}

	public void setCaseText(String caseText) {
		this.caseText = caseText;
	}

	public String getIllName() {
		return illName;
	}

	public void setIllName(String illName) {
		this.illName = illName;
	}

	public String getIllSex() {
		return illSex;
	}

	public void setIllSex(String illSex) {
		this.illSex = illSex;
	}

	public String getIllAge() {
		return illAge;
	}

	public void setIllAge(String illAge) {
		this.illAge = illAge;
	}

	public String getIllRoom() {
		return illRoom;
	}

	public void setIllRoom(String illRoom) {
		this.illRoom = illRoom;
	}

	public String getIllBedNum() {
		return illBedNum;
	}

	public void setIllBedNum(String illBedNum) {
		this.illBedNum = illBedNum;
	}

	public String getIllNum() {
		return illNum;
	}

	public void setIllNum(String illNum) {
		this.illNum = illNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserNum(){
		return loginName;
	}

	public void setUserNum(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return userName;
	}

	public void setName(String userName) {
		this.userName = userName;
	}

	

	public String getShortAdvice() {
		return shortAdvice;
	}

	public void setShortAdvice(String shortAdvice) {
		this.shortAdvice = shortAdvice;
	}

	public String getLongAdvice() {
		return longAdvice;
	}

	public void setLongAdvice(String longAdvice) {
		this.longAdvice = longAdvice;
	}



	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
	
	
	


}
