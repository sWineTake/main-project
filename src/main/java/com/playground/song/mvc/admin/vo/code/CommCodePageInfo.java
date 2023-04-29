package com.playground.song.mvc.admin.vo.code;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommCodePageInfo {
	private int page=0;
	private int perPage = 10;
	private int startPage = page * perPage;
	private String code = "";
}
