package com.twocow.song.configuration.annotation;

import com.twocow.song.enums.Menu;

import java.lang.annotation.*;

/**
 * RequsetConfig - PreHandler에서 사용
 * 포함 여부
 * - 메뉴 이름
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestConfig {
	/*
	 * 로그인 여부
	 * */
	boolean login() default false;

	/*
	 * API 여부
	 * */
	boolean isApi() default false;

	/*
	 * 현재 메뉴 정보
	 * */
	Menu menu() default Menu.NONE;
}
