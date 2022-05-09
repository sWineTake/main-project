package com.twocow.song.mvc.admin.controller.main;

import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.enums.Menu;
import com.twocow.song.enums.common.CommonCode;
import com.twocow.song.mvc.admin.service.board.BoardService;
import com.twocow.song.mvc.admin.service.code.CodeService;
import com.twocow.song.mvc.admin.vo.board.Board;
import com.twocow.song.mvc.admin.vo.board.BoardPageInfo;
import com.twocow.song.mvc.admin.vo.code.CommCode;
import com.twocow.song.mvc.admin.vo.code.CommCodePageInfo;
import com.twocow.song.mvc.common.mvc.service.CommonService;
import com.twocow.song.mvc.common.vo.Code;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminMainController {

	private final BoardService boardService;
	private final CommonService commonService;
	private final CodeService codeService;

	@GetMapping("/main")
	@RequestConfig(menu = Menu.MAIN, login = true)
	public String init() {
		return "/admin/main";
	}

	@GetMapping("/board/management")
	@RequestConfig(menu = Menu.BOARD_MANAGEMENT, login = true)
	public String boardManagement(Model model, BoardPageInfo boardPageInfo) {
		ArrayList<Board> boardInfo = boardService.getBoardInfo(boardPageInfo);
		model.addAttribute("list", boardInfo);
		return "/admin/board/management";
	}

	@GetMapping("/code/management")
	@RequestConfig(menu = Menu.COMMON_MANAGEMENT, login = true)
	public String commonManagement(Model model, CommCodePageInfo commCodePageInfo) {
		ArrayList<Code> code = commonService.getCode(CommonCode.BOARD.name());
		ArrayList<CommCode> commCode = codeService.getCommCode(commCodePageInfo);

		model.addAttribute("commCode", commCode);
		model.addAttribute("pageInfo", commCodePageInfo);
		model.addAttribute("codeList", code);
		return "/admin/code/management";
	}

}
