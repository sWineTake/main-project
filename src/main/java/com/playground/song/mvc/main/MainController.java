package com.playground.song.mvc.main;

import com.playground.song.configuration.annotation.ApiRequestConfig;
import com.playground.song.mvc.main.dto.InsertMember;
import com.playground.song.utils.format.ResponseData;
import com.playground.song.utils.validation.ValidationCheck;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
	@PostMapping("/v1/member/join")
	@Operation(summary = "회원 가입", description = "회원가입이 완료됩니다.", tags = {"MainController"})
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK",
				content = @Content(schema = @Schema(implementation = ResponseData.class))),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND"),
			@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
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
