package cn.edu.tju.ina.estuary.util;

import java.util.Random;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordUtil {
	
	private static Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	
	public static String md5(String password) {
		return encoder.encodePassword(password, null);
	}
	
	public static String encodeWithRawpw(String password, int id, String username) {
		String pw = encoder.encodePassword(password, null);
		return encodeWithMd5pw(pw, id, username);
	}
	
	public static String encodeWithMd5pw(String password, int id, String username) {
		String pw = id + "ina" + password + username;
		String md5pw = encoder.encodePassword(pw, null);
		return md5pw;
	}
	
	public static String randomPw(int length) {
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";
	    Random random = new Random();
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < length; i++) {
	        int number = random.nextInt(base.length());
	        sb.append(base.charAt(number));
	    }
	    return sb.toString();
	}
}
