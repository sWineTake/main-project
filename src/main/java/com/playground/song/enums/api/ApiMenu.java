package com.playground.song.enums.api;

import com.playground.song.enums.Role;
import lombok.Getter;

@Getter
public enum ApiMenu {
	NONE("NONE" , "미설정" , Role.R000);

	private String name;
	private String title;
	private Role role;

	ApiMenu(String name, String title, Role role) {
		this.name = name;
		this.title = title;
		this.role = role;
	}
}
