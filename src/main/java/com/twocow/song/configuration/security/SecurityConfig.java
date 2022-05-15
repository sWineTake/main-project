package com.twocow.song.configuration.security;

import com.twocow.song.configuration.oauth.PrincipalOauth2UserService;
import com.twocow.song.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * 스프링 시큐리티를 사용은 하지만 url 처리는 BaseHandlerInterceptor에서 작동합니다.
 * 로그인및 필요권한은 어노테이션, Enum으로 처리합니다.
 * **/
@Configuration
// 스프링에서 시큐리티 사용을 가능하게함
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 일반 시큐리티 사용
		// String R002 = Role.R002.getRole();
		// String R003 = Role.R003.getRole();
		// http.authorizeRequests()
		// .antMatchers("/user/auth/**").authenticated() // 로그인 한 사람만
		// .antMatchers("/manager/**").access("hasRole('" + R002 + "') or hasRole('" + R003 + "')") // 로그인 & 권한
		// .antMatchers("/admin/**").access("hasRole('" + R003 + "')") // 로그인 & 권한
		// .anyRequest().permitAll() // 위 url 제외는 모두 허용
		// .and()
		// .formLogin()
		// .loginPage("/user/login") // 로그인이 필요한 페이지 접근 시 미로그인시 로그인화면으로 이동됨 -> preHandler에서 어노테이션으로 작동함
		// .loginProcessingUrl("/login") // /user/login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행합니다.
		// .usernameParameter("바꾸고싶은 아이디 파라미터 이름으로 변경 가능")
		// .defaultSuccessUrl("/main") //로그인 성공 시 메인화면으로 진행함
		// ;

		// Oauth2.0 시큐리티 로그인 사용
		// 스프링 시큐리티에서 http 통신에서는 헤더값에 인증값이 필요
		// 헤더값에 인증여부 사용 여부
		/*http.csrf()
			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.and()
			.oauth2Login()
			.loginPage("/user/login") // 구글 로그인이 완료된 후의 후처리가 필요함
			// 구글 로그인이 완료된 후의 후처리가 필요함 - 직접만든 서비스를 등록
			.userInfoEndpoint()
			.userService(principalOauth2UserService)
			;*/

		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	// 패스워드 인코드
	@Bean
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}
}
