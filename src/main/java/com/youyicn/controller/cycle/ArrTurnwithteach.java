package com.youyicn.controller.cycle;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

import com.github.pagehelper.PageHelper;
import com.youyicn.common.DateUtil;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.Actives;
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.entity.cycle.Room;
import com.youyicn.service.cycle.ArrTurnService;
import com.youyicn.util.ExportExcels;
import com.youyicn.util.PageBean;

/**
 * 统计每个月的学生的带教老师
 * @author Administrator
 *
 */
@Controller
public class ArrTurnwithteach {

	@RequestMapping("/arrTurnwithteach/index.htm")
	public String index (String li ,String div, ModelMap model){
		model.put("li", li);
		model.put("div", div);
		return "/arrTurnwithteach/index";
	}
	
	

	@RequestMapping("/arrTurnwithteach/excel.htm")
	public void out(HttpServletRequest request, HttpServletResponse response,String type)	
			throws ServletException, IOException, ParseException {
		
		String date = request.getParameter("searchStart");
		// 首先要指定查询的时间周期 都是月度，所以获取月第一天和最后一天
		List<ArrTurn> arrturnList =getArrTurnList(date);
	
		String fileName = "导出" + date+"月带教数据.xls"; 
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
		
		HSSFSheet sheet = wb.createSheet();
		ExportExcels exportExcel = new ExportExcels(wb, sheet);
		// 创建报表头部
		exportExcel.createNormalHead(worksheetTitle, 6);
		// 定义第一行
		HSSFRow row1 = sheet.createRow(1);
		HSSFCell cell1 = row1.createCell(0);
		// 第一行第一列
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("学生编号"));
		// 第一行第er列
		cell1 = row1.createCell(1);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("学员姓名"));
		// 第一行第san列
		cell1 = row1.createCell(2);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("年级"));

		// 第一行第si列
		cell1 = row1.createCell(3);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("轮转科室"));

		// 第一行第wu列
		cell1 = row1.createCell(4);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("开始时间"));

		// 第一行第liu列
		cell1 = row1.createCell(5);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("结束时间"));

		// 第一行第qi列
		cell1 = row1.createCell(6);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("指导老师1"));
		// 第一行第qi列
		cell1 = row1.createCell(7);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString("指导老师2"));
	
		// 定义第二行
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell = row.createCell(1);
		ArrTurn arrturn = new ArrTurn();
		for (int i = 0; i < arrturnList.size(); i++) {
			arrturn = arrturnList.get(i);
			String teacherTitle="";
			
			row = sheet.createRow(i + 2);
			
			cell = row.createCell(0);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(arrturn.getHospitalId() + ""));
			
			cell = row.createCell(1);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(arrturn.getRealName()+ ""));
			
			cell = row.createCell(2);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(arrturn.getGrade() + ""));
			
			cell = row.createCell(3);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(arrturn.getRoomName() + ""));
			
			
			cell = row.createCell(4);
			cell.setCellStyle(cellStyle);
			String startTimeT = arrturn.getStartTime()+"";
			cell.setCellValue(new HSSFRichTextString(startTimeT.substring(0, 10)));
			

			cell = row.createCell(5);
			cell.setCellStyle(cellStyle);
			String end = arrturn.getEndTime()+"";
			cell.setCellValue(new HSSFRichTextString(end.substring(0, 10)));
			
			cell = row.createCell(6);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(arrturn.getTeacherName1() + ""));
			
			cell = row.createCell(7);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(arrturn.getTeacherName2()+ ""));
			
		}
		try {
			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output   is   closed ");
		} finally {
			arrturnList.clear();
		}	
	}
	

	private List<ArrTurn> getArrTurnList(String searchStart) throws ParseException {
		
		searchStart = searchStart+"-3 00:00" ;
		List<ArrTurn> arrturnList = new ArrayList<ArrTurn>();
		ArrTurn arrTurnCon = new ArrTurn();
		arrTurnCon.setSearchStart(DateUtil.str2TimestampM(searchStart));
		String searchEnd = searchStart.replaceAll("-3 00:00","-20 00:00" );
		arrTurnCon.setSearchEnd(DateUtil.str2TimestampM(searchEnd));
		
		arrturnList = arrTurnService.getThisMonthTeacherUser(arrTurnCon);
		
		
		
		return arrturnList;
		
	}
	@Autowired
	public ArrTurnService arrTurnService;
}
