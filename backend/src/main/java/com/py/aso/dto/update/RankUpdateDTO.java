package com.py.aso.dto.update;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un un rango de los bomberos")
public class RankUpdateDTO {

	@NotBlank(message = "El nombre del rango es requerido")
	@ApiModelProperty(notes = "title", example = "Comandante", required = true)
	private String title;

	@ApiModelProperty(notes = "description", example = "Es el rango mas alto")
	private String description;

	@ApiModelProperty(notes = "imageId", example = "1")
	private long imageId;

}
