package com.twocow.song.configuration.security;

import com.twocow.song.mvc.vo.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 시큐리티 User 객체 정보 설정
 */
public class PrincipalDetails implements UserDetails {

	private User user;
	public PrincipalDetails(User user) {
		this.user = user;
	}

	// 해당 유저의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add((GrantedAuthority) () -> user.getRole());

		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 현재시간 - 로그인 시간 => 1년 초과 시 return false;
		// user.getLoginDate();
		return true;
	}
}
