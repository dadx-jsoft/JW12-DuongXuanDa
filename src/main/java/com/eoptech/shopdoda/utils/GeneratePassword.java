package com.eoptech.shopdoda.utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePassword {
	public static void main(String[] args) {
//		String result = encodePassword("user");
	    String result = encodePassword("user12345");
		System.out.println(result);
	}
	
	public static final String encodePassword(String passOrigin) {
		return new BCryptPasswordEncoder(4).encode(passOrigin);
	}
}
