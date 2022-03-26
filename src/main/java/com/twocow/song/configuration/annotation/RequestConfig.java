package com.twocow.song.configuration.annotation;

import com.twocow.song.enums.Menu;

import java.lang.annotation.*;

/**
 * RequsetConfig - PreHandler에서 사용
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestConfig {
	/*
	 * 로그인 여부
	 * */
	boolean login() default false;

	/*
	 * 현재 메뉴 정보
	 * */
	Menu menu() default Menu.NONE;
}
