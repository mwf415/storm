package com.youyicn.entity;

import java.io.Serializable;

public class UserRole implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8455703751336804596L;

	private Integer id;
	private Integer uid;

    private Integer rid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}
    


}
