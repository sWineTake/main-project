package com.twocow.song.enums;

import lombok.Getter;

@Getter
public enum Menu {
	NONE("NONE" , "미설정" , Role.R000, false),
	SAMPLE("SAMPLE" , "샘플" , Role.R000, false),
	MAIN("MAIN", "메인", Role.R000, true),
	ERROR("ERROR", "오류", Role.R000, false),
	USER_LOGIN("USER_LOGIN", "로그인" , Role.R000, false),
	USER_LOGOUT("USER_LOGOUT", "로그아웃" , Role.R000, false),
	USER_FORM("USER_JOIN", "회원가입", Role.R000, false);

	private String name;
	private String title;
	private Role role;
	private boolean isLnb;

	Menu(String name, String title, Role role, boolean isLnb) {
		this.name = name;
		this.title = title;
		this.role = role;
		this.isLnb = isLnb;
	}
}
