package com.youyicn.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.youyicn.model.constants.Constants;

/**
 * @Description: 配置文件工具类
 * @author zhangxw
 * @date 2017年4月14日
 */
public class ConfigUtils {
	private final static Logger logger = LoggerFactory.getLogger(ConfigUtils.class);
	private static Map<String, String> PROPERTY;
	private static Object lock = new Object();
	private final static String FileName = "/config.properties";
	private static File file = null;
	private static long lastModif = 0; 
	static {
		String filePath = Constants.HOMEPATH+FileName;
		PROPERTY = getConfig(filePath);
		file = new File(filePath);
		lastModif = file.lastModified();
		
		logger.info("parameter file path:{}",filePath);
		
	}

	public static String getConfigPath(String fileName) {
		URL url = ConfigUtils.class.getClassLoader().getResource(fileName);
		if (url != null)
			return url.getPath();
		return null;
	}

	public static Map<String, String> getConfig(String fileName) {
		Map<String, String> map = new HashMap<String, String>();
		InputStream fis = null;
		try {
			new File(fileName);
			fis = new FileInputStream(fileName);;
			Properties properties = new Properties();
			InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
			properties.load(isr);
			for (Map.Entry<Object, Object> p : properties.entrySet()) {
				if (p.getValue().toString().length() > 0) {
					map.put(p.getKey().toString(), p.getValue().toString());
				}
			}
			return map;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			try {
				fis.close();
			} catch (Exception e2) {
			}
		}
	}

	
	public static String getProperty(String key) {
		
		 long l = file.lastModified();
		 //降低同步开销.双重检查：保证文件修改后，只进行一次重新load
		 if(l > lastModif) { 
			synchronized (lock) {
				 if(l > lastModif){
					 lastModif = l;
					 PROPERTY = getConfig(Constants.HOMEPATH+FileName);
				 }
			}
			 logger.info("config parameter values={}",PROPERTY);
		 }
		 
		if (PROPERTY.containsKey(key)) {
			return PROPERTY.get(key);
		}
		
		return "";
	}
	
	/**
	 * 获取整型参数
	 * @param key
	 * @return
	 */
	public static int getIntProperty(String key) {
		int intProperty = -1;
		try {
			intProperty = Integer.valueOf(getProperty(key));
		} catch (Exception e) {
			logger.warn("parameter [{}] not exists or config error,please check!",key);
		}
		
		return intProperty;
	}
	
}
