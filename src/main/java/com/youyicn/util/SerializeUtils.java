/**
 * 
 */
package com.youyicn.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Description: 对象序列化工具类
 * @author zhangxw
 * @date 2017年4月14日
 */
public class SerializeUtils {
	
	/** 
     * 序列化 
     * @param object 
     * @return 
     */  
    public static byte[] serialize(Object object) {  
        ObjectOutputStream oos = null;  
        ByteArrayOutputStream baos = null;  
        try {  
            baos = new ByteArrayOutputStream();  
            oos = new ObjectOutputStream(baos);  
            oos.writeObject(object);  
            byte[] bytes = baos.toByteArray();  
            return bytes;  
        } catch (Exception e) {  
  
        }  
        return null;  
    }  
  
    /** 
     * 反序列化 
     * @param bytes 
     * @return 
     */  
    public static Object unserialize(byte[] bytes) {  
        ByteArrayInputStream bais = null;  
        try {  
            bais = new ByteArrayInputStream(bytes);  
            ObjectInputStream ois = new ObjectInputStream(bais);  
            return ois.readObject();  
        } catch (Exception e) {  
  
        }  
        return null;  
    }  
    
}
