package com.py.aso.exception;

public class FilesMaximumException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FilesMaximumException(final String message) {
		super(String.format(message));
	}
}
