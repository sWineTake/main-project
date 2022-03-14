package com.twocow.song.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

	private final TestService service;

	@RequestMapping("/")
	public String init(Model model) {
		model.addAttribute("obj", service.getTestConnectionList());
		return "/main/main";
	}

}
