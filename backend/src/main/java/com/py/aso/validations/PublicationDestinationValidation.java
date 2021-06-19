package com.py.aso.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 
 * @author Victor Ciceia
 * 
 *         Validacion de destino de una publicacion. Solo se permite los destinos
 *         especificados. [Publico, Todos, Mi Brigada]
 */
public class PublicationDestinationValidation implements ConstraintValidator<PublicationDestination, String> {

	public static final String DESTINATION_MY_BRIGADE = "Mi Brigada";
	public static final String DESTINATION_ALL = "Todos";

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value.equals(DESTINATION_ALL)) {
			return true;
		} else if (value.equals(DESTINATION_MY_BRIGADE)) {
			return true;
		} else {
			return false;
		}
	}

}
