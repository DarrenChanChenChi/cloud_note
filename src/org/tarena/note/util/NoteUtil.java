package org.tarena.note.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;


public class NoteUtil {
	/**
	 * 生成Id
	 * @return
	 */
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	/**
	 * 密码加密处理
	 * @param msg 明文
	 * @return 加密之后的密文
	 */
	public static String md5(String msg){
		try {
			//还有一种SHA算法
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] input = msg.getBytes();
			byte[] output = md.digest(input);
			//将md5处理后的output结果利用Base64算法转成字符串
			String str = Base64.encodeBase64String(output);
			return str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(NoteUtil.md5("123456"));
		System.out.println(md5("a"));
		System.out.println(md5("sss"));
		System.out.println(md5("陈驰!!/opp43{}"));
	
	}
}
