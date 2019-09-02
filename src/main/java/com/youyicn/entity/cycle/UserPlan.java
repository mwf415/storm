package com.youyicn.entity.cycle;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.stereotype.Component;
/**
 * 教学张翔写的   不用，仅供参考
 */
@Component
public class UserPlan {

	private int planId;
	private int userId;
	private String planContext;
	private String planTitle;
	private Date planTime;
	private Date lastUpdateTime;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPlanContext() {
		return planContext;
	}
	public void setPlanContext(String planContext) {
		this.planContext = planContext;
	}
	public String getPlanTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(planTime == null){
			return "";
		}
		return sdf.format(planTime);
	}
	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public String getPlanTitle() {
		return planTitle;
	}
	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}
	public String getLastUpdateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(lastUpdateTime == null){
			return "";
		}
		return sdf.format(lastUpdateTime);
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	
}
