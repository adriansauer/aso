package com.py.aso.exception;

/**
 * 
 * @author Victor Ciceia
 * 
 *         Clase que representa una excepcion
 * 
 *         Se utiliza cuando el archivo en cuestion tiene problemas.
 * 
 */
public class FileProblemsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileProblemsException(final String message) {
		super(String.format(message));
	}

}
