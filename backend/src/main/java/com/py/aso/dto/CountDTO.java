package com.py.aso.dto;

import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que la cantidad de un recurso")
public class CountDTO {

	@Positive(message = "la cantidad debe ser mayor a 0")
	@ApiModelProperty(notes = "Cantidad", example = "1", required = true)
	private Long quantity;
}
