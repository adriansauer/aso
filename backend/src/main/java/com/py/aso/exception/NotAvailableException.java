package com.py.aso.exception;

/**
 * 
 * @author Victor Ciceia
 * 
 *         Clase que representa una excepcion
 * 
 *         Se debe utilizar cuando un recurso no esta disponible 
 * 
 */
public class NotAvailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotAvailableException() {
		super(String.format("No esta disponible esta acción"));
	}
}
