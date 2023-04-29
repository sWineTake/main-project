package com.playground.song.mvc.admin.vo.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPageInfo {
	private int page=1;
	private int perPage = 10;
	private int pageCnt = 10;

}
