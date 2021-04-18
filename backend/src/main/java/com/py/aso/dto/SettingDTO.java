package com.py.aso.dto;

import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una configuración del sistema, se utiliza para listar")
public class SettingDTO {

	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@ApiModelProperty(notes = "Clave", example = "NAME_APP")
	private String key;

	@ApiModelProperty(notes = "Valor", example = "ASO")
	private String value;

}
