package com.py.aso.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que se utiliza para resibir el password del usuario y actualizarlo")
public class PasswordDTO {

	@NotBlank(message = "La contraseña es requerida")
	@ApiModelProperty(notes = "newPassword", example = "12345678", required = true)
	private String newPassword;

	@NotBlank(message = "Repetir la contraseña es requerido")
	@ApiModelProperty(notes = "repeatPassword", example = "12345678", required = true)
	private String repeatPassword;

	@NotBlank(message = "La contraseña es antigua es requerida")
	@ApiModelProperty(notes = "oldPassword", example = "12345678", required = true)
	private String oldPassword;

}