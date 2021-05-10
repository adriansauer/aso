package com.py.aso.exception;

public class FilesMaximumException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FilesMaximumException() {
		super(String.format("Superó la cantidad máxima de archivos permitidos"));
	}
}
