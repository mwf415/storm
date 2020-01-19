package com.youyicn.entity.vo;

import com.youyicn.entity.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@Component
public class ArrTurnVo {
	private int arrTurnId;
	private String loginName;
	private String hospitalId;//工号
	private Integer grade; // 级别
	private String roomName;
	private String realName;
	private Timestamp startTime;
	private Timestamp endTime;
	private String teacherNum1;
	private String teacherName1;
	private String teacherName2;
	private String teacherNum2;
	private String basename; //基地名称
	private String batch; //批次  主要分为单个创建和一次性创建 0 批量，1 单独
	private String checkStatus;//是否通过审核   1 审核通过，0 暂时未通过
	private Timestamp searchStart;//用来实现统计的
	private Timestamp searchEnd;//用来实现统计
	private Integer trainTime;//培训时间，主要用来区分研究生和本科生的
	private String sTime;
	private String eTime;
	private String nextRoomName;


	private Timestamp nextStartTime  ; // 下个科室开始的时间
	private Timestamp nextEndTime ; // 下个科室的时间
	private List <User> teacherList;


	public static void main(String[] args) {
		ArrTurnVo vo = new ArrTurnVo();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		vo.setNextEndTime(timestamp);
		System.out.println(vo.getNextEndTime().toString().substring(0,10));
		System.out.println("args = [" + args + "]");
	}




}
