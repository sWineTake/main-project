package com.twocow.song.enums;

import lombok.Getter;

@Getter
public enum Menu {
	NONE("NONE"),
	MAIN("MAIN");

	private String name;

	Menu(String name) {
		this.name = name;
	}
}
