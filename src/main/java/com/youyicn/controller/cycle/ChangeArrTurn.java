package com.youyicn.controller.cycle;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.Room;
import com.youyicn.service.cycle.ArrTurnService;
import com.youyicn.service.cycle.BaseService;
import com.youyicn.service.cycle.RoomService;
import com.youyicn.util.ExportExcels;
import com.youyicn.util.TimeUtils;

/**
 * 换科查询
 */
@Controller
public class ChangeArrTurn {
	@Autowired
	public ArrTurnService arrTurnService;
	@Autowired
	public RoomService roomService;
	@Autowired
	public BaseService baseService;
	@RequestMapping("/changeArrTurn/index.htm")
	public String index(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,String type,String li,String div ) {
		model.put("li", li);
		model.put("div", div);
		model.put("type", type);
		List<Base> baseValues = baseService.queryAllBase();
		model.put("baseValues", baseValues);

		List<Room> roomValues = roomService.queryAllRoom();
		model.put("roomValues", roomValues);
		
		return "/change/index";
	}
	
	

	// 页面显示
	@RequestMapping("/changeArrTurn/detail.htm")
	public String detail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,String type) {
		String baseName = request.getParameter("baseName");
		String roomName = request.getParameter("roomName");
		String time = request.getParameter("searchStart");
		model.put("type", type);
		ArrTurn arrTurnCon = new ArrTurn();
		if ("" != baseName && null != baseName) {
			arrTurnCon.setBasename(baseName);
		}
		if ("" != roomName && null != roomName) {
			arrTurnCon.setRoomName(roomName);
		}
		
		arrTurnCon.seteTime(time.substring(0, 7));
		model.put("time", time.substring(0,7));
		List<ArrTurn> arrTurnList = arrTurnService.getChange(arrTurnCon);//结束月份
		ArrTurn arrTurnCon1 = new ArrTurn();
		arrTurnCon1.setsTime((TimeUtils.addMonth(time, "1", "yyyy-MM-dd")).substring(0, 7));
		List<ArrTurn> arrTurnList1 = arrTurnService.getChange(arrTurnCon1);//下一月
		if("y".equals(type) || "y"== type){
			for (ArrTurn arrTurn : arrTurnList) {
				for (ArrTurn arrTurn1 : arrTurnList1) {
					if ( (arrTurn.getLoginName()).equals(arrTurn1.getLoginName()) ) {
						arrTurn.setsTime(time.substring(0, 7));
						arrTurn.setNextRoomName(arrTurn1.getRoomName());
					}
				}
			}
			model.put("arrTurnList", arrTurnList);
			return "/change/detail";
		}
		if("n".equals(type) || "n"==type){
			for (ArrTurn arrTurn : arrTurnList) {
				for (ArrTurn arrTurn1 : arrTurnList1) {
					if (arrTurnCon.getLoginName().equals(arrTurn1.getLoginName())) {
						arrTurn.setsTime(time.substring(0, 7));
						arrTurn.setNextRoomName(arrTurn1.getRoomName());
					}
				}
			}
			//arrTrunList 是所有换科的人员
			
			List<ArrTurn> listTemp = arrTurnService.getChangeNo(arrTurnCon);//获取所有该月有轮转的学员
			List<ArrTurn> arrTurnListNo = new ArrayList<ArrTurn>();
			arrTurnListNo.addAll(listTemp);
			for (ArrTurn changeNo : listTemp) {
				for (ArrTurn change : arrTurnList) {
					if(change.getLoginName()==changeNo.getLoginName()|| change.getLoginName().equals(changeNo.getLoginName())){
						arrTurnListNo.remove(change);
					}
				}
			}
			model.put("smonth", time.substring(0, 7));
			model.put("arrTurnListNo", arrTurnListNo);
			return "/change/detailno";
		}
		return null;
	}
	
	// 换科数据导出
	@RequestMapping("/changeArrTurn/exportexcel.htm")
	public void out(HttpServletRequest request, HttpServletResponse response,String type)	throws ServletException, IOException {
		
		String time = request.getParameter("searchStart");
		String d= TimeUtils.addMonth(time, "1", "yyyy-MM-dd").substring(0, 7);//为了获取时间
		if("y".equals(type) || "y"== type){
			d =d+"月份换科";
		}else{
			d =d+"月份不换科";
			
		}
		ArrTurn arrTurnCon = new ArrTurn();
		arrTurnCon.seteTime(time.substring(0, 7));
		List<ArrTurn> arrTurnList = arrTurnService.getChange(arrTurnCon);
		ArrTurn arrTurnCon1 = new ArrTurn();
		arrTurnCon1.setsTime((TimeUtils.addMonth(time, "1", "yyyy-MM-dd")).substring(0, 7));
		List<ArrTurn> arrTurnList1 = arrTurnService.getChange(arrTurnCon1);
		for (ArrTurn arrTurn : arrTurnList) {
			for (ArrTurn arrTurn1 : arrTurnList1) {
				if (arrTurn.getLoginName().equals(arrTurn1.getLoginName())) {
					arrTurn.setsTime(time.substring(0, 7));
					arrTurn.setNextRoomName(arrTurn1.getRoomName());
				}
			}
		}
		
		String fileName = "导出级" + d + "学员名单.xls"; 
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename="+ fileName);// 指定下载的文件名
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		OutputStream output = response.getOutputStream();
		BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
		String worksheetTitle = "Excel导出" +d+ "学员名单";
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
		String sTime = "月份";
		String loginName = "编号";
		String userName = "姓名";
		String grade = "年级";
		
		String  basename= "专业基地";
		String roomName = "本月科室";
		String teacher = "带教老师";
		String hospitalId="工号";
		String nextRoom = "下月科室";
		HSSFSheet sheet = wb.createSheet();
		ExportExcels exportExcel = new ExportExcels(wb, sheet);
		// 创建报表头部
		exportExcel.createNormalHead(worksheetTitle, 6);
		// 定义第一行
		HSSFRow row1 = sheet.createRow(1);
		HSSFCell cell1 = row1.createCell(0);
		// 第一行第一列
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(sTime));
		// 第一行第er列
		cell1 = row1.createCell(1);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(loginName));
		// 第一行第san列
		cell1 = row1.createCell(2);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(userName));

		// 第一行第si列
		cell1 = row1.createCell(3);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(grade));

		// 第一行第wu列
		cell1 = row1.createCell(4);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(basename));

		// 第一行第liu列
		cell1 = row1.createCell(5);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(roomName));

		// 第一行第qi列
		cell1 = row1.createCell(6);
		cell1.setCellStyle(cellStyleTitle);
		cell1.setCellValue(new HSSFRichTextString(teacher));
	
		// 定义第二行
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell = row.createCell(1);
		ArrTurn arrTurn = new ArrTurn();
		
		
		
		if("y".equals(type) || "y"== type){  //换科
			cell1 = row1.createCell(7);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString(nextRoom));
			
			cell1 = row1.createCell(8);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString("开始时间"));
			
			cell1 = row1.createCell(9);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString("结束时间"));
			
			cell1 = row1.createCell(10);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString(hospitalId));
			
	
			for (int i = 0; i < arrTurnList.size(); i++) {
				arrTurn = arrTurnList.get(i);
				row = sheet.createRow(i + 2);
				
				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurn.getsTime() + ""));
				
				cell = row.createCell(1);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurn.getHospitalId()+ ""));
				
				cell = row.createCell(2);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurn.getRealName() + ""));
				
				cell = row.createCell(3);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurn.getGrade() + ""));
				
				cell = row.createCell(4);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurn.getBasename() + ""));
				
				cell = row.createCell(5);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurn.getRoomName() + ""));
				
				cell = row.createCell(6);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurn.getTeacherName1()+ "," + arrTurn.getTeacherName2()+""));
				
				cell = row.createCell(7);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurn.getNextRoomName() + ""));
				
				cell = row.createCell(8);
				cell.setCellStyle(cellStyle);
				String startTime = arrTurn.getStartTime()+"";
				System.out.println(startTime.substring(0,10));
				cell.setCellValue(new HSSFRichTextString(startTime.substring(0, 10)));
				
				cell = row.createCell(9);
				cell.setCellStyle(cellStyle);
				String endTime = arrTurn.getEndTime()+"";
				System.out.println(endTime.substring(0, 10));
				cell.setCellValue(new HSSFRichTextString(endTime.substring(0, 10)));
				
				cell = row.createCell(10);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurn.getHospitalId() + ""));
				
				
			}
			try {
				bufferedOutPut.flush();
				wb.write(bufferedOutPut);
				bufferedOutPut.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Output   is   closed ");
			} finally {
				arrTurnList.clear();
			}
		}else{
			List<ArrTurn> listTemp = arrTurnService.getChangeNo(arrTurnCon);//获取所有该月有轮转的学员
			List<ArrTurn> arrTurnListNo = new ArrayList<ArrTurn>();
			arrTurnListNo.addAll(listTemp);
			for (ArrTurn changeNo : listTemp) {
				for (ArrTurn change : arrTurnList) {
					if(change.getLoginName()==changeNo.getLoginName()|| change.getLoginName().equals(changeNo.getLoginName())){
						arrTurnListNo.remove(change);
					}
				}
			}
	
			cell1 = row1.createCell(7);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString("开始时间"));
			
			cell1 = row1.createCell(8);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString("结束时间"));

			cell1 = row1.createCell(9);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString(hospitalId));
			for (int i = 0; i < arrTurnListNo.size(); i++) {
				arrTurn = arrTurnListNo.get(i);
				row = sheet.createRow(i + 2);

				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(d));

				cell = row.createCell(1);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurnCon.getLoginName()+ ""));

				cell = row.createCell(2);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurn.getRealName() + ""));

				cell = row.createCell(3);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurn.getGrade() + ""));

				cell = row.createCell(4);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurn.getBasename() + ""));
				
				cell = row.createCell(5);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurn.getRoomName() + ""));
			
				cell = row.createCell(6);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurn.getTeacherName1()+ "," + arrTurn.getTeacherName2()+""));
				
				cell = row.createCell(7);
				cell.setCellStyle(cellStyle);
				String startTime = arrTurn.getStartTime()+"";
				System.out.println(startTime.substring(0,10));
				cell.setCellValue(new HSSFRichTextString(startTime.substring(0, 10)));
				
				cell = row.createCell(8);
				cell.setCellStyle(cellStyle);
				String endTime = arrTurn.getEndTime()+"";
				System.out.println(endTime.substring(0, 10));
				cell.setCellValue(new HSSFRichTextString(endTime.substring(0, 10)));
				

				cell = row.createCell(9);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(arrTurn.getHospitalId() + ""));
			
			}
			try {
				bufferedOutPut.flush();
				wb.write(bufferedOutPut);
				bufferedOutPut.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Output   is   closed ");
			} finally {
				arrTurnList.clear();
			}
			
			
		}
	}

}
