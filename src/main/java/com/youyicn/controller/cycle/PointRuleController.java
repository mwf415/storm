package com.youyicn.controller.cycle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youyicn.entity.cycle.CyclePointRule;
import com.youyicn.service.cycle.CyclePointRuleService;
import com.youyicn.service.cycle.CyleNoteService;

@Controller
public class PointRuleController {

	@Autowired
	public CyclePointRuleService cyclePointRuleService;
	
	@RequestMapping("/pointrule/index")
	public String index(ModelMap model,String li, String div, Integer menuOrder){
		model.put("li", li);
		model.put("div", div);
		
		List<CyclePointRule> list= cyclePointRuleService.findAll();
		model.put("rulelist", list);
		
		return "/points/index";
			
	}
	@RequestMapping("/pointrule/edit")
	public String edit(String li, String div,ModelMap model,int each_score,int id){
			
		CyclePointRule rule = new CyclePointRule();
		rule.setId(id);
		rule.setEach_score(each_score);
		cyclePointRuleService.updateById(rule);
		
		return "redirect:/pointrule/index.htm?li=li15&div=div_1&menuOrder=1";
		
	}
}
