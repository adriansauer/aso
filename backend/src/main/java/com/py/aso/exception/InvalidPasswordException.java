package com.py.aso.exception;

/**
 * 
 * @author Victor Ciceia
 * 
 *         Clase que representa una excepcion
 * 
 *         Se utiliza cuando la contraseña no es valida o no coincide.
 * 
 */
public class InvalidPasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPasswordException(final String message) {
		super(String.format(message));
	}

}
