package com.py.aso.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una ciudad")
public class CityDTO {

	private Long id;

	@NotBlank(message = "El nombre de la ciudad es requerida")
	@ApiModelProperty(notes = "name", example = "Encarnación", required = true)
	private String name;

}
