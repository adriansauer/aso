package com.py.aso.dto.create;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un rango de los bomberos, se utiliza para crear")
public class RankCreateDTO {

	@NotBlank(message = "El nombre del rango es requerido")
	@ApiModelProperty(notes = "Titulo del Rango", example = "Comandante", required = true)
	private String title;

	@ApiModelProperty(notes = "Descripción del Rango", example = "Es el rango mas alto")
	private String description;
	
	@NotBlank(message = "La imagen en base64 es requerida")
	@ApiModelProperty(notes = "Imagen en Base 64", required = true)
	private String image;

}
