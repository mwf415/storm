package com.youyicn.util;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.youyicn.common.DateUtil;
import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.service.cycle.ArrTurnService;

/**
 * 轮转安排导出功能；
 */

@Controller
public class ArrTurnOutExcelController {
	@Autowired
	public ArrTurnService arrTurnService;
	
	@RequestMapping("/arrTurn/outexcel.htm")
	public void out(HttpServletRequest request,  
           HttpServletResponse response) throws ServletException, IOException {  
       System.out.println("helloworld");  
       String baseName = request.getParameter("baseName");
       String gradeP =request.getParameter("grade");
       ArrTurn arrTurnCon = new ArrTurn();
       arrTurnCon.setBasename(baseName);
       arrTurnCon.setGrade(Integer.parseInt(gradeP));
       List<ArrTurn> list = arrTurnService.getArrTurnByCon(arrTurnCon);
       String fileName = "导出"+gradeP+"级"+baseName+"学员.xls";  
       fileName = new String(fileName.getBytes("GBK"), "iso8859-1");  
       response.reset();  
       response.setHeader("Content-Disposition", "attachment;filename="+ fileName);// 指定下载的文件名  
       response.setContentType("application/vnd.ms-excel");  
       response.setHeader("Pragma", "no-cache");  
       response.setHeader("Cache-Control", "no-cache");  
       response.setDateHeader("Expires", 0);  
       OutputStream output = response.getOutputStream();  
       BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);  
       // 定义单元格报头  
       String worksheetTitle = "Excel导出"+gradeP+"级Student信息";  
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
       // 工作表名  
       String basename = "基地";  
       String grade = "年级";  
       String loginName = "编号";  
       String userName = "姓名";  
       String roomName = "科室";  
       String startTimeTitle = "开始时间";  
       String endTimeTitle = "结束时间";  
       HSSFSheet sheet = wb.createSheet();  
       ExportExcels exportExcel = new ExportExcels(wb, sheet);  
       // 创建报表头部  
       exportExcel.createNormalHead(worksheetTitle, 6);  
       // 定义第一行  
       HSSFRow row1 = sheet.createRow(1);  
       HSSFCell cell1 = row1.createCell(0);  
       //第一行第一列  
       cell1.setCellStyle(cellStyleTitle);  
       cell1.setCellValue(new HSSFRichTextString(basename));  
       //第一行第er列  
       cell1 = row1.createCell(1);  
       cell1.setCellStyle(cellStyleTitle);  
       cell1.setCellValue(new HSSFRichTextString(grade));  
       //第一行第san列  
       cell1 = row1.createCell(2);  
       cell1.setCellStyle(cellStyleTitle);  
       cell1.setCellValue(new HSSFRichTextString(loginName));  
 
       //第一行第si列  
       cell1 = row1.createCell(3);  
       cell1.setCellStyle(cellStyleTitle);  
       cell1.setCellValue(new HSSFRichTextString(userName));  
 
       //第一行第wu列  
       cell1 = row1.createCell(4);  
       cell1.setCellStyle(cellStyleTitle);  
       cell1.setCellValue(new HSSFRichTextString(roomName));  
 
       //第一行第liu列  
       cell1 = row1.createCell(5);  
       cell1.setCellStyle(cellStyleTitle);  
       cell1.setCellValue(new HSSFRichTextString(startTimeTitle));  
 
       //第一行第qi列  
       cell1 = row1.createCell(6);  
       cell1.setCellStyle(cellStyleTitle);  
       cell1.setCellValue(new HSSFRichTextString(endTimeTitle));  
 
         
       //定义第二行  
       HSSFRow row = sheet.createRow(2);  
       HSSFCell cell = row.createCell(1);  
       ArrTurn arrTurn = new ArrTurn();  
       for (int i = 0; i < list.size(); i++) {  
           arrTurn = list.get(i);  
           row = sheet.createRow(i + 2);  

           cell = row.createCell(0);  
           cell.setCellStyle(cellStyle);  
           cell.setCellValue(new HSSFRichTextString(arrTurn.getBasename() + ""));  
             
           cell = row.createCell(1);  
           cell.setCellStyle(cellStyle);  
           cell.setCellValue(new HSSFRichTextString(arrTurn.getGrade() + ""));  
             
           cell = row.createCell(2);  
           cell.setCellStyle(cellStyle);  
           cell.setCellValue(new HSSFRichTextString(arrTurnCon.getLoginName()+ ""));  
             
           cell = row.createCell(3);  
           cell.setCellStyle(cellStyle);  
           cell.setCellValue(new HSSFRichTextString(arrTurn.getRealName() + ""));  
             
           cell = row.createCell(4);  
           cell.setCellStyle(cellStyle);  
           cell.setCellValue(new HSSFRichTextString(arrTurn.getRoomName()+""));  
           
           String startTimeLong = arrTurn.getStartTime()+"";
           String startTime  = DateUtil.long2short(startTimeLong);
           cell = row.createCell(5);  
           cell.setCellStyle(cellStyle);  
           cell.setCellValue(new HSSFRichTextString(startTime));  
           
           String endTimeLong =arrTurn.getEndTime()+"";
           String endTime= DateUtil.long2short(endTimeLong);
           cell = row.createCell(6);  
           cell.setCellValue(new HSSFRichTextString(endTime));  
           cell.setCellStyle(cellStyle);  
             
       }  
       try {  
           bufferedOutPut.flush();  
           wb.write(bufferedOutPut);  
           bufferedOutPut.close();  
       } catch (IOException e) {  
           e.printStackTrace();  
           System.out.println("Output   is   closed ");  
       } finally {  
           list.clear();  
       }  
   }  

}
