package com.twocow.song.mvc.controller.user;

import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.enums.Menu;
import com.twocow.song.utils.session.SessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class LoginController {

	private final SessionUtils sessionUtils;

	@GetMapping("/login")
	@RequestConfig(menu = Menu.USER_LOGIN)
	public String login() {
		return "/user/login";
	}

	@GetMapping("/logout")
	@RequestConfig(menu = Menu.USER_LOGOUT)
	public String logout() {
		if (sessionUtils.isLogin()) {
			sessionUtils.delUser();
		}
		return "redirect:/user/login";
	}

	@GetMapping("/form")
	@RequestConfig(menu = Menu.USER_FORM)
	public String userForm() {
		return "/user/form";
	}

}
