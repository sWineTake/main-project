package com.playground.song.mvc.mvc.common.controller;

import com.playground.song.configuration.annotation.RequestConfig;
import com.playground.song.enums.Menu;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
	@RequestMapping("/error")
	@RequestConfig(menu = Menu.ERROR)
	public String errorPage(HttpServletRequest request) {
		String errorCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
		if ("403".equals(errorCode)) {
			// 권한 에러 -> 스프링 시큐리티에서 CSRF 설정된 값을 같이 보내야지 가능함
			return "error/403";
		}
		return "error/error";
	}

}
