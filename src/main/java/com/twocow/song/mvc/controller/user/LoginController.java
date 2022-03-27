package com.twocow.song.mvc.controller.user;
import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.enums.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class LoginController {

	@GetMapping("/login")
	@RequestConfig(menu = Menu.USER_LOGIN)
	public String login() {
		return "/user/login";
	}

	@GetMapping("/form")
	@RequestConfig(menu = Menu.USER_FORM)
	public String userForm() {
		return "/user/form";
	}

}
