package com.twocow.song.mvc.controller.api;

import com.twocow.song.configuration.annotation.RequestConfig;
import com.twocow.song.mvc.vo.TestParameter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestApiController {

	@GetMapping("/test")
	@RequestConfig(api = true)
	public TestParameter test(@RequestBody TestParameter parameter) {
		return parameter;
	}

}
