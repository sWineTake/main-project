package com.twocow.song.mvc.admin.controller.board;

import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.enums.Menu;
import com.twocow.song.enums.common.CommonCode;
import com.twocow.song.mvc.common.mvc.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/board/management")
@RequiredArgsConstructor
public class BoardController {

	private final CommonService commonService;

	@GetMapping("/write")
	@RequestConfig(menu = Menu.BOARD_MANAGEMENT)
	public String write(Model model) {
		model.addAttribute("code", commonService.getCode(CommonCode.BOARD.name()));

		return "/admin/board/write";
	}

}
