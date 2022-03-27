package com.twocow.song.interceptor;

import com.twocow.song.configuration.GlobalConfig;
import com.twocow.song.configuration.annotation.ApiRequestConfig;
import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.enums.Menu;
import com.twocow.song.enums.Role;
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
				// 일반 호출일경우 메뉴이름 바인딩
				request.setAttribute("menu", requestConfig.menu());
				Menu menu = requestConfig.menu();
				if (!menu.getRole().equals(Role.R000)) {

				}
			}
			else if (ObjectUtils.isNotEmpty(apiRequestConfig)) { // API 호출
				if(apiRequestConfig.login()) {
					//로그인이 필요한경우
				}
			}
			else {
				// Todo) 컨틀롤러에 맵핑된 RequestConfig가 없을경우 잘못된 요청으로 판단할것인지
				response.sendRedirect("/error");
				return false;
			}
		}
		else {
			response.sendRedirect("/error");
			return false;
		}

		return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
	}

}
