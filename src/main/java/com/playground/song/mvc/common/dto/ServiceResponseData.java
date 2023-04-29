package com.playground.song.mvc.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponseData {
	private boolean error = false;
	private String message;

	public ServiceResponseData(boolean error) {
		this.error = error;
	}

	public ServiceResponseData(String message) {
		this.message = message;
	}
}
