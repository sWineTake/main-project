package com.twocow.song.mvc.controller.main;

import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.enums.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping({"/main", "/"})
	@RequestConfig(menu = Menu.MAIN, login = true)
	public String init() {
		return "/main/main";
	}
}
