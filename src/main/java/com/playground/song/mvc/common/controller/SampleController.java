package com.playground.song.mvc.common.controller;
import com.playground.song.configuration.annotation.RequestConfig;
import com.playground.song.enums.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Sample 맵핑 컨트롤러
 * */
@Controller
@RequestConfig(menu = Menu.SAMPLE)
public class SampleController {

	@GetMapping("/sample/404")
	public String sample404() {
		return "sample/404";
	}

	@GetMapping("/sample/blank")
	public String sampleBlank() {
		return "sample/blank";
	}

	@GetMapping("/sample/buttons")
	public String sampleButtons() {
		return "sample/buttons";
	}

	@GetMapping("/sample/cards")
	public String sampleCards() {
		return "sample/cards";
	}

	@GetMapping("/sample/charts")
	public String sampleCharts() {
		return "sample/charts";
	}

	@GetMapping("/sample/forgot-password")
	public String sampleForgotPassword() {
		return "sample/forgot-password";
	}

	@GetMapping("/sample/index")
	public String sampleIndex() {
		return "sample/index";
	}

	@GetMapping("/sample/login")
	public String sampleLogin() {
		return "sample/login";
	}

	@GetMapping("/sample/register")
	public String sampleRegister() {
		return "sample/register";
	}

	@GetMapping("/sample/tables")
	public String sampleTables() {
		return "sample/tables";
	}

	@GetMapping("/sample/utilities-animation")
	public String sampleutilitiesAnimation() {
		return "sample/utilities-animation";
	}

	@GetMapping("/sample/utilitiesBorder")
	public String sampleUtilitiesBorder() {
		return "sample/utilities-border";
	}

	@GetMapping("/sample/utilitiesColor")
	public String sampleUtilitiesColor() {
		return "sample/utilities-color";
	}

	@GetMapping("/sample/utilitiesOther")
	public String sampleUtilitiesOther() {
		return "sample/utilities-other";
	}
}
