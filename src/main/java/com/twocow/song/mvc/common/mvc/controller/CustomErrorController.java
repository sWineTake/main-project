package com.twocow.song.mvc.common.mvc.controller;

import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.enums.Menu;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	@RequestConfig(menu = Menu.ERROR)
	public String errorPage() {
		return "/error";
	}

}
