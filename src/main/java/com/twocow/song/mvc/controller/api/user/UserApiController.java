package com.twocow.song.mvc.controller.api.user;

import com.twocow.song.configuration.annotation.ApiRequestConfig;
import com.twocow.song.enums.ApiError;
import com.twocow.song.mvc.vo.user.User;
import com.twocow.song.utils.format.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping
	@ApiRequestConfig
	public ResponseData<Map<String, Object>> checkUserIdExist(@RequestBody User user) {

		return null;
	}

	@PostMapping
	@ApiRequestConfig
	public ResponseData insertUserInfo(@RequestBody User user) {
		// 유저 패스워드 암호화
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		return new ResponseData(ApiError.SUCCESS.getName());
	}

}
