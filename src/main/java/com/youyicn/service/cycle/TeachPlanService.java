package com.youyicn.service.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.TeachPlan;

/**
 * 教学计划service
 * @author Administrator
 *
 */
@Service
public interface TeachPlanService {
	public List<TeachPlan> getTeachPlan(TeachPlan teachPlan);
	/**
	 * 添加教学计划
	 * @param teachPlan
	 */
	public void addTeachPlan(TeachPlan teachPlan);
	//根据Id删除教学计划
	public void delTeachPlan(Integer planId);
	public TeachPlan getTeachPlanById(Integer planId);
	
	public void updateTeachePlan(TeachPlan teachPlan);
	
}
