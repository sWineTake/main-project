package com.twocow.song.mvc.controller;
import com.twocow.song.configuration.annotation.RequestConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Sample 맵핑 컨트롤러
 * */
@Controller
public class SampleController {

	@GetMapping("/sample/404")
	@RequestConfig
	public String sample404() {
		return "/sample/404";
	}

	@GetMapping("/sample/blank")
	@RequestConfig
	public String sampleBlank() {
		return "/sample/blank";
	}

	@GetMapping("/sample/buttons")
	@RequestConfig
	public String sampleButtons() {
		return "/sample/buttons";
	}

	@GetMapping("/sample/cards")
	@RequestConfig
	public String sampleCards() {
		return "/sample/cards";
	}

	@GetMapping("/sample/charts")
	@RequestConfig
	public String sampleCharts() {
		return "/sample/charts";
	}

	@GetMapping("/sample/forgot-password")
	@RequestConfig
	public String sampleForgotPassword() {
		return "/sample/forgot-password";
	}

	@GetMapping("/sample/index")
	@RequestConfig
	public String sampleIndex() {
		return "/sample/index";
	}

	@GetMapping("/sample/login")
	@RequestConfig
	public String sampleLogin() {
		return "/sample/login";
	}

	@GetMapping("/sample/register")
	@RequestConfig
	public String sampleRegister() {
		return "/sample/register";
	}

	@GetMapping("/sample/tables")
	@RequestConfig
	public String sampleTables() {
		return "/sample/tables";
	}

	@GetMapping("/sample/utilities-animation")
	@RequestConfig
	public String sampleutilitiesAnimation() {
		return "/sample/utilities-animation";
	}

	@GetMapping("/sample/utilitiesBorder")
	@RequestConfig
	public String sampleUtilitiesBorder() {
		return "/sample/utilities-border";
	}

	@GetMapping("/sample/utilitiesColor")
	@RequestConfig
	public String sampleUtilitiesColor() {
		return "/sample/utilities-color";
	}

	@GetMapping("/sample/utilitiesOther")
	@RequestConfig
	public String sampleUtilitiesOther() {
		return "/sample/utilities-other";
	}
}
