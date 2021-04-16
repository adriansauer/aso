package com.py.aso.dto.update;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una configuracion que sera creada o actualizada en el sistema")
public class SettingUpdateDTO {

	@NotBlank(message = "El key no puede ser nulo")
	@ApiModelProperty(notes = "Key", example = "NAME_APP", required = true)
	private String key;

	@NotBlank(message = "El value no puede ser nulo")
	@ApiModelProperty(notes = "Value", example = "ASO", required = true)
	private String value;

}
