package com.playground.song.configuration.interceptor;

import com.playground.song.configuration.GlobalConfig;
import com.playground.song.configuration.annotation.ApiRequestConfig;
import com.playground.song.configuration.annotation.RequestConfig;
import com.playground.song.configuration.exception.LoginValidationException;
import com.playground.song.enums.Menu;
import com.playground.song.enums.Role;
import com.playground.song.utils.session.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class BaseHandlerInterceptor implements AsyncHandlerInterceptor {

	@Autowired
	private GlobalConfig config;

	@Autowired
	private SessionUtils sessionUtils;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();
		log.info(requestURI);

		HandlerMethod handlerMethod = null;
		if (handler instanceof HandlerMethod) {
			// 파라미터로 들어온 handler에 HandlerMethod가 있을경우에만 실행
			handlerMethod = (HandlerMethod) handler;
		}

		if (handlerMethod != null) {
			RequestConfig requestConfig = handlerMethod.getMethodAnnotation(RequestConfig.class);
			ApiRequestConfig apiRequestConfig = handlerMethod.getMethodAnnotation(ApiRequestConfig.class);

			if (ObjectUtils.isNotEmpty(requestConfig)) { // 일반 호출
				// 로그인이 필요한 기능
				if (requestConfig.login()) {
					if (!sessionUtils.isLogin()) {
						request.setAttribute("menu", Menu.USER_LOGIN);
						throw new LoginValidationException(requestURI);
					}

					Menu menu = requestConfig.menu();
					if (!menu.getRole().equals(Role.R000)) {

					}
				}
				request.setAttribute("menu", requestConfig.menu());
			}
			else if (ObjectUtils.isNotEmpty(apiRequestConfig)) { // API 호출
				if (apiRequestConfig.login()) {
					if (!sessionUtils.isLogin()) {
						request.setAttribute("menu", Menu.USER_LOGIN);
						throw new LoginValidationException(requestURI);
					}
				}
			}
			else {
				// Todo) 컨틀롤러에 맵핑된 RequestConfig가 없을경우 잘못된 요청으로 판단할것인지
				response.sendRedirect("/error");
				return false;
			}
		} else {
			// 어노테이션이 없는경우
			response.sendRedirect("/error");
		}
		return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
	}
}
