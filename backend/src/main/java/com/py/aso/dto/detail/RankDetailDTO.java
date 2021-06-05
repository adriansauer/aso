package com.py.aso.dto.detail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un rango de los bomberos, se utiliza para mostrar todos los datos del objeto")
public class RankDetailDTO {

	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@NotBlank(message = "El titulo del rango es requerido")
	@ApiModelProperty(notes = "Titulo del Rango", example = "Comandante", required = true)
	private String title;

	@ApiModelProperty(notes = "Descripción del Rango", example = "Es el rango mas alto")
	private String description;
	
	@NotBlank(message = "La imagen en base64 es requerida")
	@ApiModelProperty(notes = "Imagen en Base 64", required = true)
	private String image;

}
