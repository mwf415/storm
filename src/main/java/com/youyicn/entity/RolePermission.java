package com.youyicn.entity;

import java.io.Serializable;

public class RolePermission implements Serializable{


	private static final long serialVersionUID = -8288627411061400908L;
	
	private Long id;
	
	private Long rid;
	   
	private Long pid;

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
