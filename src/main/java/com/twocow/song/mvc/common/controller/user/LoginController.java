package com.twocow.song.mvc.common.controller.user;

import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.enums.Menu;
import com.twocow.song.utils.session.SessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 일반 사용자 로그인 & 어드민 사용자 로그인 동시 관리
 */
@Controller
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class LoginController {

	private final SessionUtils sessionUtils;

	@GetMapping("/login")
	@RequestConfig(menu = Menu.USER_LOGIN)
	public String login() {
		if (sessionUtils.isLogin()) return "redirect:/main";
		return "/common/user/login";
	}

	@GetMapping("/logout")
	@RequestConfig(menu = Menu.USER_LOGOUT)
	public String logout() {
		if (sessionUtils.isLogin()) {
			sessionUtils.delUser();
		}
		return "redirect:/common/user/login";
	}

	@GetMapping("/form")
	@RequestConfig(menu = Menu.USER_FORM)
	public String userForm() {
		return "/common/user/form";
	}

}
