package com.youyicn.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 由于每次启动，都会删除已经有的文件，所以需要进行备份，同事也要生成新的静态文件
 */
public class StaticPageSchedule {
	private static final Logger logger = LoggerFactory
			.getLogger(StaticPageSchedule.class);

	private StaticPageSchedule() {
	}

	private static StaticPageSchedule single = null;

	// 静态工厂方法
	public static StaticPageSchedule getInstance() {
		if (single == null) {
			single = new StaticPageSchedule();
		}
		return single;
	}

	public void staticPage() {
		/**
		 * 暂时不需要定时任务如果需要重复，那么用while（true）
		 */
		File f = new File(getClass().getResource("/").getPath());
		String path = f.getPath();
		path = path.substring(0, path.indexOf("WEB-INF")) + "staticpage";
		String beifenPath = path.substring(0, path.indexOf("webapps"))
				+ "staticpage";
		copyFolder(beifenPath, path);

	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();

		}

	}

}
