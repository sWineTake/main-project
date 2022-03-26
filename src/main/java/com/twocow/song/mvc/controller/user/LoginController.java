package com.twocow.song.mvc.controller.user;
import com.twocow.song.configuration.annotation.ApiRequestConfig;
import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.enums.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class LoginController {

	@GetMapping
	@ApiRequestConfig
	public String getUser() {
		return "/test/user";
	}

	@GetMapping("/login")
	@RequestConfig(menu = Menu.USER_LOGIN)
	public String login() {
		return "/user/login/form";
	}

	@GetMapping("/join")
	@RequestConfig(menu = Menu.USER_LOGIN)
	public String getJoin() {
		return "/test/join";
	}

	@GetMapping("/joinProc")
	@ApiRequestConfig
	public String getJoinProc() {
		return "회원가입 완료됨";
	}

}
