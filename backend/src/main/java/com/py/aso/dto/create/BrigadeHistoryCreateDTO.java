package com.py.aso.dto.create;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una historia de una brigada, se utiliza para crear la historia")
public class BrigadeHistoryCreateDTO {

	@NotBlank(message = "El texto de la historia es requerido")
	@ApiModelProperty(notes = "Texto de la historia de la brigada", example = "Historia de la brigada", required = true)
	private String text;

	@ApiModelProperty(notes = "Id de la brigada", example = "1")
	private long brigadeId;
	
}
