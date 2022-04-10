package com.twocow.song.configuration.security;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	PrincipalDetailsService principalDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();

		UserDetails user = principalDetailsService.loadUserByUsername(username);
		if (ObjectUtils.isNotEmpty(user)) {
			return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}
}
