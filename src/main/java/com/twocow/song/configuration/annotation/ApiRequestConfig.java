package com.twocow.song.configuration.annotation;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * ApiRequestConfig - PreHandler에서 사용
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ResponseBody
@Documented
public @interface ApiRequestConfig {
	/*
	 * 로그인 여부
	 * */
	boolean login() default false;


}
