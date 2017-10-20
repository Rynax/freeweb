package com.freeweb.global;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

public class PublicCrypto {
	public static String getmd5(String s) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] digest = md5.digest(s.getBytes("utf-8"));
			String res = DatatypeConverter.printHexBinary(digest);
			if(res == null || res.isEmpty()) {
				return null;
			}
			return res;
		} catch (Exception e) {
			System.out.printf("exception: %s\n", e.toString());
			return null;
		}
	}
}
