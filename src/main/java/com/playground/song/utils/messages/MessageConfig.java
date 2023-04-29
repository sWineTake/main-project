package com.playground.song.utils.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageConfig {
	@Autowired
	MessageSource messageSource;

	public String getMessage(String code) {
		return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
	}

	public String getMessage(String code, String label) {
		return messageSource.getMessage(code, new String[]{label}, LocaleContextHolder.getLocale());
	}

	public String getMessage(String code, String label, int min, int max) {
		return messageSource.getMessage(code, new String[]{label, Integer.toString(min), Integer.toString(max)}, LocaleContextHolder.getLocale());
	}
}
