package com.playground.song.configuration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomLengthValidationException extends CustomException {
	protected String message;
	protected int min;
	protected int max;
}
