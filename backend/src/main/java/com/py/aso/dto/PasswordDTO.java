package com.py.aso.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que se utiliza para resibir el password del usuario y actualizarlo")
public class PasswordDTO {

	@NotBlank(message = "La contraseña es requerida")
	@Size(min = 8, max = 255, message = "La contraseña debe contener 8 caracteres como minimo y 255 caracteres como maximo")
	@ApiModelProperty(notes = "newPassword", example = "12345678", required = true)
	private String newPassword;

	@NotBlank(message = "Repetir la contraseña es requerido")
	@Size(min = 8, max = 255, message = "La contraseña debe contener 8 caracteres como minimo y 255 caracteres como maximo")
	@ApiModelProperty(notes = "repeatPassword", example = "12345678", required = true)
	private String repeatPassword;

	@NotBlank(message = "La contraseña es antigua es requerida")
	@ApiModelProperty(notes = "oldPassword", example = "12345678", required = true)
	private String oldPassword;

}