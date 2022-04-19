package com.twocow.song.configuration.security;

import com.twocow.song.mvc.admin.repository.user.UserRepository;
import com.twocow.song.mvc.common.vo.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 시큐리티 설정에서 loginProcessingUrl("/login")
 * 요청이 오면 자동으로 PrincipalDetailsService 타입으로
 * IoC되어 있는 loadUserByUsername 함수가 실행
 *
 * 현재 해당클래스는 사용하지않습니다. 레디스를 사용하기위해 일반 Controller에서 로그인을 진행합니다.
 * 필요하신분은 SecurityConfig에서 loginProcessingUrl값을 수정하여 사용해주세요.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	// 시큐리티 session = Authentication(내부 UserDetails) = UserDetails
	// session(내부 Authentication(내부 UserDetails))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userInfo = userRepository.getUserInfo(username);
		if (ObjectUtils.isNotEmpty(userInfo)) {
			return new PrincipalDetails(userInfo);
		}
		return null;
	}
}
