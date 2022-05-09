package com.twocow.song.mvc.admin.controller.api.code;

import com.twocow.song.configuration.annotation.ApiRequestConfig;
import com.twocow.song.mvc.admin.vo.code.CommCode;
import com.twocow.song.mvc.common.dto.BaseResponseData;
import com.twocow.song.mvc.common.mvc.service.CommonService;
import com.twocow.song.mvc.common.vo.ServiceResponseData;
import com.twocow.song.utils.validation.ValidationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/code")
public class CodeApiController {

	private final CommonService commonService;

	@PostMapping
	@ApiRequestConfig
	public BaseResponseData<ServiceResponseData> insertCommCode(@RequestBody CommCode commCode) {
		ValidationCheck.notEmpty(commCode, "masterCode",1,10,"Master Code");
		ValidationCheck.notEmpty(commCode, "code",1,10,"Code");
		ValidationCheck.notEmpty(commCode, "title",1,10,"title");
		ValidationCheck.notEmpty(commCode, "ordNo",1,10,"ordNo");
		return new BaseResponseData<>(commonService.insertCommCode(commCode));
	}

}
