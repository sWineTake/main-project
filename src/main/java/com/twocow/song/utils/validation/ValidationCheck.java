package com.twocow.song.utils.validation;

import com.twocow.song.configuration.exception.CustomLengthValidationException;
import com.twocow.song.configuration.exception.CustomValidationException;
import com.twocow.song.utils.messages.MessageConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;

/**
 * 입력값 빈값 여부 확인 -> 에러발생시 Exception리졸버로 던짐
 ***/
@Component
@Slf4j
public class ValidationCheck {

	@Autowired
	MessageConfig messageConfig;

	public static Object notEmpty(Object cls, Object field, String extraLabel) {
		Object value = getValue(cls, field);
		if (StringUtils.isEmpty((String) value)) {
			error(extraLabel);
		}
		return value;
	}

	public static Object notEmpty(Object cls, Object field, int minLength, int maxLength, String extraLabel) {
		notEmpty(cls, extraLabel);
		Object value = getValue(cls, field);
		if (StringUtils.isEmpty((String) value)) {
			error(extraLabel);
		}

		int valueLength = ((String) value).length();
		if (minLength > valueLength || maxLength < valueLength) {
			throw new CustomLengthValidationException(extraLabel, minLength, maxLength);
		}

		return value;
	}

	public static void notEmpty(Object cls, int minLength, int maxLength, String extraLabel) {
		if (ObjectUtils.isEmpty(cls)) {
			error(extraLabel);
		}
		int valueLength = ((String) cls).length();
		if (minLength > valueLength || maxLength < valueLength) {
			throw new CustomLengthValidationException(extraLabel, minLength, maxLength);
		}
	}

	public static void notEmpty(Object cls, String extraLabel) {
		if (ObjectUtils.isEmpty(cls)) {
			error(extraLabel);
		}
	}

	public static Object getValue(Object cls, Object field) {
		Field target = getField(cls, field);
		if (target == null) {
			return null;
		}
		// private 필드에 접근하려면 접근을 허가해줘야한다.
		if (!target.isAccessible()) {
			target.setAccessible(true);
		}
		return ReflectionUtils.getField(target, cls);
	}

	public static Field getField(Object cls, Object field) {
		Field target = null;
		if (field instanceof Field) {
			target = ((Field) field);
		}
		else if (field instanceof String) {
			try {
				target = cls.getClass().getDeclaredField((String) field);
			} catch (Exception e) { }
		}
		return target;
	}

	private static void error(String label) {
		throw new CustomValidationException(label);
	}

}
