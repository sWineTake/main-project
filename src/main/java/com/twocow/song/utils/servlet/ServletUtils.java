package com.twocow.song.utils.servlet;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ServletUtils {

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (requestAttributes == null) {
			return null;
		}
		return requestAttributes.getRequest();
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}

}
