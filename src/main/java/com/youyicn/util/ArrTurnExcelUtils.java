package com.youyicn.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.youyicn.common.DateUtil;
import com.youyicn.dao.cycle.ArrTurnMapper;
import com.youyicn.entity.cycle.ArrTurn;

public class ArrTurnExcelUtils {

	public static void main(String[] args) throws IOException {
		ArrTurnExcelUtils xlsMain = new ArrTurnExcelUtils();
		List<ArrTurn> studentList = xlsMain.readXls("C:\\aaa.xls");
		ApplicationContext context = new ClassPathXmlApplicationContext("/context/context-main.xml");
		
		ArrTurnMapper userService = (ArrTurnMapper) context.getBean("userMapper");
//		for (ArrTurn stu : studentList) {
//		}
//		System.out.println(String.valueOf(""));
//		System.out.println(formatTime(stringToLong("2016/11/30")));
	}

	public static ArrTurnExcelUtils getInstance() {

		return new ArrTurnExcelUtils();
	}

	public static List<ArrTurn> readXlsx(String path) {
		return new ArrayList<ArrTurn>();
	}

	public static List<ArrTurn> readXls(String path) throws IOException {

		InputStream is = new FileInputStream(path);

		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		List<ArrTurn> atList = new ArrayList<ArrTurn>();
		// 循环行Row
		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow == null) {
				continue;
			}
			ArrTurn arrTurn = new ArrTurn();
			arrTurn.setLoginName(getValue(hssfRow.getCell(0)));
			arrTurn.setRealName(getValue(hssfRow.getCell(1)));
			String stStr = getValue(hssfRow.getCell(2));
			stStr = stStr+" 01:00:00";
			Timestamp startTime =DateUtil.str2Timestamp(stStr,null);
			arrTurn.setStartTime(startTime);
			
			arrTurn.setRoomName(getValue(hssfRow.getCell(3)));
			arrTurn.setGrade(Integer.parseInt(getValue(hssfRow.getCell(4))));
			String etStr = getValue(hssfRow.getCell(5));
			etStr = etStr +" 23:55:00";
			Timestamp endTime =DateUtil.str2Timestamp(etStr,null);
			arrTurn.setEndTime(endTime);
			arrTurn.setBasename(getValue(hssfRow.getCell(6)));
			arrTurn.setTrainTime(Integer.parseInt(getValue(hssfRow.getCell(7))));
			arrTurn.setHospitalId(getValue(hssfRow.getCell(8)));
			atList.add(arrTurn);
			// 循环列Cell
			for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
				HSSFCell hssfCell = hssfRow.getCell(cellNum);
				if (hssfCell == null) {
					continue;
				}
			}
		}
		// }
		return atList;
	}
	
	public static String formatTime(long time){
		
		SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return mat.format(time);
	}
	
	
	public static long stringToLong(String time){
		SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd");
		long res = 0;
		try {
			 res = mat.parse(time).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@SuppressWarnings("static-access")
	public static String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			String a = null;
			if(HSSFDateUtil.isCellDateFormatted(hssfCell)){
				 SimpleDateFormat sdf = null;  
	                if (hssfCell.getCellStyle().getDataFormat() == HSSFDataFormat  
	                        .getBuiltinFormat("h:mm")) {  
	                    sdf = new SimpleDateFormat("HH:mm");  
	                } else {// 日期  
	                    sdf = new SimpleDateFormat("yyyy-MM-dd");  
	                }  
	                Date date = hssfCell.getDateCellValue();  
	                a = sdf.format(date);  
			}else{
				a =  String.valueOf((int) hssfCell.getNumericCellValue());
			}
			
			return a;
		} else {
			if(hssfCell.getStringCellValue() != null){
				String a =  String.valueOf(hssfCell.getStringCellValue());
				return a;
			}else{
				return String.valueOf("");
			}
		}
	}
	
}