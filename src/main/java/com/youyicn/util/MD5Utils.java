package com.youyicn.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Utils {

	private static String algorithmName = "md5";
	private int hashIterations = 2;
	/**
	 * discription:统一加密方式
	 * @param msg
	 * @return
	 */
	public static String md5(String msg){
//		try
//		{
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			byte[] output = md.digest(msg.getBytes());
//			return Base64.encodeBase64String(output);
//		}
//		catch (NoSuchAlgorithmException e) {}
//		return null;


		return new SimpleHash(algorithmName, msg).toHex();
	}

	@Deprecated
	public static String md5Deprecated(String msg){
		try {
			MessageDigest md = 	MessageDigest.getInstance("MD5");
			byte[] output = md.digest(msg.getBytes());
			System.out.println(output.toString());
			return Base64.encodeBase64String(output);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static void main(String[] args) {

		String s = MD5Utils.md5("cyle123");

		System.out.println(s);
	}

	public static String md6(String msg){
		return new SimpleHash(algorithmName, msg).toHex();
	}


}
