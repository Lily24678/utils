package com.lsy.code.utils;

import java.security.MessageDigest;

import com.thoughtworks.xstream.core.util.Base64Encoder;

/**
 * 加解密工具
 */
public class EncryDecryUtils {

	public static String getMD5(String str) {
		String newstr="";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			Base64Encoder base64en = new Base64Encoder();
			//加密后的字符串
		    newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newstr;
	}
	
}