package com.py.aso.dto.create;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una configuracion, se utiliza para crear")
public class SettingCreateDTO {

	@NotBlank(message = "El key no puede ser nulo")
	@ApiModelProperty(notes = "Clave", example = "NAME_APP", required = true)
	private String key;

	@NotBlank(message = "El value no puede ser nulo")
	@ApiModelProperty(notes = "Valor", example = "ASO", required = true)
	private String value;

}
