package com.youyicn.util;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CyclePropertiesUtil {
	private static Logger log = Logger.getLogger(CyclePropertiesUtil.class);

	private static String filePath = "";
	private static CyclePropertiesUtil cycleContext = new CyclePropertiesUtil();


	private CyclePropertiesUtil() {

		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("cycle.properties");
		try {
			Properties properties = new Properties();
			properties.load(inputStream);
			filePath = properties.getProperty("file_path");
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public static CyclePropertiesUtil getInstance() {
		return cycleContext;
	}

	public final static String getFilePath(){
		return filePath;
	}



}
