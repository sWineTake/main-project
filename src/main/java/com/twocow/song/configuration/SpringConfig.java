package com.twocow.song.configuration;

import com.twocow.song.configuration.exception.BaseMappingExceptionResolver;
import com.twocow.song.interceptor.BaseHandlerInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
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
				"/sample/**",
				"/js/**",
				"/scss/**",
				"/vendor/**",
				"/favicon.ico"
		);
		registry.addInterceptor(baseHandlerInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns(excludePathPatterns);
	}

	/**
	  * 예외처리 resolver Bean
	  * */
	@Bean
	public BaseMappingExceptionResolver baseMappingExceptionResolver() {
		return new BaseMappingExceptionResolver();
	}
	/**
	  * 에러처리 리졸버 등록
	  *  */
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		resolvers.add(baseMappingExceptionResolver());
	}

}
