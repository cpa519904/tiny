package org.tinygroup.bizframedslenterprise.impl.util;

import javax.servlet.http.HttpServletRequest;

public class IpAddressUtil {
	/**
	 * 获取客户端ip地址
	 * @param request
	 * @return
	 */
/*	private String getClinetIp(HttpServletRequest request){
		if (request.getHeader("x-forwarded-for") == null) {  
	        return request.getRemoteAddr();  
	    } 
	    return request.getHeader("x-forwarded-for");  
	}*/
	
	public static String getClientIp(HttpServletRequest request){
		{
			String ip = request.getHeader("x-forwarded-for"); 
		       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		           ip = request.getHeader("Proxy-Client-IP"); 
		       } 
		       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		           ip = request.getHeader("WL-Proxy-Client-IP"); 
		       } 
		       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		           ip = request.getRemoteAddr(); 
		       } 
		       return ip; 
		}
	}
}
