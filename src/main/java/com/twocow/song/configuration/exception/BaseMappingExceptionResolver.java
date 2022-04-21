package com.twocow.song.configuration.exception;

import com.twocow.song.configuration.annotation.ApiRequestConfig;
import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.enums.api.Api;
import com.twocow.song.enums.api.ApiError;
import com.twocow.song.utils.format.ResponseData;
import com.twocow.song.utils.messages.MessageConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionTimedOutException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
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

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		excludeConsoleWrite(ex);

		ModelAndView mav = new ModelAndView();
		HandlerMethod handlerMethod = getHandler(handler);
		RequestConfig requestConfig = handlerMethod.getMethodAnnotation(RequestConfig.class);
		ApiRequestConfig apiRequestConfig = handlerMethod.getMethodAnnotation(ApiRequestConfig.class);

		// 일반 컨트롤러에 대한 에러처리 -> 에러 얼럿 문구 후 404 화면으로 이동
		if (ObjectUtils.isNotEmpty(requestConfig)) {
			mav.setViewName("error");
			if (ex instanceof ArithmeticException) {
				setMessage(messageConfig.getMessage("error.arithmetic.msg"), response);
			} else if (ex instanceof TransactionTimedOutException) {
				// DB 트랜잭션 에러
				setMessage(messageConfig.getMessage("error.transactionTimedOut.msg"), response);
			} else if (ex instanceof LoginValidationException) {
				setMessage(messageConfig.getMessage("error.not-login.msg"), response);
				mav.addObject("goUrl", ((LoginValidationException) ex).getGoUrl());
				mav.setViewName("/user/login");
			} else {
				log.info("!!!!!ERROR MISS MATCH!!!!! --- !!!ERROR DIVISION REQUEST!!!");
				log.info("ERROR TYPE : {}", ex.toString());
			}
		}
		// API에 대한 에러처리 -> 에러 내용을 담은 JSON 객체 리턴
		else if (ObjectUtils.isNotEmpty(apiRequestConfig)) {
			// 에러코드처리
			ResponseData responseData = ResponseData.builder()
													.apiError(ApiError.ERROR).build();
			if (ex instanceof ArithmeticException) {
				responseData.setMessage(messageConfig.getMessage("error.arithmetic.msg"));
			} else if (ex instanceof TransactionTimedOutException) {
				responseData.setMessage(messageConfig.getMessage("error.transactionTimedOut.msg"));
			} else if (ex instanceof CustomValidationException) { // 커스텀 exception
				responseData.setMessage(messageConfig.getMessage("error.validation.missing.msg", ex.getMessage()));
			} else if (ex instanceof CustomLengthValidationException) {
				responseData.setMessage(messageConfig.getMessage("error.validation.length.missing.msg", ex.getMessage(),
													((CustomLengthValidationException) ex).getMin(),
													((CustomLengthValidationException) ex).getMax()));
			} else {
				responseData.setMessage(messageConfig.getMessage("error.arithmetic.msg"));
				log.info("!!!!!API ERROR MISS MATCH!!!!!");
				log.info("ERROR TYPE : {}", ex.toString());
			}
			mav.addObject(Api.ERROR.getName(), responseData);
			mav.setViewName("jsonView");
		}
		// 상위 2개 어노테이션을 담고있지않는경우 바로 에러화면으로 이동
		else {
			log.info("BaseMappingExceptionResolver -- RequestConfig No value");
			log.info("ERROR TYPE : {}", ex.toString());
			mav.setViewName("error");
		}
		return mav;
	}

	/**
	 * 커스텀 Exception은 콘솔 로그를 찍을 필요가없음으로 제외해야함
	 * @param ex
	 */
	private void excludeConsoleWrite(Exception ex) {
		if (!(ex instanceof CustomException)) {
			log.error("doResolveException {}", ex);
		}
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
