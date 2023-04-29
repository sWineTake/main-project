package com.playground.song.mvc.mvc.main;

import com.playground.song.configuration.annotation.RequestConfig;
import com.playground.song.enums.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final MainService mainService;

	@GetMapping("/")
	@RequestConfig(menu = Menu.COMMON_MAIN)
	public String firstMain() {
		return "common/user/login";
	}

}
