package com.youyicn.entity.cycle;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;
/**
 * 临床技能，这个主要是提供一个模板，让别人来填
 */
@Component
public class Clinic implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String roomName;	
	private String clinicTxt;
	private Timestamp createTime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getClinicTxt() {
		return clinicTxt;
	}
	public void setClinicTxt(String clinicTxt) {
		this.clinicTxt = clinicTxt;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}		
	
	
}
