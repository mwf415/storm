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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.Soap;
import com.youyicn.service.UserService;
import com.youyicn.service.cycle.SoapService;
import com.youyicn.util.ControllerHelper;
import com.youyicn.util.ScoreUtils;

@Controller
public class SoapController {
	
	private Logger logger = Logger.getLogger(SoapController.class);
	
	@Autowired
	private SoapService soapService;
	
	@RequestMapping("/soapController/add_soap.htm")
	public String toAddGrade(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String id = request.getParameter("id");//soapId
		if(null != id){
			int soapId = Integer.valueOf(id);
			Soap soap = soapService.findById(soapId);
			model.put("soap", soap);
		}
		
		return "/soap/addsoap";
	}
	
	@RequestMapping("/soapController/addSoap.htm")
	public String addSoap(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Integer id) {
		//考官信息
		String kgName = request.getParameter("kgName");//考官姓名
		String kgNum = request.getParameter("kgNum");//考官工号
		String gradeDate = request.getParameter("gradeDate");//考评日期
		String gradeTime = request.getParameter("gradeTime");//时间段
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
		String wtjh = request.getParameter("wtjh");
		String jgsf = request.getParameter("jgsf");
		String apjc = request.getParameter("apjc");
		String zd = request.getParameter("zd");
		String cz = request.getParameter("cz");
		String ywzl = request.getParameter("ywzl");
		String jkjy = request.getParameter("jkjy");
		String jmsf = request.getParameter("jmsf");
		
		
		//满意度
		String kgmy = request.getParameter("kgmy");//考官满意度
		String k1 = request.getParameter("k1");//非观察项目7
		String ksmy = request.getParameter("ksmy");//考生满意度
		String k2 = request.getParameter("k2");//非观察项目7
		//
		String fkyd = request.getParameter("fkyd");//考官对于考生的主要反馈要点
		String wcjh = request.getParameter("wcjh");//完成较好部分
		String gcTime = request.getParameter("gcTime");//观察时间
		String hkTime = request.getParameter("hkTime");//回馈时间
		
		Soap cex = new Soap();
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
		cex.setKhaddress(khaddress);
		cex.setKhmudi(khmudi);
		
		cex.setBlh(blh);
		cex.setBage(bage);
		cex.setBch(bch);
		cex.setBfh(bfh);
		cex.setBsex(bsex);
		cex.setComplexDegree(complexDegree);
		cex.setFkyd(fkyd);
		
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
		cex.setWcjh(wcjh);
		cex.setGcTime(gcTime);
		cex.setHkTime(hkTime);
		
		cex.setAztlc(aztlc);
		cex.setBztlc(bztlc);
		cex.setZjzl(zjzl);
		cex.setLjwt(ljwt);
		cex.setWtjh(wtjh);
		cex.setJgsf(jgsf);
		cex.setApjc(apjc);
		cex.setZd(zd);
		cex.setCz(cz);
		cex.setYwzl(ywzl);
		//cex.setYwzl(ywzl);
		cex.setJkjy(jkjy);
		cex.setJmsf(jmsf);
		
		List<String> listSoap = new ArrayList<String>();
		listSoap.add(mtjq);
		listSoap.add(tgjc);
		listSoap.add(zysy);
		listSoap.add(lcpd);
		listSoap.add(zxjy);
		listSoap.add(zznl);
		listSoap.add(ztlc);
		listSoap.add(aztlc);
		listSoap.add(bztlc);
		listSoap.add(zjzl);
		listSoap.add(ljwt);
		listSoap.add(wtjh);
		listSoap.add(jgsf);
		listSoap.add(apjc);
		listSoap.add(zd);
		listSoap.add(cz);
		listSoap.add(ywzl);
		listSoap.add(jkjy);
		listSoap.add(jmsf);
		int score = ScoreUtils.score(listSoap);
		cex.setScore(String.valueOf(score));
		cex.setFlag("1");
		soapService.updateById(cex);
		HttpSession session = request.getSession();
		String div = (String) session.getAttribute("div");
		String li = (String) session.getAttribute("li");
		String type = (String) session.getAttribute("type");
		Integer menuOrder = (Integer) session.getAttribute("menuOrder");

		return "redirect:/soapController/list.htm?li="+li+"&div="+ div+"&menuOrder="+menuOrder+"&type="+type;	
	}
	
	@RequestMapping("/soapController/list.htm")
	public String cexList(HttpServletRequest request,Integer menuOrder,
			HttpServletResponse response, ModelMap model,String li,String div,String type){
		model.put("li", li);
		model.put("div", div);
		User user = getSession(request);
		HttpSession session = request.getSession();
		session.setAttribute("menuOrder", menuOrder);
		session.setAttribute("li", li);
		session.setAttribute("div", div);
		session.setAttribute("type", type);
		model.put("type", type);
		
		String pageNum = request.getParameter("pageIndex");
		
		String ksName = request.getParameter("ksName");
		String ksNum = request.getParameter("ksNum");
		//String gradeDate = request.getParameter("gradeDate");
		int pageIndex = 1;
		if(null!=pageNum && !"".equals(pageNum) ){
			pageIndex = Integer.valueOf(pageNum);
		}
		PageHelper.startPage(pageIndex, 10);
		Soap cex = new Soap();
		if(null != ksName && !"".equals(ksName)){
			cex.setKsName(ksName);
		}
		if(null != ksNum && !"".equals(ksNum)){
			cex.setKsNum(ksNum);
		}
		List<Soap> cexList  = new ArrayList<Soap>();
		if("h".equals(type)){
			cex.setRoomName(user.getRoomName());
		}
		if("b".equals(type)){
			cex.setBaseName(user.getBaseName());
			
		}
		if("r".equals(type)){
			cex.setRoomName(user.getRoomName());
		}
		if("t".equals(type)){
			cex.setKgNum(user.getLoginName());
			
		}

		if("s".equals(type)){
			cex.setKsNum(user.getLoginName());
			
		}
		cexList= soapService.findAll(cex);
		PageInfo<Soap> page = new PageInfo<Soap>(cexList);
		model.put("page", page);
		
		return "/soap/index";
		
		
		
	}
	
	@RequestMapping("/soapController/soapview.htm")
	public String cexView(HttpServletRequest request,HttpServletResponse response, ModelMap model){
		String id = request.getParameter("id");
		if(null != id && !"".equals(id)){
			Soap cex =soapService.findById(Integer.valueOf(id));			
			model.put("cex", cex);
			model.put("dac", "dac");
		}
		return "/soap/soapview";
	}
	
	@RequestMapping("/soapController/toupdatesoap.htm")
	public String toUpdateCex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		String id = request.getParameter("id");
		if(null != id && !"".equals(id)){
			Soap cex = soapService.findById(Integer.valueOf(id));			
			model.put("cex", cex);
		}
		return "/soap/updatesoap";
	}
	
	///gradeController/updateCex.htm更新
	@RequestMapping("/soapController/updateSoap.htm")
	public String updateCex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String id = request.getParameter("id");
		
		//考官信息
		String kgName = request.getParameter("kgName");//考官姓名
		String kgNum = request.getParameter("kgNum");//考官工号
		String gradeDate = request.getParameter("gradeDate");//考评日期
		String gradeTime = request.getParameter("gradeTime");//时间段
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
		String fkyd = request.getParameter("fkyd");//考官对于考生的主要反馈要点
		String wcjh = request.getParameter("wcjh");//完成较好部分
		String gcTime = request.getParameter("gcTime");//观察时间
		String hkTime = request.getParameter("hkTime");//回馈时间
		
		//新增
		String aztlc = request.getParameter("aztlc");
		String bztlc = request.getParameter("bztlc");
		String zjzl = request.getParameter("zjzl");
		String ljwt = request.getParameter("ljwt");
		String wtjh = request.getParameter("wtjh");
		String jgsf = request.getParameter("jgsf");
		String apjc = request.getParameter("apjc");
		String zd = request.getParameter("zd");
		String cz = request.getParameter("cz");
		String ywzl = request.getParameter("ywzl");
		String jkjy = request.getParameter("jkjy");
		String jmsf = request.getParameter("jmsf");
		
		Soap cex = soapService.findById(Integer.valueOf(id));
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
		cex.setKhaddress(khaddress);
		cex.setKhmudi(khmudi);
		
		cex.setBlh(blh);
		cex.setBage(bage);
		cex.setBch(bch);
		cex.setBfh(bfh);
		cex.setBsex(bsex);
		cex.setComplexDegree(complexDegree);

		cex.setFkyd(fkyd);
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
		cex.setWcjh(wcjh);
		cex.setGcTime(gcTime);
		cex.setHkTime(hkTime);
		
		cex.setAztlc(aztlc);
		cex.setBztlc(bztlc);
		cex.setZjzl(zjzl);
		cex.setLjwt(ljwt);
		cex.setWtjh(wtjh);
		cex.setJgsf(jgsf);
		cex.setApjc(apjc);
		cex.setZd(zd);
		cex.setCz(cz);
		cex.setYwzl(ywzl);
		//cex.setYwzl(ywzl);
		cex.setJkjy(jkjy);
		cex.setJmsf(jmsf);
		
		List<String> listSoap = new ArrayList<String>();
		listSoap.add(mtjq);
		listSoap.add(tgjc);
		listSoap.add(zysy);
		listSoap.add(lcpd);
		listSoap.add(zxjy);
		listSoap.add(zznl);
		listSoap.add(ztlc);
		listSoap.add(aztlc);
		listSoap.add(bztlc);
		listSoap.add(zjzl);
		listSoap.add(ljwt);
		listSoap.add(wtjh);
		listSoap.add(jgsf);
		listSoap.add(apjc);
		listSoap.add(zd);
		listSoap.add(cz);
		listSoap.add(ywzl);
		listSoap.add(jkjy);
		listSoap.add(jmsf);
		int score = ScoreUtils.score(listSoap);
		cex.setScore(String.valueOf(score));
		
		try {
			soapService.updateById(cex);
			ControllerHelper.respOut(response, true);
		} catch (Exception e) {
			ControllerHelper.respOut(response, false);
		}
		
		
		return null;
	}
	
	///gradeController/delcex.htm
	@RequestMapping("/soapController/delsoap.htm")
	public String delcex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		String id = request.getParameter("id");
		if(null != id && !"".equals(id)){
			try {
				soapService.deleteById(Integer.valueOf(id));
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
