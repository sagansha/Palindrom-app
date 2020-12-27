package com.nex.palindrom.exceptions;

public class PalindromeException {
	private String errorMessage;

	public PalindromeException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
