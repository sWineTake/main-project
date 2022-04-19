package com.twocow.song.mvc.admin.repository.user;

import com.twocow.song.mvc.common.vo.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {
	Integer checkUserIdExist(String userId);
	Integer checkEmailExist(String email);
	Integer insertUserInfo(User user);
	User getUserInfo(String userId);
	Integer updateWrongCnt(String userId);
	Integer updateWrongCntClear(String userId);
}
