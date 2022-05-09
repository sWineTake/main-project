package com.twocow.song.mvc.admin.repository.code;

import com.twocow.song.mvc.admin.vo.code.CommCode;
import com.twocow.song.mvc.admin.vo.code.CommCodePageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface CodeRepository {

	ArrayList<CommCode> getCommCode(CommCodePageInfo commCodePageInfo);
}
