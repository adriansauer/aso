package com.py.aso.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que se utiliza para autenticacion de usuarios")
public class LoginDTO {

	@NotBlank(message = "El codigo de usuario es requerido")
	@ApiModelProperty(notes = "usercode", example = "ASO-123", required = true)
	private String usercode;

	@NotBlank(message = "La contraseña es requerida")
	@ApiModelProperty(notes = "password", example = "12345678", required = true)
	private String password;

}