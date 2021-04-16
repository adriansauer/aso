package com.py.aso.dto.create;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.py.aso.dto.RoleDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a los usuarios del sistema")
public class UserCreateDTO {

	@NotBlank(message = "El nombre del usuario es requerido")
	@ApiModelProperty(notes = "name", example = "Juan", required = true)
	private String name;

	@Size(min = 0, max = 125, message = "El apellido puede contener como maximo 125 caracteres")
	@ApiModelProperty(notes = "lastname", example = "Perez")
	private String lastname;

	@NotBlank(message = "El codigo de usuario es requerido")
	@Size(min = 2, max = 50, message = "El codigo puede contener como maximo 50 caracteres")
	@ApiModelProperty(notes = "usercode", example = "ABCDE-1234", required = true)
	private String usercode;

	@Email(message = "El correo electronico debe ser valido")
	@ApiModelProperty(notes = "email", example = "user@gmail.com")
	private String email;

	@NotBlank(message = "La contraseña es requerida")
	@Min(value = 8, message = "La contraseña debe contener 8 caracteres como minimo")
	@ApiModelProperty(notes = "password", example = "pass1234-ABC_xzy", required = true)
	private String password;

	@NotBlank(message = "Repetir la contraseña es requerido")
	@ApiModelProperty(notes = "repeatPassword", example = "12345678", required = true)
	private String repeatPassword;

	@ApiModelProperty(notes = "roles", example = "pass1234-ABC_xzy", required = true)
	private List<RoleDTO> roles;

}
