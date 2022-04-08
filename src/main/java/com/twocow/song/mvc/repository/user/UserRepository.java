package com.twocow.song.mvc.repository.user;

import com.twocow.song.mvc.vo.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {
	Integer checkUserIdExist(String userId);
	Integer checkEmailExist(String email);
	Integer insertUserInfo(User user);
}
