package com.twocow.song.mvc.service.user;

import com.twocow.song.mvc.repository.user.UserRepository;
import com.twocow.song.mvc.vo.ServiceResponseData;
import com.twocow.song.mvc.vo.user.User;
import com.twocow.song.utils.regular.email.EmailUtils;
import com.twocow.song.utils.regular.password.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	private final PasswordUtils passwordUtils;
	private final EmailUtils emailUtils;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public boolean checkUserIdExist(String userId) {
		return userRepository.checkUserIdExist(userId) >= 1;
	}

	public ServiceResponseData checkEmailExist(String email) {
		ServiceResponseData emailParameter = emailUtils.checkEmail(email);
		if (emailParameter.isError()) return emailParameter;

		return userRepository.checkEmailExist(email) >= 0
				? new ServiceResponseData(true)
				: new ServiceResponseData();
	}

	public ServiceResponseData insertUserInfo(User user) {
		// 패스워드 정규식 확인
		ServiceResponseData passwordParameter = passwordUtils.checkPassword(user.getPassword(), user.getUserId());
		if (passwordParameter.isError()) return passwordParameter;
		// 유저 패스워드 암호화
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		// 이메일 정규식 확인

		// 회원가입 진행

		return passwordParameter;
	}

}
