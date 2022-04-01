package com.twocow.song.configuration.exception;

import com.twocow.song.configuration.GlobalConfig;
import com.twocow.song.configuration.annotation.ApiRequestConfig;
import com.twocow.song.configuration.annotation.RequestConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionTimedOutException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import com.twocow.song.configuration.message.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * API, 일반 페이지 - 두 에러 핸들링을 동시 처리.
 * */
@Slf4j
public class BaseMappingExceptionResolver extends SimpleMappingExceptionResolver {

	@Autowired
	MessageConfig messageConfig;
	private static final String ERROR_PATH = "/error.html";
	private static GlobalConfig globalConfig;

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		log.error("doResolveException {}", ex);
		ModelAndView mav = new ModelAndView();
		HandlerMethod handlerMethod = getHandler(handler);
		RequestConfig requestConfig = handlerMethod.getMethodAnnotation(RequestConfig.class);
		ApiRequestConfig apiRequestConfig = handlerMethod.getMethodAnnotation(ApiRequestConfig.class);

		if (ObjectUtils.isNotEmpty(requestConfig)) {
			if (ex instanceof ArithmeticException) {
				setMessage(messageConfig.getMessage("error.arithmetic.msg"), response);
			} else if (ex instanceof TransactionTimedOutException) {
				// DB 트랜잭션 에러
				setMessage(messageConfig.getMessage("error.transactionTimedOut.msg"), response);
			} else {
				log.info("!!!!!ERROR MISS MATCH!!!!! --- !!!ERROR DIVISION REQUEST!!!");
				log.info("ERROR TYPE : {}", ex.toString());
			}
			mav.setViewName("error");
		}
		else if (ObjectUtils.isNotEmpty(apiRequestConfig)) {

		}
		else {
			log.info("BaseMappingExceptionResolver -- RequestConfig No value");
			log.info("ERROR TYPE : {}", ex.toString());
			mav.setViewName("error");
		}
		return mav;
	}

	private HandlerMethod getHandler(Object handler) {
		HandlerMethod handlerMethod = null;
		if (handler instanceof HandlerMethod) {
			handlerMethod = (HandlerMethod) handler;
		}
		return handlerMethod;
	}

	private void setMessage(String msg, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('" + msg + "');</script>");
			writer.flush();
		}
		catch (Exception ex) {
		}
	}
}
