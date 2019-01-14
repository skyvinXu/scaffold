package com.fengluochuni.scaffold.commons.utils;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Miscellaneous utilities for web applications.
 *
 * @author L.cm
 */
public class WebUtils extends org.springframework.web.util.WebUtils {
	/**
	 * 判断是否ajax请求
     *
	 * <p>spring ajax 返回含有 ResponseBody 或者 RestController注解</p>
     *
	 * @param handlerMethod HandlerMethod
	 * @return 是否ajax请求
	 */
	public static boolean isAjax(HandlerMethod handlerMethod) {
		ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
		if (null != responseBody) {
			return true;
		}
		// 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
		Class<?> beanType = handlerMethod.getBeanType();
		responseBody = AnnotationUtils.getAnnotation(beanType, ResponseBody.class);
		if (null != responseBody) {
			return true;
		}
		return false;
	}

    /**
     * 读取Cookie
     *
     * @param request   HttpServletRequest
     * @param name  Cookie名称
     * @return  Cookie的值
     */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		return cookie != null ? cookie.getValue() : null;
	}

	/**
	 * 清除 某个指定的Cookie
     *
	 * @param response  HttpServletResponse
	 * @param key   Cookie名称
	 */
	public static void removeCookie(HttpServletResponse response, String key) {
		setCookie(response, key, null, 0);
	}

	/**
	 * 设置Cookie
     *
	 * @param response  HttpServletResponse
	 * @param name  Cookie名称
	 * @param value Cookie的值
	 * @param maxAgeInSeconds   最大存活时间
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAgeInSeconds);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}
}
