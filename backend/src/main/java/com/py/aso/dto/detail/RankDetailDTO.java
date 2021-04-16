package com.py.aso.dto.detail;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un un rango de los bomberos")
public class RankDetailDTO {

	private Long id;

	@NotBlank(message = "El nombre del rango es requerido")
	@ApiModelProperty(notes = "title", example = "Comandante", required = true)
	private String title;

	@ApiModelProperty(notes = "description", example = "Es el rango mas alto")
	private String description;

	@ApiModelProperty(notes = "imageId", example = "1", required = true)
	private long imageId;

}
