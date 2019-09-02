package com.youyicn.entity.cycle;

public class GroupByEntity implements  Comparable<GroupByEntity> {

	private String teacherName ;  //老师姓名
	private String teacherNum;	//老师编号
	private int countNum;   //统计数
	private String baseName;	
	private String roomName;
	private String content;  
	private String startTime;
	private String type;
	private String ext01;
	private String ext02;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int getCountNum() {
		return countNum;
	}
	public void setCountNum(int countNum) {
		this.countNum = countNum;
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
	
	
	public GroupByEntity() {
	}
	
	public GroupByEntity(String teacherName) {
		this.teacherName = teacherName;
	}
	@Override
	public int compareTo(GroupByEntity o) {
		 // 按name排序
        if (this.teacherName.compareTo(o.getTeacherName()) > 0) {
            return 1;
        }
		return 0;
	}
	
	
}
