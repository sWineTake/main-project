package com.twocow.song.mvc.controller.api.user;

import com.twocow.song.configuration.annotation.ApiRequestConfig;
import com.twocow.song.enums.api.ApiError;
import com.twocow.song.mvc.dto.BaseResponseData;
import com.twocow.song.mvc.service.user.UserService;
import com.twocow.song.mvc.vo.ServiceResponseData;
import com.twocow.song.mvc.vo.user.User;
import com.twocow.song.utils.validation.ValidationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {
	private final UserService userService;


	@GetMapping("/{userId}")
	@ApiRequestConfig
	public BaseResponseData<String> checkUserIdExist(@PathVariable String userId) {
		ValidationCheck.notEmpty(userId,8,16,"아이디");
		return userService.checkUserIdExist(userId)
				? new BaseResponseData<>(ApiError.FAIL.name())
				: new BaseResponseData<>(ApiError.SUCCESS.name());
	}

	@GetMapping("/email/{email}")
	@ApiRequestConfig
	public BaseResponseData<ServiceResponseData> checkEmailExist(@PathVariable String email) {
		ValidationCheck.notEmpty(email,0,50,"이메일");
		return new BaseResponseData<>(userService.checkEmailExist(email));
	}


	@PostMapping
	@ApiRequestConfig
	public BaseResponseData<ServiceResponseData> insertUserInfo(@RequestBody User user) {
		ValidationCheck.notEmpty(user,"userId", 8, 16,"아이디");
		ValidationCheck.notEmpty(user,"password","패스워드");
		ValidationCheck.notEmpty(user,"email","이메일");
		return new BaseResponseData<>(userService.insertUserInfo(user));
	}
}
