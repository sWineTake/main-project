package com.playground.song.mvc.main;

import com.playground.song.mvc.main.dto.InsertMember;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MainRepository {
	int insertMember(InsertMember insertMember);

}
