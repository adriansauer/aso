package com.py.aso.dto.detail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una configuración del sistema, se utiliza para mostrar todos los datos del objeto")
public class SettingDetailDTO {

	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@NotBlank(message = "La clave no puede ser nula")
	@ApiModelProperty(notes = "Clave", example = "NAME_APP", required = true)
	private String key;

	@NotBlank(message = "El valor no puede ser nulo")
	@ApiModelProperty(notes = "Valor", example = "ASO", required = true)
	private String value;

}
