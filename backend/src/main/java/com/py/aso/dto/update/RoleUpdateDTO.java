package com.py.aso.dto.update;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a los roles, utilizado para actualizar el objeto")
public class RoleUpdateDTO {

	@NotBlank(message = "La autoridad es requerida")
	@ApiModelProperty(notes = "Titulo del rol", example = "ROLE_ADMIN", required = true)
	private String authority;

}
