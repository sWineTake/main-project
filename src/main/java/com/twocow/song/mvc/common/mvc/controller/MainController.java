package com.twocow.song.mvc.common.mvc.controller;

import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.enums.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@GetMapping("/")
	@RequestConfig(menu = Menu.COMMON_MAIN)
	public String firstMain() {
		return "/common/user/login";
	}

}
