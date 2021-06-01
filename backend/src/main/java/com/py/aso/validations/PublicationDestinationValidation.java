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

	public final String DESTINATION_MY_BRIGADE = "Mi Brigada";
	public final String DESTINATION_ALL = "Todos";
	public final String DESTINATION_PUBLIC = "Publico";

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value.equals(this.DESTINATION_ALL)) {
			return true;
		} else if (value.equals(this.DESTINATION_MY_BRIGADE)) {
			return true;
		} else if (value.equals(this.DESTINATION_PUBLIC)) {
			return true;
		} else {
			return false;
		}
	}

}
