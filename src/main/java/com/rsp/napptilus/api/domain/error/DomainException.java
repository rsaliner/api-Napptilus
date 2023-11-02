package com.rsp.napptilus.api.domain.error;

public class DomainException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DomainException(final String message) {
		super(message);
	}
}
