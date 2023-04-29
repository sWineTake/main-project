package com.playground.song.enums.api;

import lombok.Getter;

@Getter
public enum Api {
	SUCCESS("000", "result"),
	ERROR("001", "error")
	;

	private String code;
	private String name;
	Api(String code, String name) {
		this.code = code;
		this.name = name;
	}
}
