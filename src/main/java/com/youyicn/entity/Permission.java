package com.youyicn.entity;

import java.io.Serializable;

public class Permission implements Serializable{
	

	private static final long serialVersionUID = 5540283269191418145L;
	private Long id;
	private String url;
	private String name;
	private String pid;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}

}
