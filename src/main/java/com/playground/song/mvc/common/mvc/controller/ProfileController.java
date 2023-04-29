package com.playground.song.mvc.common.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/profile")
public class ProfileController {

	// 이력서 URL
	@GetMapping("/lee_woo_song")
	public String getProfileTwocowsong() {
		return "pdf/twocowsong.pdf";
	}

}
