package com.loja.exceptions;

// Exception: Checked exceptions (compilador) handle or declare
// RuntimeException: Unchecked Exception (sem regras)

@SuppressWarnings("serial")
public class UpdateNotExecutedException extends Exception {

	public UpdateNotExecutedException(String message) {
		super(message);
	}

}
