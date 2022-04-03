package com.twocow.song.mvc.service.user;

import com.twocow.song.mvc.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public Integer checkUserIdExist(String userId) {
		return userRepository.checkUserIdExist(userId);
	}

}
