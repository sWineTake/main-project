package com.twocow.song.mvc.common.mvc.repository;

import com.twocow.song.mvc.common.vo.Code;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface CommonRepository {
	ArrayList<Code> getCode(String masterCode);
}
