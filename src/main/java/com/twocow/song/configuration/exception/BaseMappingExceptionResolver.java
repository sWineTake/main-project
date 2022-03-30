package com.twocow.song.configuration.exception;

import com.twocow.song.configuration.GlobalConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * API, 일반 페이지 - 두 에러 핸들링을 동시 처리.
 * */
@RequiredArgsConstructor
@Slf4j
public class BaseMappingExceptionResolver extends SimpleMappingExceptionResolver {

	private static final String ERROR_PATH = "/error.html";
	private static GlobalConfig globalConfig;

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		log.error("doResolveException {}", ex);
		// Todo) 각 에러에 맞게 로직 구현하기

		return super.doResolveException(request, response, handler, ex);
	}

}
