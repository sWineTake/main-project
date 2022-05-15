package com.twocow.song.configuration.oauth;

import com.twocow.song.enums.Role;
import com.twocow.song.mvc.admin.repository.user.UserRepository;
import com.twocow.song.mvc.common.vo.user.User;
import com.twocow.song.utils.session.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * 구글 로그인 후 처리하는 Service
 */
@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private SessionUtils sessionUtils;
	@Autowired
	private UserRepository userRepository;

	// 구글 로그인 후 후처리 함수
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// registrationId로 어떤 Oauth로 로그인 했는지 확인이 가능
		System.out.println(userRequest.getClientRegistration());

		// 구글 로그인 순서
		// 구글 로그인 버튼 클릭 -> 구글 로그인 창 -> 로그인을 완료 -> code를 리턴(Oauth-client 라이브러리) -> AccessToken요청
		// userRequest 정보 -> 회원 프로필을 받아야함(loadUser 함수) -> 구글로부터 회원 프로필을 받아 줍니다.
		System.out.println(super.loadUser(userRequest).getAttributes());
		OAuth2User oAuth2User = super.loadUser(userRequest);

		String provider = userRequest.getClientRegistration().getClientId(); // google
		String providerId = oAuth2User.getAttribute("sub");
		String userId = provider + "_" + providerId;
		String email = oAuth2User.getAttribute("email");

		User userInfo = userRepository.getUserInfo(userId);
		if (ObjectUtils.isEmpty(userInfo)) {
			User user = User.builder()
				.userId(userId)
				.email(email)
				.role(Role.R003.name()).build();
			userRepository.insertUserInfo(user);
		}

		userInfo = userRepository.getUserInfo(userId);
		if (ObjectUtils.isNotEmpty(userInfo)) {
			sessionUtils.setUser(userInfo);
		}
		return null;
	}
}
