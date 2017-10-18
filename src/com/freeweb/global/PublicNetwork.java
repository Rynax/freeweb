package com.freeweb.global;

import javax.servlet.http.HttpServletRequest;

public class PublicNetwork {
	public String checkHttpIpAddr(String ip) {
		if(ip == null || ip.length() == 0) {
	    	return null;
	    }
		
		String ipa[] = ip.split(", ");
		if(ipa == null || ipa.length == 0) {
	    	return null;
	    }
		
		CheckIpAddr ipchk = new CheckIpAddr();
		for(String i : ipa) {
			if(ipchk.check(i)) {
				return i;
			}
	    }
		return null;
	}
	
	public String getIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("x-forwarded-for");
	    if(checkHttpIpAddr(ip) == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	        if(checkHttpIpAddr(ip) == null) {
		        ip = request.getHeader("WL-Proxy-Client-IP");
		        if(checkHttpIpAddr(ip) == null) {
			        ip = request.getRemoteAddr();
			    }
		    }
	    }
	    return ip;
	}
}
