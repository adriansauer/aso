package com.py.aso.dto.create;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a los roles, se utiliza para crear")
public class RoleCreateDTO {

	@NotBlank(message = "La autoridad es requerida")
	@ApiModelProperty(notes = "Titulo del Rol", example = "ROLE_ADMIN", required = true)
	private String authority;

}
