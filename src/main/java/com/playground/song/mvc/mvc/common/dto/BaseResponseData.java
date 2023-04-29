package com.playground.song.mvc.mvc.common.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponseData<T> {
	private T result;

}
