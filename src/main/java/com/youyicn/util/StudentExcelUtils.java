package com.youyicn.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.youyicn.entity.User;
import com.youyicn.service.UserService;

public class StudentExcelUtils {

	public static void main(String[] args) throws IOException {
		StudentExcelUtils xlsMain = new StudentExcelUtils();
		List<User> studentList = xlsMain.readXls("C:\\aaa.xls");
		ApplicationContext context = new ClassPathXmlApplicationContext("/context/context-main.xml");
		UserService userService = (UserService) context.getBean("userService");
		for (User stu : studentList) {
			userService.addUser(stu);
		}
		System.out.println(String.valueOf(""));
	}

	public static StudentExcelUtils getInstance() {

		return new StudentExcelUtils();
	}

	public static List<User> readXlsx(String path) {
		return new ArrayList<User>();
	}

	public static List<User> readXls(String path) throws IOException {

		InputStream is = new FileInputStream(path);

		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		List<User> userList = new ArrayList<User>();
		System.out.println(hssfSheet.getLastRowNum());
		// 循环行Row
		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			System.out.println(rowNum);
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow == null) {
				continue;
			}
			User user = new User();
			try {
				user.setRealName(getValue(hssfRow.getCell(0)));
				user.setLoginName(getValue(hssfRow.getCell(1)));
				String userPwd = getValue(hssfRow.getCell(2));
				userPwd = MD5Utils.md5(userPwd);
				user.setUserPwd(userPwd);
				user.setUserNum(getValue(hssfRow.getCell(3)));
				user.setGrade(Integer.parseInt(getValue(hssfRow.getCell(4))));
				user.setHospitalId(getValue(hssfRow.getCell(5)));
				user.setSex(getValue(hssfRow.getCell(6)));
				user.setBaseName(getValue(hssfRow.getCell(7)));
				user.setRoomName(getValue(hssfRow.getCell(8)));
				user.setGradSchool(getValue(hssfRow.getCell(9)));
				user.setCardId(getValue(hssfRow.getCell(10)));
				user.setDegree(getValue(hssfRow.getCell(11)));
				user.setXuewei(getValue(hssfRow.getCell(12)));
				user.setTrainTime(Integer.parseInt(getValue(hssfRow.getCell(13))));
				user.setCellPhone(getValue(hssfRow.getCell(14)));
				user.setEmail(getValue(hssfRow.getCell(15)));
				user.setIdentityId(Integer.parseInt(getValue(hssfRow.getCell(16))));
				user.setStatus(1);
				user.setIsAt(0);
				userList.add(user);

				// 循环列Cell
				for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
					HSSFCell hssfCell = hssfRow.getCell(cellNum);
					if (hssfCell == null) {
						continue;
					}
				}
			}catch (Exception e ){
				System.out.println("path = [" +e + "]");
			}
		}
		// }
		return userList;
	}

	@SuppressWarnings("static-access")
	public static String getValue(HSSFCell hssfCell) {
		System.out.println("cellType"+hssfCell.getCellType());
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			System.out.println(hssfCell.getBooleanCellValue());
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf((int) hssfCell.getNumericCellValue());
		} else {
			if(hssfCell.getStringCellValue() != null){
				return String.valueOf(hssfCell.getStringCellValue());
			}else{
				return String.valueOf("");
			}
		}
	}
	
}