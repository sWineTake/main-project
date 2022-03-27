package com.twocow.song.enums;

import lombok.Getter;

@Getter
public enum Menu {
	NONE("NONE" , "미설정" , Role.R000),
	MAIN("MAIN", "메인", Role.R000),
	ERROR("ERROR", "오류", Role.R000),
	USER_LOGIN("USER_LOGIN", "로그인" , Role.R000),
	USER_FORM("USER_JOIN", "회원가입", Role.R000);

	private String name;
	private String title;
	private Role role;

	Menu(String name, String title, Role role) {
		this.name = name;
		this.title = title;
		this.role = role;
	}
}
