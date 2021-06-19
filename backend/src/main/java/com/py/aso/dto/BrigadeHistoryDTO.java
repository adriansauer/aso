package com.py.aso.dto;

import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una historia de una brigada, se utiliza para listar")
public class BrigadeHistoryDTO {
	
	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@ApiModelProperty(notes = "Texto de la historia de la brigada", example = "Historia de la brigada")
	private String text;
	
	@ApiModelProperty(notes = "Id de la brigada", example = "1")
	private long brigadeId;

}
