package com.twocow.song.mvc.controller;
import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.enums.Menu;
import com.twocow.song.mvc.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

	private final TestService service;

	@GetMapping({"/main", "/"})
	@RequestConfig(menu = Menu.MAIN)
	public String init(Model model) {
		model.addAttribute("obj", service.getTestConnectionList());
		return "/test/main";
	}

	@RequestMapping("/main/test")
	@RequestConfig
	public String testMain(Model model) {
		model.addAttribute("obj", service.updateTestConnectionList());
		int a = 5/ 0;
		return "/test/main";
	}
}
