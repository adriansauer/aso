package com.py.aso.dto.detail;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a los roles que posee el sistema")
public class RoleDetailDTO {

	private Long id;

	@NotBlank(message = "La autoridad es requerida")
	@ApiModelProperty(notes = "authority", example = "ROLE_ADMIN", required = true)
	private String authority;

}
