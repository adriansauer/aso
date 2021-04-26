package com.py.aso.dto.update;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.py.aso.dto.RoleDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a los usuarios del sistema, utilizado para actualizar el objeto")
public class UserUpdateDTO {

	@NotBlank(message = "El nombre del usuario es requerido")
	@ApiModelProperty(notes = "Nombre del Usuario", example = "Juan", required = true)
	private String name;

	@Size(min = 0, max = 125, message = "El apellido puede contener como maximo 125 caracteres")
	@ApiModelProperty(notes = "Apellido del Usuario", example = "Perez")
	private String lastname;

	@NotBlank(message = "El codigo de usuario es requerido")
	@Size(min = 2, max = 50, message = "El codigo puede contener como maximo 50 caracteres")
	@ApiModelProperty(notes = "Codigo del Usuario", example = "ABCDE-1234", required = true)
	private String usercode;

	@Email(message = "El correo electronico debe ser valido")
	@ApiModelProperty(notes = "Email del Usuario", example = "user@gmail.com")
	private String email;

	@NotNull(message = "La lista de roles es requerido")
	@ApiModelProperty(notes = "Lista de roles del Usuario", example = "[{\"id\":1}]", required = true)
	private List<RoleDTO> roles;

}
