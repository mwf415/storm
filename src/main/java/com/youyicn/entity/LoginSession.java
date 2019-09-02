package com.youyicn.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LoginSession<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9137889503263388597L;
	
	private int userID;
	
	private String loginName;//登陆的用户名
	private String userName;
	
	private String userType;
	private String userRole;
	
	private T obj;
	private String path;//主要是保存手机还是电脑端访问
	
	private Map<String, Boolean> priMap = new HashMap<String, Boolean>();

	
	
	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public int getUserID() {
		return userID;
	}

	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserRole() {
		return userRole;
	}


	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return userName;
	}

	public void setName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Map<String, Boolean> getPriMap() {
		return priMap;
	}

	public void setPriMap(Map<String, Boolean> priMap) {
		this.priMap = priMap;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}
	
	

}
