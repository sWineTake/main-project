package com.twocow.song.utils.format;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData<T> {
	private boolean error;
	private String resultCode;
	private String message;
	private T data;
}
