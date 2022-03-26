package com.twocow.song.mvc.controller.api;

import com.twocow.song.configuration.annotation.ApiRequestConfig;
import com.twocow.song.mvc.vo.TestParameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class TestApiController {

	@GetMapping("/test")
	@ApiRequestConfig
	public TestParameter test(@RequestBody TestParameter parameter) {
		return parameter;
	}

}
