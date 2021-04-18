package com.py.aso.dto.create;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una ciudad, se utiliza para crear")
public class CityCreateDTO {

	@NotBlank(message = "El nombre de la ciudad es requerida")
	@ApiModelProperty(notes = "Nombre de la Ciudad", example = "Encarnación", required = true)
	private String name;

}
