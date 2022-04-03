package com.twocow.song.mvc.repository.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {
	Integer checkUserIdExist(String userId);

}
