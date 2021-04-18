package com.py.aso.dto.update;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un departamento, utilizado para actualizar el objeto")
public class DepartamentUpdateDTO {

	@NotBlank(message = "El nombre del departamento es requerido")
	@ApiModelProperty(notes = "Nombre del Departamento", example = "Itapúa", required = true)
	private String name;

}
