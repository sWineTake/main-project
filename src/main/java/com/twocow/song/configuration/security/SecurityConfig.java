package com.twocow.song.configuration.security;

import com.twocow.song.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.AuthenticationException;

@Configuration
// 스프링에서 시큐리티 사용을 가능하게함
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		return new CustomAuthenticationProvider();
	}*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 크로스 도메인 여부
		http.csrf().disable();

		// 스프링 시큐리티에서 http 통신에서는 헤더값에 인증값이 필요
		// 헤더값에 인증여부 사용 여부
		http.headers().frameOptions().disable();

		String R002 = Role.R002.getRole();
		String R003 = Role.R003.getRole();
		http.authorizeRequests()
			.antMatchers("/user/auth/**").authenticated() // 로그인 한 사람만
			.antMatchers("/manager/**").access("hasRole('" + R002 + "') or hasRole('" + R003 + "')") // 로그인 & 권한
			.antMatchers("/admin/**").access("hasRole('" + R003 + "')") // 로그인 & 권한
			.anyRequest().permitAll() // 위 url 제외는 모두 허용
			.and()
			.formLogin()
			.loginPage("/user/login") // 로그인이 필요한 페이지 접근 시 미로그인시 로그인화면으로 이동됨
			.loginProcessingUrl("/login") // /user/login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행합니다.
			//.usernameParameter("바꾸고싶은 파라미터 이름으로 변경 가능")
			.defaultSuccessUrl("/main") //로그인 성공 시 메인화면으로 진행함
			;
	}

	// 패스워드 인코드
	@Bean
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}


}
