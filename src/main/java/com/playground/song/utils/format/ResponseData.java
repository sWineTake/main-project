package com.playground.song.utils.format;

import com.playground.song.enums.api.ApiError;
import com.playground.song.utils.messages.MessageConfig;
import lombok.*;

import javax.servlet.http.HttpServletResponse;

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

	public ResponseData(boolean error, String message) {
		this.error = error;
		this.message = message;
	}

	public ResponseData(boolean error, HttpServletResponse httpServletResponse) {
		this.error = error;
		httpServletResponse.setStatus(error ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
	}

	public ResponseData(boolean error, T response, HttpServletResponse httpServletResponse) {
		this.error = error;
		this.response = response;
		httpServletResponse.setStatus(error ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
	}
}
