package com.py.aso.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(description = "Objeto que representa a una excepción ocurrida")
public class ExceptionDTO {

	@ApiModelProperty(notes = "Descripción de la excepción", example = "Recurso no encontrado", required = true)
	private String description;
}
