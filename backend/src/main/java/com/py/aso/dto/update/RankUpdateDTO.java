package com.py.aso.dto.update;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un rango de los bomberos, utilizado para actualizar el objeto")
public class RankUpdateDTO {

	@NotBlank(message = "El nombre del rango es requerido")
	@ApiModelProperty(notes = "Titulo del Rango", example = "Comandante", required = true)
	private String title;

	@ApiModelProperty(notes = "Descripción del Rango", example = "Es el rango mas alto")
	private String description;

}
