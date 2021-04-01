package com.py.aso.exception;

/**
 * 
 * @author Victor Ciceia
 * 
 *         Clase que representa una excepcion
 * 
 *         Se debe utilizar cuando un recurso buscado por un atributo no es
 *         encontrado.
 * 
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(final String resourceName, final String by, final Object key) {
		super(String.format("El recurso %s no fue encontrado por %s <%s>", resourceName, by, key));
	}

}
