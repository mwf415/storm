package com.youyicn.entity.cycle;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.youyicn.entity.Permission;



public class Role implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5491231380492426522L;

	private long id;
	
    /**角色名称*/
    private String name;
    /**角色类型*/
    private String type;
    
    private String des;
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	//***做 role --> permission 一对多处理
    private List<Permission> permissions = new LinkedList<Permission>();

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}


}
