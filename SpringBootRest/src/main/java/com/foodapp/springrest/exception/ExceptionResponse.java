package com.foodapp.springrest.exception;

public class ExceptionResponse {
	private String errorMessage;
	private String requestUri;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(final String requestUri) {
		this.requestUri = requestUri;
	} 

}

