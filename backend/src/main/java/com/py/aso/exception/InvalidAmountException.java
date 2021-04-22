package com.py.aso.exception;

/**
 * 
 * @author Victor Ciceia
 * 
 *         Clase que representa una excepcion
 * 
 *         Se utiliza cuando un atributo esta fuera de rango
 * 
 */
public class InvalidAmountException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidAmountException(final String attribute) {
		super(String.format("%s esta fuera de rango aceptado", attribute));
	}
}
