package com.twocow.song.enums;

import lombok.Getter;

@Getter
public enum Menu {
	NONE("NONE" , "미설정"),
	MAIN("MAIN", "메인"),
	USER_LOGIN("USER_LOGIN", "로그인"),
	USER_JOIN("USER_JOIN", "회원가입");

	private String name;
	private String title;

	Menu(String name, String title) {
		this.name = name;
		this.title = title;
	}
}
