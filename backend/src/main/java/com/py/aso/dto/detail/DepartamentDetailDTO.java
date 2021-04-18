package com.py.aso.dto.detail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un departamento, se utiliza para mostrar todos los datos del objeto")
public class DepartamentDetailDTO {

	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@NotBlank(message = "El nombre del departamento es requerido")
	@ApiModelProperty(notes = "Nombre del Departamento", example = "Itapúa", required = true)
	private String name;

}
