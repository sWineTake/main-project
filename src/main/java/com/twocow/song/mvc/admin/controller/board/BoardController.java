package com.twocow.song.mvc.admin.controller.board;

import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.enums.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/board/management")
public class BoardController {

	@GetMapping("/write")
	@RequestConfig(menu = Menu.BOARD_MANAGEMENT)
	public String write() {
		return "/admin/board/write";
	}

}
