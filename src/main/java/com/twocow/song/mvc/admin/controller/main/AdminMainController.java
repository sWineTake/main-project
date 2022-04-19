package com.twocow.song.mvc.admin.controller.main;

import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.enums.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminMainController {
	@GetMapping("/main")
	@RequestConfig(menu = Menu.MAIN, login = true)
	public String init() {
		return "/admin/main";
	}

	@GetMapping("/board/management")
	@RequestConfig(menu = Menu.BOARD_MANAGEMENT, login = true)
	public String management() {
		return "/admin/board/management";
	}

}
