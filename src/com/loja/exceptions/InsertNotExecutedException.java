package com.loja.exceptions;

@SuppressWarnings("serial")
public class InsertNotExecutedException extends RuntimeException {

	public InsertNotExecutedException(String message) {
		super(message);
	}

}
