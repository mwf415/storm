package com.youyicn.entity.cycle;

public class ActivesUserJson {
	private String userNum;
	private String userName;
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "ActivesUserJson [userNum=" + userNum + ", userName=" + userName
				+ "]";
	}
	

}
