package com.wisenc.wizex.framework.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpUtil {

	private static final String[] IP_HEADER_CANDIDATES = {
			"X-Forwarded-For",
			"Proxy-Client-IP",
			"WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR",
			"HTTP_X_FORWARDED",
			"HTTP_X_CLUSTER_CLIENT_IP",
			"HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR",
			"HTTP_FORWARDED",
			"HTTP_VIA",
			"REMOTE_ADDR"
	};

	public static String getRequestURI() {
		ServletRequestAttributes	attributes	= (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if (attributes == null) {
			return null;
		}

		HttpServletRequest	request	= attributes.getRequest();
		return request.getRequestURI();
	}

	public static String getRequestURI(HttpServletRequest req) {
		return req.getRequestURI();
	}

	public static String getClientIp() {
		ServletRequestAttributes	attributes	= (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if (attributes == null) {
			return "0.0.0.0";
		}

		HttpServletRequest	request	= attributes.getRequest();
		for (String header: IP_HEADER_CANDIDATES) {
			String	ipList	= request.getHeader(header);
			System.out.println("ip list=" + ipList);
			if (!StringUtils.isEmpty(ipList) && !"unknown".equals(ipList.toLowerCase()))
				return ipList.split(",")[0];
		}

		return request.getRemoteAddr();
	}

	public static String getClientIp(HttpServletRequest req) {
		for (String header: IP_HEADER_CANDIDATES) {
			String	ipList	= req.getHeader(header);
			if (!StringUtils.isEmpty(ipList) && !"unknown".equals(ipList.toLowerCase()))
				return ipList.split(",")[0];
		}

		return req.getRemoteAddr();
	}
}
