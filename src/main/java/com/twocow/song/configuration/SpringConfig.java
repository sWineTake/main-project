package com.twocow.song.configuration;

import com.twocow.song.configuration.exception.BaseMappingExceptionResolver;
import com.twocow.song.interceptor.BaseHandlerInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

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

	/**
	 * 메세지 소스를 생성한다.
	 */
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		// 메세지 프로퍼티파일의 위치와 이름을 지정한다..
		source.setBasename("classpath:/message/messages");
		// 기본 인코딩을 지정한다.
		source.setDefaultEncoding("UTF-8");
		// 없는 메세지일 경우 예외를 발생시키는 대신 코드를 기본 메세지로 한다.
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

	/**
	  * 에러 리졸버에서 API처리는 JSON으로 리턴하기위한 jsonView설정
	  * */
	@Bean
	MappingJackson2JsonView jsonView(){
		return new MappingJackson2JsonView();
	}

}
