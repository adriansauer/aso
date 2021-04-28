package com.py.aso.dto;

import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una imagen de perfil o logo, se utiliza para listar")
public class ImageDTO {

	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@ApiModelProperty(notes = "Nombre de la Imagen", example = "Perfil de Juan")
	private String name;
}
