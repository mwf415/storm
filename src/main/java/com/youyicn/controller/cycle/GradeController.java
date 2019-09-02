package com.youyicn.controller.cycle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youyicn.entity.cycle.MiniCEX;
import com.youyicn.service.cycle.CEXService;
import com.youyicn.util.ControllerHelper;
import com.youyicn.util.PageBean;
import com.youyicn.util.ScoreUtils;
import com.github.pagehelper.PageHelper;

/**
 * 考评系统Mini-CEX考评表
 * @author cjf
 *
 */
@Controller
public class GradeController {
	
	private Logger logger = Logger.getLogger(GradeController.class);
	
	@Autowired
	private CEXService cexService;
	
	@RequestMapping("/gradeController/add_grade.htm")
	public String toAddGrade(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.debug("test");
		return "/grade/addgrade";
	}
	
	@RequestMapping("/gradeController/addGrade.htm")
	public String addGrade(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
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
		String isFirst = request.getParameter("isFirst");//是否第一次接触
		String complexDegree = request.getParameter("complexDegree");//病例复杂程度
		String coordination = request.getParameter("coordination");//病人配合程度
		//
		String internship = request.getParameter("internship");//实习、见习、住院
		String mtjq = request.getParameter("mtjq");//医院面谈技巧
		String f1 = request.getParameter("f1");//非观察项目1
		String tgjc = request.getParameter("tgjc");//体格检查技巧
		String f2 = request.getParameter("f2");//非观察项目2
		String zysy = request.getParameter("zysy");//人道关怀
		String f3 = request.getParameter("f3");//非观察项目3
		String lcpd = request.getParameter("lcpd");//临床判断能力
		String f4 = request.getParameter("f4");//非观察项目4
		String zxjy = request.getParameter("zxjy");//咨询建议
		String f5 = request.getParameter("f5");//非观察项目5
		String zznl = request.getParameter("zznl");//组织能力
		String f6 = request.getParameter("f6");//非观察项目6
		String ztlc = request.getParameter("ztlc");//总体临床能力
		String f7 = request.getParameter("f7");//非观察项目7
		//满意度
		String kgmy = request.getParameter("kgmy");//考官满意度
		String k1 = request.getParameter("k1");//非观察项目7
		String ksmy = request.getParameter("ksmy");//考生满意度
		String k2 = request.getParameter("k2");//非观察项目7
		//
		String fkyd = request.getParameter("fkyd");//考官对于考生的主要反馈要点
		String wcjh = request.getParameter("wcjh");//完成较好部分
		String kygj = request.getParameter("kygj");//可以改进部分
		String bzbf = request.getParameter("bzbf");//不足部分
		String ztyx = request.getParameter("ztyx");//总体印象
		String gcTime = request.getParameter("gcTime");//观察时间
		String hkTime = request.getParameter("hkTime");//回馈时间
		
		MiniCEX cex = new MiniCEX();
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
		cex.setBzbf(bzbf);
		cex.setIsFirst(isFirst);
		cex.setComplexDegree(complexDegree);
		cex.setCoordination(coordination);
		
		cex.setInternship(internship);
		cex.setF1(f1);
		cex.setF2(f2);
		cex.setF3(f3);
		cex.setF4(f4);
		cex.setF5(f5);
		cex.setF6(f6);
		cex.setF7(f7);
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
		cex.setKygj(kygj);
		cex.setZtyx(ztyx);
		cex.setGcTime(gcTime);
		cex.setHkTime(hkTime);
		//考评总分
		List<String> listMini = new ArrayList<String>();
		listMini.add(mtjq);
		listMini.add(tgjc);
		listMini.add(lcpd);
		listMini.add(zznl);
		listMini.add(zysy);
		listMini.add(ztlc);
		listMini.add(zxjy);
		int score = ScoreUtils.score(listMini);
		cex.setScore(String.valueOf(score));

		
		
		int a = cexService.insert(cex);
		
		return "redirect:/gradeController/list.htm?li=li912&div=div_9&menuOrder=3&type=r";
	}
	
	@RequestMapping("/gradeController/list.htm")
	public String cexList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,String li,String div,String type){
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		
		String pageNum = request.getParameter("pageIndex");
		String ksName = request.getParameter("ksName");
		String ksNum = request.getParameter("ksNum");
		String gradeDate = request.getParameter("gradeDate");
		int pageIndex = 1;
		if(null!=pageNum && !"".equals(pageNum) ){
			pageIndex = Integer.valueOf(pageNum);
		}
		PageHelper.startPage(pageIndex, 10);
		MiniCEX cex = new MiniCEX();
		if(null != ksName && !"".equals(ksName)){
			cex.setKsName(ksName);
		}
		if(null != ksNum && !"".equals(ksNum)){
			cex.setKsNum(ksNum);
		}
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
		
		List<MiniCEX> cexList = cexService.findAll(cex);
		PageBean<MiniCEX> page = new PageBean<MiniCEX>(cexList);
		model.put("page", page);
			return "/grade/cexresult";
	}
	
	///gradeController/cexview.htm
	@RequestMapping("/gradeController/cexview.htm")
	public String cexView(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		String id = request.getParameter("id");
		String loginUserNum = request.getParameter("loginUserNum");		
		if(null != id && !"".equals(id)){
			MiniCEX cex = cexService.findById(Integer.valueOf(id));			
			model.put("cex", cex);
			model.put("dac", "dac");
		}
		if(null != loginUserNum && !"".equals(loginUserNum)){
			MiniCEX cexcon = new MiniCEX();
			cexcon.setKgNum(loginUserNum);			
			List<MiniCEX> cexList = cexService.findAll(cexcon);
			if(cexList != null && cexList.size()>0){
				MiniCEX cexView = cexList.get(0);
				model.put("cex", cexView);
				
			}
			
		}
		return "/grade/cexview";
	}
	
	@RequestMapping("/gradeController/toupdatecex.htm")
	public String toUpdateCex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		String id = request.getParameter("id");
		if(null != id && !"".equals(id)){
			MiniCEX cex = cexService.findById(Integer.valueOf(id));			
			model.put("cex", cex);
		}
		return "/grade/updatecex";
	}
	
	///gradeController/updateCex.htm更新
	@RequestMapping("/gradeController/updateCex.htm")
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
		String isFirst = request.getParameter("isFirst");//是否第一次接触
		String complexDegree = request.getParameter("complexDegree");//病例复杂程度
		String coordination = request.getParameter("coordination");//病人配合程度
		//
		String internship = request.getParameter("internship");//实习、见习、住院
		String mtjq = request.getParameter("mtjq");//医院面谈技巧
		String f1 = request.getParameter("f1");//非观察项目1
		String tgjc = request.getParameter("tgjc");//体格检查技巧
		String f2 = request.getParameter("f2");//非观察项目2
		String zysy = request.getParameter("zysy");//人道关怀
		String f3 = request.getParameter("f3");//非观察项目3
		String lcpd = request.getParameter("lcpd");//临床判断能力
		String f4 = request.getParameter("f4");//非观察项目4
		String zxjy = request.getParameter("zxjy");//咨询建议
		String f5 = request.getParameter("f5");//非观察项目5
		String zznl = request.getParameter("zznl");//组织能力
		String f6 = request.getParameter("f6");//非观察项目6
		String ztlc = request.getParameter("ztlc");//总体临床能力
		String f7 = request.getParameter("f7");//非观察项目7
		//满意度
		String kgmy = request.getParameter("kgmy");//考官满意度
		String k1 = request.getParameter("k1");//非观察项目7
		String ksmy = request.getParameter("ksmy");//考生满意度
		String k2 = request.getParameter("k2");//非观察项目7
		//
		String fkyd = request.getParameter("fkyd");//考官对于考生的主要反馈要点
		String wcjh = request.getParameter("wcjh");//完成较好部分
		String kygj = request.getParameter("kygj");//可以改进部分
		String bzbf = request.getParameter("bzbf");//不足部分
		String ztyx = request.getParameter("ztyx");//总体印象
		String gcTime = request.getParameter("gcTime");//观察时间
		String hkTime = request.getParameter("hkTime");//回馈时间
		
		MiniCEX cex = cexService.findById(Integer.valueOf(id));
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
		cex.setBzbf(bzbf);
		cex.setIsFirst(isFirst);
		cex.setComplexDegree(complexDegree);
		cex.setCoordination(coordination);
		
		cex.setInternship(internship);
		cex.setF1(f1);
		cex.setF2(f2);
		cex.setF3(f3);
		cex.setF4(f4);
		cex.setF5(f5);
		cex.setF6(f6);
		cex.setF7(f7);
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
		cex.setKygj(kygj);
		cex.setZtyx(ztyx);
		cex.setGcTime(gcTime);
		cex.setHkTime(hkTime);
		//考评总分
		List<String> listMini = new ArrayList<String>();
		listMini.add(mtjq);
		listMini.add(tgjc);
		listMini.add(lcpd);
		listMini.add(zznl);
		listMini.add(zysy);
		listMini.add(ztlc);
		listMini.add(zxjy);
		int score = ScoreUtils.score(listMini);
		cex.setScore(String.valueOf(score));
		
		try {
			cexService.updateById(cex);
			ControllerHelper.respOut(response, true);
		} catch (Exception e) {
			ControllerHelper.respOut(response, false);
		}
		
		
		return null;
	}
	
	///gradeController/delcex.htm
	@RequestMapping("/gradeController/delcex.htm")
	public String delcex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		String id = request.getParameter("id");
		if(null != id && !"".equals(id)){
			try {
				cexService.deleteById(Integer.valueOf(id));
				ControllerHelper.respOut(response, true);
			} catch (Exception e) {
				ControllerHelper.respOut(response, false);
			}
			
		}
		return null;
	}
}
