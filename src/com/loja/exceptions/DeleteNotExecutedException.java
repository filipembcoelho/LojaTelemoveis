package com.loja.exceptions;

@SuppressWarnings("serial")
public class DeleteNotExecutedException extends RuntimeException {

	public DeleteNotExecutedException(String message) {
		super(message);
	}

}
