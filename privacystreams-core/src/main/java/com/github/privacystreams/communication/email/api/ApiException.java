package com.github.privacystreams.communication.email.api;

public class ApiException extends RuntimeException {
	private int code = 0;
	private String requestId;

	public ApiException(String message, int code) {
		super(message);
		this.code = code;
	}

	public ApiException(String message, int code, String requestId) {
		super(message);
		this.code = code;
		this.requestId = requestId;
	}

	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getCode() {
		return code;
	}

	public String getRequestId() {
		return requestId;
	}
}
