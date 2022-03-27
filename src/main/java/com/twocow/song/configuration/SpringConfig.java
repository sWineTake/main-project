package com.twocow.song.configuration;

import com.twocow.song.interceptor.BaseHandlerInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Configuration 관리
 * */
@Configuration
public class SpringConfig implements WebMvcConfigurer, WebMvcRegistrations {

	/**
	 * 공통 인터셉터 등록
	 */
	@Bean
	public BaseHandlerInterceptor baseHandlerInterceptor() {
		return new BaseHandlerInterceptor();
	}

	/**
	 * 인터셉터 등록 및 관리
	 * */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 기본 인터셉터에 제외될 URL
		List<String> excludePathPatterns = Arrays.asList(
				"/css/**",
				"/image/**",
				"/js/**",
				"/scss/**",
				"/vendor/**",
				"/favicon.ico"
		);
		registry.addInterceptor(baseHandlerInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns(excludePathPatterns);
	}
}
