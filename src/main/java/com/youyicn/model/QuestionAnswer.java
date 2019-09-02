package com.youyicn.model;

import java.util.Map;

/**
 * @Description: 
 * @author: zhangxw   
 * @date: 2017年3月23日
 */
public class QuestionAnswer {
	//question id
	private int qId;
	//类型
	private int type;
	//问题答案
	private Map<Integer,String> answerMap;
	
	public int getqId() {
		return qId;
	}
	
	public void setqId(int qId) {
		this.qId = qId;
	}
	
	public Map getAnswerMap() {
		return answerMap;
	}
	
	public void setAnswerMap(Map answerMap) {
		this.answerMap = answerMap;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	
	
}
