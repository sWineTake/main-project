package com.twocow.song.mvc.admin.service.board;

import com.twocow.song.mvc.admin.repository.board.BoardRepository;
import com.twocow.song.mvc.admin.vo.board.Board;
import com.twocow.song.mvc.admin.vo.board.BoardPageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	public LinkedList<Board> getBoardInfo(BoardPageInfo boardPageInfo) {
		return boardRepository.getBoardInfo(boardPageInfo);
	}

}
