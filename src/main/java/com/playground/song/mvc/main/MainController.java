package com.playground.song.mvc.main;

import com.playground.song.configuration.annotation.ApiRequestConfig;
import com.playground.song.mvc.main.dto.InsertMember;
import com.playground.song.utils.format.ResponseData;
import com.playground.song.utils.validation.ValidationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final MainService mainService;

	@ApiRequestConfig
	@PostMapping("/member/join")
	public ResponseData<Boolean> memberInsert(
			@RequestBody InsertMember insertMember, HttpServletResponse response) {
		ValidationCheck.notEmpty(insertMember, "snsId", "회원 아이디");
		ValidationCheck.notEmpty(insertMember, "city", "도시");
		ValidationCheck.notEmpty(insertMember, "name", "이름");
		ValidationCheck.notEmpty(insertMember, "street", "주소");
		ValidationCheck.notEmpty(insertMember, "zipcode", "상세주소");

		return new ResponseData<>(mainService.insertMember(insertMember), response);
	}

}
