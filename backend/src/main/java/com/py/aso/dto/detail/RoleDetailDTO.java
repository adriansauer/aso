package com.py.aso.dto.detail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a los roles que posee el sistema, se utiliza para mostrar todos los datos del objeto")
public class RoleDetailDTO {

	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@NotBlank(message = "La autoridad es requerida")
	@ApiModelProperty(notes = "Titulo del rol", example = "ROLE_ADMIN", required = true)
	private String authority;

}
