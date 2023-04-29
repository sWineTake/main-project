package com.playground.song.enums;

import lombok.Getter;

@Getter
public enum Role {
	R000("고객 권한", "R000" , 0),
	R001("일반 권한", "R001" , 1),
	R002("관리자 권한", "R002", 2),
	R003("어드민 권한", "R003", 3);

	private String name;
	private String role;
	private int level;

	Role(String name, String role, int level) {
		this.name = name;
		this.role = role;
		this.level = level;
	}
}
