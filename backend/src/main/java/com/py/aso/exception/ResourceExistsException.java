package com.py.aso.exception;

/**
 * 
 * @author Victor Ciceia
 * 
 *         Clase que representa una excepcion
 * 
 *         Se utiliza cuando un recurso ya existe
 * 
 */
public class ResourceExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceExistsException(final String attribute) {
		super(String.format("Ya existe el recuso %s", attribute));
	}
}
