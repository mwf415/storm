package com.youyicn.entity.cycle;

public class ArrturnRule {
	
	private int id;
	private String education; //学历
	private String roomName; //科室
	private String period; //周期   单位：月
	private String baseName; //基地
	private Integer type; //培训时间
	private Integer roomSort;
	
	
	
	public Integer getRoomSort() {
		return roomSort;
	}
	public void setRoomSort(Integer roomSort) {
		this.roomSort = roomSort;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	@Override
	public String toString() {
		return "ArrturnRule [id=" + id + ", education=" + education
				+ ", roomName=" + roomName + ", period=" + period + "]";
	}
	
	

}
