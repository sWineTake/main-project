package com.twocow.song.mvc.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponseData<T> {
	private T result;
	public BaseResponseData(T responseData) {
		this.result = responseData;
	}

}
