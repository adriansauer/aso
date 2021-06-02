package com.py.aso.exception;

/**
 * 
 * @author Victor Ciceia
 * 
 *         Clase que representa una excepcion
 * 
 *         Se debe utilizar cuando el valor de un argumento es invalido
 * 
 */
public class InvalidArgumentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidArgumentException(final String argName, final String admited) {
		super(String.format("Valor invalido para %s, solo se permite %s", argName, admited));
	}
}
