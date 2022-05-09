package com.twocow.song.mvc.admin.repository.board;

import com.twocow.song.mvc.admin.vo.board.Board;
import com.twocow.song.mvc.admin.vo.board.BoardPageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;

@Mapper
@Repository
public interface BoardRepository {

	ArrayList<Board> getBoardInfo(BoardPageInfo boardPageInfo);

}
