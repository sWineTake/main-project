package com.playground.song.enums.common;

import lombok.Getter;

@Getter
public enum CommonCode {
	BOARD("BOARD")
	;

	private String masterCode;
	CommonCode(String masterCode) {
		this.masterCode = masterCode;
	}
}
