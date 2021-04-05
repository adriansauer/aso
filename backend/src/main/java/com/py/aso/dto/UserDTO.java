package com.py.aso.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.py.aso.entity.RoleEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a los usuarios del sistema")
public class UserDTO {

	private Long id;

	@NotBlank(message = "El nombre del usuario es requerido")
	@ApiModelProperty(notes = "name", example = "Juan", required = true)
	private String name;

	@Max(value = 125, message = "El apellido solo se permite hasta 125 caracteres")
	@ApiModelProperty(notes = "lastname", example = "Perez")
	private String lastname;

	@NotBlank(message = "El codigo de usuario es requerido")
	@Max(value = 50, message = "El codigo solo permite hasta 50 caracteres")
	@ApiModelProperty(notes = "usercode", example = "ABCDE-1234", required = true)
	private String usercode;

	@NotBlank(message = "El correo electronico es requerido")
	@Email(message = "El correo electronico debe ser valido")
	@ApiModelProperty(notes = "email", example = "user@gmail.com", required = true)
	private String email;

	@NotBlank(message = "La contraseña es requerida")
	@Min(value = 8, message = "La contraseña debe contener 8 caracteres como minimo")
	@ApiModelProperty(notes = "password", example = "pass1234-ABC_xzy")
	private String password;

	@ApiModelProperty(notes = "enabled", example = "true")
	private boolean enabled;

	private List<RoleEntity> roles;

}
