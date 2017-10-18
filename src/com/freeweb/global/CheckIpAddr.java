package com.freeweb.global;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckIpAddr {
	private String reg = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
	Pattern pat = Pattern.compile(reg);
	
	public boolean check(String ip) {
		Matcher mat = pat.matcher(ip);
		return mat.matches();
	}
}
