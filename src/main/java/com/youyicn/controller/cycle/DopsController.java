package com.youyicn.controller.cycle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youyicn.entity.User;
import com.youyicn.entity.cycle.Dops;
import com.youyicn.entity.cycle.Soap;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.DopsService;
import com.youyicn.util.ControllerHelper;
import com.youyicn.util.PageBean;
import com.youyicn.util.ScoreUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class DopsController {
	
	private Logger logger = Logger.getLogger(DopsController.class);
	
	@Autowired
	private DopsService dopsService;
	
	@RequestMapping("/dopsController/add_dops.htm")
	public String toAddDops(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String id = request.getParameter("id");
		if(null != id){
			Dops dops = dopsService.findById(Integer.valueOf(id));
			model.put("dops", dops);
		}
		return "/dops/adddops";
	}
	
	@RequestMapping("/dopsController/addDops.htm")
	public String addDops(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Integer id) {
		//考官信息
		String kgName = request.getParameter("kgName");//考官姓名
		String kgNum = request.getParameter("kgNum");//考官工号
		String gradeDate = request.getParameter("gradeDate");//考评日期
		String gradeTime = request.getParameter("gradeTime");//时间段
		String kgIdentity = request.getParameter("kgIdentity");//考生身份
		//考生信息
		String ksName = request.getParameter("ksName");//考生姓名
		String ksNum = request.getParameter("ksNum");//考生工号
		String ksIdentity = request.getParameter("ksIdentity");//考管身份
		String khaddress = request.getParameter("khaddress");//考核地点
		String khmudi = request.getParameter("khmudi");//考核目的
		//病人信息
		String blh = request.getParameter("blh");//病历号
		String bage = request.getParameter("bage");//病人年龄
		String bsex = request.getParameter("bsex");//病人性别
		String bfh = request.getParameter("bfh");//病房号
		String bch = request.getParameter("bch");//病床号
		String complexDegree = request.getParameter("complexDegree");//病例复杂程度
		String isFirst = request.getParameter("isFirst");//病人属于
		String coordination = request.getParameter("coordination");//病人配合程度
		//
		String mtjq = request.getParameter("mtjq");//医院面谈技巧
		String tgjc = request.getParameter("tgjc");//体格检查技巧
		String zysy = request.getParameter("zysy");//人道关怀
		String lcpd = request.getParameter("lcpd");//临床判断能力
		String zxjy = request.getParameter("zxjy");//咨询建议
		String zznl = request.getParameter("zznl");//组织能力
		String ztlc = request.getParameter("ztlc");//总体临床能力
		//
		String aztlc = request.getParameter("aztlc");
		String bztlc = request.getParameter("bztlc");
		String zjzl = request.getParameter("zjzl");
		String ljwt = request.getParameter("ljwt");

		//满意度
		String kgmy = request.getParameter("kgmy");//考官满意度
		String k1 = request.getParameter("k1");//非观察项目7
		String ksmy = request.getParameter("ksmy");//考生满意度
		String k2 = request.getParameter("k2");//非观察项目7
		//
		String gcTime = request.getParameter("gcTime");//观察时间
		String hkTime = request.getParameter("hkTime");//回馈时间
		
		Dops cex = new Dops();
		cex.setKgName(kgName);
		cex.setKgNum(kgNum);
		if(null != gradeDate && !"".equals(gradeDate)){
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			Date d;
			try {
				d = sim.parse(gradeDate);
				cex.setGradeDate(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}			
		}		
		cex.setId(id);
		cex.setGradeTime(gradeTime);
		cex.setKsName(ksName);
		cex.setKsNum(ksNum);
		cex.setKsIdentity(ksIdentity);
		cex.setKgIdentity(kgIdentity);
		cex.setKhaddress(khaddress);
		cex.setKhmudi(khmudi);
		
		cex.setBlh(blh);
		cex.setBage(bage);
		cex.setBch(bch);
		cex.setBfh(bfh);
		cex.setBsex(bsex);
		cex.setComplexDegree(complexDegree);
		cex.setIsFirst(isFirst);
		cex.setCoordination(coordination);
		
		cex.setMtjq(mtjq);
		cex.setTgjc(tgjc);
		cex.setZysy(zysy);
		cex.setLcpd(lcpd);
		cex.setZxjy(zxjy);
		cex.setZznl(zznl);
		cex.setZtlc(ztlc);
		
		cex.setKgmy(kgmy);
		cex.setKsmy(ksmy);
		cex.setK1(k1);
		cex.setK2(k2);
		cex.setGcTime(gcTime);
		cex.setHkTime(hkTime);
		
		cex.setAztlc(aztlc);
		cex.setBztlc(bztlc);
		cex.setZjzl(zjzl);
		cex.setLjwt(ljwt);
		cex.setFlag("1");
		
		//考评总分
		List<String> listDops = new ArrayList<String>();
		if(!mtjq.equals("7")){
			listDops.add(mtjq);
		}
		if(!zysy.equals("7")){
			listDops.add(zysy);
		}
		if(!lcpd.equals("7")){
			listDops.add(lcpd);
		}
		if(!zxjy.equals("7")){
			listDops.add(zxjy);
		}
		if(!zznl.equals("7")){
			listDops.add(zznl);
		}
		if(!ztlc.equals("7")){
			listDops.add(ztlc);
		}
		if(!aztlc.equals("7")){
			listDops.add(aztlc);
		}
		if(!bztlc.equals("7")){
			listDops.add(bztlc);
		}
		if(!zjzl.equals("7")){
			listDops.add(zjzl);
		}
		if(!ljwt.equals("7")){
			listDops.add(ljwt);
		}
		if(!tgjc.equals("7")){
			listDops.add(tgjc);
		}
		int score = ScoreUtils.score(listDops);
		cex.setScore(String.valueOf(score));
				
		dopsService.updateById(cex);

		HttpSession session = request.getSession();
		String div = (String) session.getAttribute("div");
		String li = (String) session.getAttribute("li");
		String type = (String) session.getAttribute("type");
		Integer menuOrder = (Integer) session.getAttribute("menuOrder");

		return "redirect:/dopsController/list.htm?li="+li+"&div="+ div+"&menuOrder="+menuOrder+"&type="+type;	
	}
	
	@RequestMapping("/dopsController/list.htm")
	public String cexList(HttpServletRequest request,HttpServletResponse response,Integer menuOrder, ModelMap model,String li,String div,String type){

		HttpSession session = request.getSession();
		session.setAttribute("menuOrder", menuOrder);
		session.setAttribute("li", li);
		session.setAttribute("div", div);
		session.setAttribute("type", type);
		
		User user = getSession(request);
		
		String pageNum = request.getParameter("pageIndex");
		String ksName = request.getParameter("ksName");
		String ksNum = request.getParameter("ksNum");
		int pageIndex = 1;
		if(null!=pageNum && !"".equals(pageNum) ){
			pageIndex = Integer.valueOf(pageNum);
		}
		PageHelper.startPage(pageIndex, 10);
		Dops dops = new Dops();
		if(null != ksName && !"".equals(ksName)){
			dops.setKsName(ksName);
		}
		if(null != ksNum && !"".equals(ksNum)){
			dops.setKsNum(ksNum);
		}
	
		List<Dops> dopsList  = new ArrayList<Dops>();
		if("h".equals(type)){
			dops.setRoomName(user.getRoomName());
		}
		if("b".equals(type)){
			dops.setBaseName(user.getBaseName());
		}
		if("r".equals(type)){
			dops.setRoomName(user.getRoomName());
		}
		if("t".equals(type)){
			dops.setKgNum(user.getLoginName());
		}

		if("s".equals(type)){
			dops.setKsNum(user.getLoginName());
			
		}
		dopsList= dopsService.findAll(dops);
		PageInfo<Dops> page = new PageInfo<Dops>(dopsList);
		model.put("page", page);
		return "/dops/index";
	}
	
	@RequestMapping("/dopsController/dopsview.htm")
	public String cexView(HttpServletRequest request,
			HttpServletResponse response, ModelMap model ,Integer id){
		if(null != id && !"".equals(id)){
			Dops cex =dopsService.findById(id);			
			model.put("cex", cex);
			model.put("dac", "dac");
		}
		return "/dops/dopsview";
	}
	
	@RequestMapping("/dopsController/toupdatedops.htm")
	public String toUpdateCex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		String id = request.getParameter("id");
		if(null != id && !"".equals(id)){
			Dops cex = dopsService.findById(Integer.valueOf(id));			
			model.put("cex", cex);
		}
		return "/dops/updatedops";
	}
	
	@RequestMapping("/dopsController/updateDops.htm")
	public String updateCex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String id = request.getParameter("id");
		
		//考官信息
		String kgName = request.getParameter("kgName");//考官姓名
		String kgNum = request.getParameter("kgNum");//考官工号
		String gradeDate = request.getParameter("gradeDate");//考评日期
		String gradeTime = request.getParameter("gradeTime");//时间段
		String kgIdentity = request.getParameter("kgIdentity");
		//考生信息
		String ksName = request.getParameter("ksName");//考生姓名
		String ksNum = request.getParameter("ksNum");//考生工号
		String ksIdentity = request.getParameter("ksIdentity");//考生身份
		String khaddress = request.getParameter("khaddress");//考核地点
		String khmudi = request.getParameter("khmudi");//考核目的
		//病人信息
		String blh = request.getParameter("blh");//病历号
		String bage = request.getParameter("bage");//病人年龄
		String bsex = request.getParameter("bsex");//病人性别
		String bfh = request.getParameter("bfh");//病房号
		String bch = request.getParameter("bch");//病床号
		String complexDegree = request.getParameter("complexDegree");//病例复杂程度
		String isFirst = request.getParameter("isFirst");//病人属于
		String coordination = request.getParameter("coordination");//病人配合程度
		//
		String mtjq = request.getParameter("mtjq");//医院面谈技巧
		String tgjc = request.getParameter("tgjc");//体格检查技巧
		String zysy = request.getParameter("zysy");//人道关怀
		String lcpd = request.getParameter("lcpd");//临床判断能力
		String zxjy = request.getParameter("zxjy");//咨询建议
		String zznl = request.getParameter("zznl");//组织能力
		String ztlc = request.getParameter("ztlc");//总体临床能力
		//满意度
		String kgmy = request.getParameter("kgmy");//考官满意度
		String k1 = request.getParameter("k1");//非观察项目7
		String ksmy = request.getParameter("ksmy");//考生满意度
		String k2 = request.getParameter("k2");//非观察项目7
		//

		String gcTime = request.getParameter("gcTime");//观察时间
		String hkTime = request.getParameter("hkTime");//回馈时间
		
		//新增
		String aztlc = request.getParameter("aztlc");
		String bztlc = request.getParameter("bztlc");
		String zjzl = request.getParameter("zjzl");
		String ljwt = request.getParameter("ljwt");

		
		Dops cex = dopsService.findById(Integer.valueOf(id));
		cex.setKgName(kgName);
		cex.setKgNum(kgNum);
		if(null != gradeDate && !"".equals(gradeDate)){
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			Date d;
			try {
				d = sim.parse(gradeDate);
				cex.setGradeDate(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}			
		}	
		if(null != id && !"".equals(id)){
			cex.setId(Integer.valueOf(id));
		}
		cex.setGradeTime(gradeTime);
		cex.setKsName(ksName);
		cex.setKsNum(ksNum);
		cex.setKsIdentity(ksIdentity);
		cex.setKgIdentity(kgIdentity);
		cex.setKhaddress(khaddress);
		cex.setKhmudi(khmudi);
		
		cex.setBlh(blh);
		cex.setBage(bage);
		cex.setBch(bch);
		cex.setBfh(bfh);
		cex.setBsex(bsex);
		cex.setComplexDegree(complexDegree);
		cex.setIsFirst(isFirst);
		cex.setCoordination(coordination);

		cex.setMtjq(mtjq);
		cex.setTgjc(tgjc);
		cex.setZysy(zysy);
		cex.setLcpd(lcpd);
		cex.setZxjy(zxjy);
		cex.setZznl(zznl);
		cex.setZtlc(ztlc);
		cex.setKgmy(kgmy);
		cex.setKsmy(ksmy);
		cex.setK1(k1);
		cex.setK2(k2);
		cex.setGcTime(gcTime);
		cex.setHkTime(hkTime);
		
		cex.setAztlc(aztlc);
		cex.setBztlc(bztlc);
		cex.setZjzl(zjzl);
		cex.setLjwt(ljwt);
		
		//考评总分
		List<String> listDops = new ArrayList<String>();
		if(!mtjq.equals("7")){
			listDops.add(mtjq);
		}
		if(!zysy.equals("7")){
			listDops.add(zysy);
		}
		if(!lcpd.equals("7")){
			listDops.add(lcpd);
		}
		if(!zxjy.equals("7")){
			listDops.add(zxjy);
		}
		if(!zznl.equals("7")){
			listDops.add(zznl);
		}
		if(!ztlc.equals("7")){
			listDops.add(ztlc);
		}
		if(!aztlc.equals("7")){
			listDops.add(aztlc);
		}
		if(!bztlc.equals("7")){
			listDops.add(bztlc);
		}
		if(!zjzl.equals("7")){
			listDops.add(zjzl);
		}
		if(!ljwt.equals("7")){
			listDops.add(ljwt);
		}
		if(!tgjc.equals("7")){
			listDops.add(tgjc);
		}
		int score = ScoreUtils.score(listDops);
		cex.setScore(String.valueOf(score));
		
		try {
			dopsService.updateById(cex);
			ControllerHelper.respOut(response, true);
		} catch (Exception e) {
			ControllerHelper.respOut(response, false);
		}
		
		
		return null;
	}
	
	@RequestMapping("/dopsController/deldops.htm")
	public String delcex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		String id = request.getParameter("id");
		if(null != id && !"".equals(id)){
			try {
				dopsService.deleteById(Integer.valueOf(id));
				ControllerHelper.respOut(response, true);
			} catch (Exception e) {
				ControllerHelper.respOut(response, false);
			}
			
		}
		return null;
	}


	private User getSession(HttpServletRequest request) {
		Object obj= request.getSession().getAttribute("loginName");
		User teacher = new User();
		if(obj instanceof String){
			String loginName = (String) obj;
			teacher  = userService.getByNum(loginName);
		}
		return teacher;
	}
	@Autowired
	public UserService userService;
}
