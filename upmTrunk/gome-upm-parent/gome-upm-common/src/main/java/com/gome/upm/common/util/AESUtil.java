package com.gome.upm.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
/**
 * Java  实现AES加密和解密
 */
public class AESUtil{

	/**  
	 * 加密  
	 * @param content 需要加密的内容  
	 * @param password  加密密码  
	 * @return  
	 */
	public static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化   
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密   
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密  
	 * @param content  待解密内容  
	 * @param password 解密密钥  
	 * @return  
	 */
	public static byte[] decrypt(byte[] content, String password) throws Exception {
		    byte[] result = null;
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化   
			result = cipher.doFinal(content);
			return result; // 加密   
	
	}
//
//	public static String byteToString(byte[] result) {
//		String results = "";
//		try {
//			results = new String(result, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return results;
//	}

	/**
	 * String to byte[]
	 * @param hexStr
	 * @return byte[]
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * byte[] to String
	 * @param buf
	 * @return String
	 */
	  public static String parseByte2HexStr(byte buf[]) {
          StringBuffer sb = new StringBuffer();
          for (int i = 0; i < buf.length; i++) {
                  String hex = Integer.toHexString(buf[i] & 0xFF);
                  if (hex.length() == 1) {
                          hex = '0' + hex;
                  }
                  sb.append(hex.toUpperCase());
          }
          return sb.toString();
  } 
}
