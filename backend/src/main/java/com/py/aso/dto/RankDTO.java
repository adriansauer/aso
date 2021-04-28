package com.py.aso.dto;

import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un rango de los bomberos, se utiliza para listar")
public class RankDTO {

	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@ApiModelProperty(notes = "Titulo del rango", example = "Comandante")
	private String title;

	@ApiModelProperty(notes = "Id de la imagen que representa al rango", example = "1")
	private long imageId;

}
