package com.twocow.song.mvc.common.controller;

import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.enums.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	@RequestConfig(menu = Menu.COMMON_MAIN)
	public String firstMain() {
		return "/common/user/login";
	}

}