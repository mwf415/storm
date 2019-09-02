package com.youyicn.controller.cycle;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.common.DateUtil;
import com.youyicn.dao.cycle.ActivesMapper;
import com.youyicn.entity.cycle.Actives;
import com.youyicn.entity.cycle.ActivesUser;
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.Room;
import com.youyicn.model.ActiveStat;
import com.youyicn.model.RoomStat;
import com.youyicn.service.cycle.ActivesService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.util.ExportExcels;
import com.youyicn.util.NumberUtils;
import com.youyicn.util.StringUtils;

/**
 * 这个事杜主任最后提出的统计功能 主要统计时长
 * 
 * @author Administrator
 *
 */
@Controller
public class CountController {

	@Autowired
	public ActivesService activesService;
	@Autowired
	public ActivesMapper activesMapper;
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;

	@RequestMapping("/countController/index.htm")
	public String index(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, String li,
			String div, String type) throws ParseException {
		Actives actives = new Actives();
		// 首先要指定查询的时间周期 都是月度，所以获取月第一天和最后一天
		String date = request.getParameter("searchStart");
		String roomName = request.getParameter("roomName");
		String baseName = request.getParameter("baseName");
		if(StringUtils.NotNull(roomName)){
			model.put("roomNamed", roomName);
		}
		if(StringUtils.NotNull(baseName)){
			model.put("baseNamed", baseName);
		}
		model.put("searchStarted", date);
		actives =getActivesFromMethod(date,type,roomName,baseName);
		
		String pageNum = request.getParameter("pageIndex");
		int pageIndex = 1;
		if (null != pageNum && !"".equals(pageNum)) {
			pageIndex = Integer.valueOf(pageNum);
		}
		PageHelper.startPage(pageIndex, 15);
		List<Actives> activesList = activesService.getActivesByForm(actives);
		PageInfo<Actives> page = new PageInfo<Actives>(activesList);
		model.put("type", type);
		model.put("page", page);
		model.put("li", li);
		model.put("div", div);
		List<Base> baseValues = baseService.queryAllBase();
		List<Room> roomValues = roomService.queryAllRoom();
		model.put("baseValues", baseValues);
		model.put("roomValues", roomValues);
		return "/count/index";
	}

	@RequestMapping("/countController/tindex.htm")
	public String tindex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, String div,
			String li,
			@RequestParam(required = false, value = "type") String type,// m
																		// 代表月度考核
																		// y代表年度考核
			@RequestParam(required = false, value = "time") String time) {
		Timestamp startTime = null;
		Timestamp endTime = null;
		Integer year = null, month = null;
		if (StringUtils.NotNull(time)) {
			year = Integer.parseInt(time.substring(0, 4));
			month = Integer.parseInt(time.substring(5, 7));
		}
		Calendar c = Calendar.getInstance();
		if (year != null) {
			c.set(Calendar.YEAR, year);
		} else {
			year = c.get(Calendar.YEAR);
		}
		if (month != null) {
			c.set(Calendar.MONTH, month - 1);
		} else {
			month = c.get(Calendar.MONTH) + 1;
		}

		if ("y".equals(type)) {
			startTime = new Timestamp(DateUtil.getStartTimeOfYear(c.getTime())
					.getTime());
			endTime = new Timestamp(DateUtil.getEndTimeOfYear(c.getTime())
					.getTime());
		} else {
			startTime = new Timestamp(DateUtil.getStartTimeOfMonth(c.getTime())
					.getTime());
			endTime = new Timestamp(DateUtil.getEndTimeOfMonth(c.getTime())
					.getTime());
		}

		List<ActiveStat> activeStats = activesMapper.getActiveStat(startTime,
				endTime);
		List<RoomStat> roomStats = activesMapper
				.getRoomStat(startTime, endTime);
		Map<String, RoomStat> m = new HashMap<String, RoomStat>();
		for (RoomStat r : roomStats) {
			m.put(r.getRoomName(), r);
		}
		for (ActiveStat a : activeStats) {
			RoomStat r = m.get(a.getRoomName());
			if (r == null)
				continue;
			switch (a.getStatus()) {
			case 4:
				r.setInRoomNum(NumberUtils.getInteger(a.getOffiNum())
						+ NumberUtils.getInteger(a.getStuNum())
						+ NumberUtils.getInteger(a.getUnOffiNum()));
				r.setInRoomTimes(a.getActiveNum());
				break;
			case 5:
				r.setOutRoomFnum(NumberUtils.getInteger(a.getFileNum()));
				break;
			case 6:
				r.setCheckRoomTimes(NumberUtils.getInteger(a.getActiveNum()));
				r.setCheckRoomNum(NumberUtils.getInteger(a.getOffiNum())
						+ NumberUtils.getInteger(a.getStuNum())
						+ NumberUtils.getInteger(a.getUnOffiNum()));
				r.setCheckRoomFnum(NumberUtils.getInteger(a.getFileNum()));
				break;
			case 7:
				r.setCaseDiscTimes((NumberUtils.getInteger(a.getActiveNum())));
				r.setCaseDiscNum(NumberUtils.getInteger(a.getOffiNum())
						+ NumberUtils.getInteger(a.getStuNum())
						+ NumberUtils.getInteger(a.getUnOffiNum()));
				r.setCaseDiscFnum(NumberUtils.getInteger(a.getFileNum()));
				break;
			case 8:
				r.setTechLectureTimes(NumberUtils.getInteger(a.getActiveNum()));
				r.setTechLectureNum(NumberUtils.getInteger(a.getOffiNum())
						+ NumberUtils.getInteger(a.getStuNum())
						+ NumberUtils.getInteger(a.getUnOffiNum()));
				r.setTechLectureFnum(NumberUtils.getInteger(a.getFileNum()));
				break;
			case 9:
				r.setPracticeTimes(NumberUtils.getInteger(a.getActiveNum()));
				r.setPracticeNum(NumberUtils.getInteger(a.getOffiNum())
						+ NumberUtils.getInteger(a.getStuNum())
						+ NumberUtils.getInteger(a.getUnOffiNum()));
				r.setPracticeFnum(NumberUtils.getInteger(a.getFileNum()));
				break;
			default:
				;
			}
		}
		model.addAttribute("roomStats", roomStats);
		model.addAttribute("type", type);
		model.addAttribute("time", time);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		return "/count/tindex";// t是total简称，type代表年度分析还是季度分析；
	}
	
	@RequestMapping("/countController/excel.htm")
	public void out(HttpServletRequest request, HttpServletResponse response,String type)	
			throws ServletException, IOException, ParseException {
		
		String date = request.getParameter("searchStart");
		
		Actives actives = new Actives();
		// 首先要指定查询的时间周期 都是月度，所以获取月第一天和最后一天
		actives =getActivesFromMethod(date,type,null,null);
		
		List<Actives> activesList = activesService.getActivesByForm(actives);
		String title="";
		switch (type) {
		case "t":
			title="老师统计";
			break;

		case "r":
			title="基地统计";
			break;

		case "b":
			title="老师统计";
			break;
		default:
			;
		}
		String fileName = "导出" + date.substring(0,7) + title+"统计.xls"; 
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename="+ fileName);// 指定下载的文件名
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		OutputStream output = response.getOutputStream();
		BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
		String worksheetTitle = fileName;
		// 定义单元格报头
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建单元格样式
		HSSFCellStyle cellStyleTitle = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		cellStyleTitle.setWrapText(true);
		// ------------------------------------------------------------------
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// ------------------------------------------------------------------
		// 设置单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyleTitle.setFont(font);
		
		String teacherName = "责任老师";
		String roomName = "科室";
		String status = "项目形式";
		String title1 = "内容";
		
		String  startTime= "开始时间";
		String takeTime = "时长";
		String userNum = "参加人数";
		String org="来源";
		HSSFSheet sheet = wb.createSheet();
		ExportExcels exportExcel = new ExportExcels(wb, sheet);
		// 创建报表头部
		exportExcel.createNormalHead(worksheetTitle, 6);
		// 定义第一行
		HSSFRow row1 = sheet.createRow(1);
		HSSFCell cell1 = row1.createCell(0);
		// 第一行第一列
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(teacherName));
		// 第一行第er列
		cell1 = row1.createCell(1);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(roomName));
		// 第一行第san列
		cell1 = row1.createCell(2);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(status));

		// 第一行第si列
		cell1 = row1.createCell(3);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(title1));

		// 第一行第wu列
		cell1 = row1.createCell(4);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(startTime));

		// 第一行第liu列
		cell1 = row1.createCell(5);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(takeTime));

		// 第一行第qi列
		cell1 = row1.createCell(6);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(userNum));
		// 第一行第qi列
		cell1 = row1.createCell(7);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(org));
	
		// 定义第二行
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell = row.createCell(1);
		Actives activesT = new Actives();
		for (int i = 0; i < activesList.size(); i++) {
			activesT = activesList.get(i);
			String teacherTitle="";
			switch (activesT.getStatus()) {
			case 4:
				teacherTitle = "入科教育";
				break;
			case 5:
				teacherTitle = "出科教育";
				break;
			case 6:
				teacherTitle = "教学查房";
				break;
			case 7:
				teacherTitle = "病历讨论";
				break;
			case 8:
				teacherTitle = "教学讲座";
				break;
			case 9:
				teacherTitle = "操作训练";
				break;
			default:
			;
			}
			row = sheet.createRow(i + 2);
			
			cell = row.createCell(0);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(activesT.getTeacherName() + ""));
			
			cell = row.createCell(1);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(activesT.getRoomName()+ ""));
			
			cell = row.createCell(2);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(teacherTitle + ""));
			
			cell = row.createCell(3);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(activesT.getTitle() + ""));
			
			
			cell = row.createCell(4);
			cell.setCellStyle(cellStyle);
			String startTimeT = activesT.getStartTime()+"";
			cell.setCellValue(new HSSFRichTextString(startTimeT.substring(0, 10)));
			
			cell = row.createCell(5);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(activesT.getTakeTime() + ""));
			
			cell = row.createCell(6);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(activesT.getUserNum()+ ""));
			
			cell = row.createCell(7);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(activesT.getOrg() + ""));
			
		}
		try {
			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output   is   closed ");
		} finally {
			activesList.clear();
		}
	
	}

	private Actives getActivesFromMethod(String date,String type,String roomName,String baseName) throws ParseException {
		Actives actives = new Actives();
		if(StringUtils.NotNull(roomName)){
			actives.setRoomName(roomName);
		}
		if(StringUtils.NotNull(baseName)){
			actives.setBaseName(baseName);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (null == date ||"".equals(date)) { // 如果为空，就显示本月的
			Calendar c = Calendar.getInstance();// 获取当前月第一天：
			c.add(Calendar.MONTH, 0);
			c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
			String first = format.format(c.getTime());
			first = first + " 01:00:00";
			System.out.println("===============first:" + first);

			Calendar ca = Calendar.getInstance(); // 获取当前月最后一天
			ca.set(Calendar.DAY_OF_MONTH,
					ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			String last = format.format(ca.getTime());
			last = last + " 23:50:00";
			System.out.println("===============last:" + last);
			actives.setSearchStart(DateUtil.str2Timestamp(first, null));
			actives.setSearchEnd(DateUtil.str2Timestamp(last, null));
		} else { // 查找date月份的第一天和最后一天，放到
			date=date+"-01";
			Date date1 = format.parse(date);
			Calendar c = new GregorianCalendar();// 获取当前月第一天：
			c.setTime(date1);
			c.add(Calendar.MONTH, 0);
			c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
			String first = format.format(c.getTime());
			first = first + " 01:00:00";
			System.out.println("===============first:" + first);

			Calendar ca =  new GregorianCalendar(); // 获取当前月最后一天
			ca.setTime(date1);
			
			ca.set(Calendar.DAY_OF_MONTH,
					ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			String last = format.format(ca.getTime());
			last = last + " 23:50:00";
			actives.setSearchStart(DateUtil.str2Timestamp(first, null));
			actives.setSearchEnd(DateUtil.str2Timestamp(last, null));
		}
		if ("b".equals(type)) {
			actives.setBaseName(baseName);
		} else if ("r".equals(type)) {
			actives.setRoomName(roomName);
		} else if ("t".equals(type)) {
			actives.setExt01("true");
		}
		return actives;
	}
	}
	
