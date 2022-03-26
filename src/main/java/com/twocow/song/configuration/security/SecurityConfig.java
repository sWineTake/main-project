package com.twocow.song.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
// 스프링에서 시큐리티 사용을 가능하게함
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 크로스 도메인 여부
		http.csrf().disable();

		// 스프링 시큐리티에서 http 통신에서는 헤더값에 인증값이 필요
		// 헤더값에 인증여부 사용 여부
		http.headers().frameOptions().disable();

		http.authorizeRequests()
			.antMatchers("/user/auth/**").authenticated() // 로그인 한 사람만
			.antMatchers("/manager/**").access("hasRole('ROLE_AMDIN') or hasRole('ROLE_MANAGER')") // 로그인 & 권한
			.antMatchers("/admin/**").access("hasRole('ROLE_AMDIN')") // 로그인 & 권한
			.anyRequest().permitAll() // 위 url 제외는 모두 허용
			.and()
			.formLogin()
			.loginPage("/user/login"); // 로그인이 필요한 페이지 접근 시 미로그인시 로그인화면으로 이동됨
	}
}
