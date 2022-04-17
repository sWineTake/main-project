package com.twocow.song.configuration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginValidationException extends CustomException {
	protected String goUrl;
}
