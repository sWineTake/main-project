package com.twocow.song.enums.api;

import lombok.Getter;

@Getter
public enum ApiError {
	SUCCESS("200", "succeed"),
	ERROR("400", "error"),
	FAIL("400", "fail")
	;

	private String code;
	private String name;
	ApiError(String code, String name) {
		this.code = code;
		this.name = name;
	}
}
