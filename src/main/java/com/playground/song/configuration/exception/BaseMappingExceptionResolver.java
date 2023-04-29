package com.playground.song.configuration.exception;

import com.playground.song.configuration.annotation.ApiRequestConfig;
import com.playground.song.enums.api.Api;
import com.playground.song.enums.api.ApiError;
import com.playground.song.utils.format.ResponseData;
import com.playground.song.utils.messages.MessageConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionTimedOutException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

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
		ApiRequestConfig apiRequestConfig = handlerMethod.getMethodAnnotation(ApiRequestConfig.class);

		// API에 대한 에러처리 -> 에러 내용을 담은 JSON 객체 리턴
		if (ObjectUtils.isNotEmpty(apiRequestConfig)) {
			ResponseData responseData = ResponseData.builder().apiError(ApiError.ERROR).build();

			if (ex instanceof TransactionTimedOutException || ex instanceof SQLException) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				responseData.setMessage(messageConfig.getMessage("error.transactionTimedOut.msg"));
			} else if (ex instanceof CustomValidationException) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				responseData.setMessage(messageConfig.getMessage("error.validation.missing.msg", ex.getMessage()));
			} else if (ex instanceof CustomLengthValidationException) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				responseData.setMessage(messageConfig.getMessage("error.validation.length.missing.msg", ex.getMessage(),
					((CustomLengthValidationException) ex).getMin(),
					((CustomLengthValidationException) ex).getMax()));
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				responseData.setMessage(messageConfig.getMessage("error.arithmetic.msg"));
				log.info("!!!!!API ERROR MISS MATCH!!!!!");
				log.info("ERROR TYPE : {}", ex.toString());
			}
			mav.addObject(Api.ERROR.getName(), responseData);
			mav.setViewName("jsonView");
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
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

}
