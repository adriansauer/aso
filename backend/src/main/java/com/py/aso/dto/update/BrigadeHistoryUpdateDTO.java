package com.py.aso.dto.update;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una historia de una brigada, se utiliza para actualizar la historia")
public class BrigadeHistoryUpdateDTO {
	
	@NotBlank(message = "El texto de la historia es requerido")
	@ApiModelProperty(notes = "Texto de la historia de la brigada", example = "Historia de la brigada", required = true)
	private String text;

}
