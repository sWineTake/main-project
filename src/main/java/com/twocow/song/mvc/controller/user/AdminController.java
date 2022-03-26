package com.twocow.song.mvc.controller.user;

import com.twocow.song.configuration.annotation.RequestConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin")
	@RequestConfig
	public String getAdmin() {
		return "/test/admin";
	}

	@GetMapping("/manager")
	@RequestConfig
	public String getManager() {
		return "/test/manager";
	}

}
