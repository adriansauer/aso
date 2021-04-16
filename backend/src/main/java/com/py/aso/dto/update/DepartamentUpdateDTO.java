package com.py.aso.dto.update;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un departamento")
public class DepartamentUpdateDTO {

	@NotBlank(message = "El nombre del departamento es requerido")
	@ApiModelProperty(notes = "name", example = "Itapúa", required = true)
	private String name;

}
