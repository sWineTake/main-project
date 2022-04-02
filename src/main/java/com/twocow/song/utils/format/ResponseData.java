package com.twocow.song.utils.format;

import com.twocow.song.enums.api.ApiError;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseData<T> {
	private boolean error = false;
	private ApiError apiError;
	private String resultCode = ApiError.SUCCESS.getCode();
	private String message;
	private T response;

	public ResponseData(String message) {
		this.message = message;
	}
}
