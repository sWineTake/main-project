package com.twocow.song.interceptor;

import com.twocow.song.configuration.GlobalConfig;
import com.twocow.song.configuration.annotation.ApiRequestConfig;
import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.configuration.exception.LoginValidationException;
import com.twocow.song.enums.Menu;
import com.twocow.song.enums.Role;
import com.twocow.song.utils.session.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class BaseHandlerInterceptor implements AsyncHandlerInterceptor {

	@Autowired
	private GlobalConfig config;

	@Autowired
	private SessionUtils sessionUtils;

	@Value("${cors.permission.ip}")
	private String permissionIpAddr;

	@Value("${cors.permission.ip2}")
	private String permissionIpAddr2;

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
		}
		else {
			// 어노테이션이 없는경우 외부에서 호출한 IP가 허가 아이피인지 확인
			List<String> permissionIpList = Arrays.asList(permissionIpAddr, permissionIpAddr2);
			if (!permissionIpList.stream().anyMatch(addr -> addr.equals(request.getRemoteAddr()))) {
				response.sendRedirect("/error");
				return false;
			}
		}
		return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
	}
}
