package com.py.aso.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(description = "Objeto que representa a una excepcion ocurrada")
public class ExceptionDTO {

	@NotBlank(message = "La descripcion no puede ser nulo")
	@ApiModelProperty(notes = "description", example = "Recurso no encontrado", required = true)
	private String descrip;
}
