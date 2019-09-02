package com.youyicn.controller.cycle;

import java.io.IOException;
import java.util.List;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spatial4j.core.shape.Point;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.CyclePoint;
import com.youyicn.entity.cycle.CyclePointRule;
import com.youyicn.entity.vo.PointVo;
import com.youyicn.service.cycle.CyclePointRuleService;
import com.youyicn.service.cycle.CyclePointService;
import com.youyicn.util.StringUtils;

@Controller
public class PointController {

	@Autowired
	public CyclePointRuleService cyclePointRuleService;
	
	@Autowired
	public CyclePointService cyclePointService;
	
	@RequestMapping("/point/index")
	public String index(HttpServletRequest request,ModelMap model ){
		
		
		Integer userId =getUserId(request); 
		
		List<CyclePoint> pointList= cyclePointService.getByUserId(userId);
		pointList = getPintWithStausName(pointList);//处理添加名称
		model.put("pointList", pointList);
		List<PointVo> pointVolist =cyclePointService.getPointByUserId(userId);
		model.put("pointVolist", pointVolist);
		int sumScore=0;
		if(null!=pointVolist && pointVolist.size()>0){			
			for (PointVo pointVo : pointVolist) {
				sumScore =sumScore+pointVo.getPoint_score();
				switch (pointVo.getActive_status()) {
				case 3:
					pointVo.setStatus_name("其他活动");
					break;
				case 4:
					pointVo.setStatus_name("入科教育");
					break;
				case 5:
					pointVo.setStatus_name("出科考试");
					break;
				case 6:
					pointVo.setStatus_name("教学查房");
					break;
				case 7:
					pointVo.setStatus_name("病例讨论");
					break;
				case 8:
					pointVo.setStatus_name("教学讲座");
					break;
				case 9:
					pointVo.setStatus_name("操作训练");
					break;
				
				}
			}			
		}
		model.put("sumScore", sumScore);
		return "/phone/points/pointall";
			
	}
	
	
	public List<CyclePoint> getPintWithStausName(List<CyclePoint> pointList){
		if(null!=pointList && pointList.size()>0){			
			for (CyclePoint point : pointList) {
				switch (point.getActive_status()) {
				case 3:
					point.setStatus_name("其他活动");
					break;
				case 4:
					point.setStatus_name("入科教育");
					break;
				case 5:
					point.setStatus_name("出科考试");
					break;
				case 6:
					point.setStatus_name("教学查房");
					break;
				case 7:
					point.setStatus_name("病例讨论");
					break;
				case 8:
					point.setStatus_name("教学讲座");
					break;
				case 9:
					point.setStatus_name("操作训练");
					break;				
				}
			}			
		}
		return pointList;
	}
	
	
	@RequestMapping("/point/add")
	public void add(CyclePoint cyclePoint ,HttpServletRequest request,HttpServletResponse resp) throws IOException{
		int active_status = cyclePoint.getActive_status();
		int score =0;
		if(StringUtils.NotNull(active_status) && active_status!=0){
			score= cyclePointRuleService.getScoreByActive_status(active_status);
			cyclePoint.setScore(score);
			cyclePointService.insert(cyclePoint);
			resp.getWriter().print("success");
			resp.getWriter().close();
		}else{
			resp.getWriter().print("failed");
			resp.getWriter().close();
		}		
	}
	
	// 通用的配置都是放在最下面
		private int getUserId(HttpServletRequest request) {
			Object obj = request.getSession().getAttribute("userId");
			int userId=0;
			if (obj instanceof Integer) {
				userId =(int) obj;
			}
			return userId;
		}
	
}
