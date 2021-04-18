package com.py.aso.dto;

import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa un Usuario del sistema, se utiliza para listar")
public class UserDTO {

	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@ApiModelProperty(notes = "Nombre del Usuario", example = "Juan")
	private String name;

	@ApiModelProperty(notes = "Apellido del Usuario", example = "Perez")
	private String lastname;

	@ApiModelProperty(notes = "Codigo del Usuario", example = "ABCDE-1234")
	private String usercode;

}
