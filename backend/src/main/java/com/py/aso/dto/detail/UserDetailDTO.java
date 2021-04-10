package com.py.aso.dto.detail;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.py.aso.dto.RoleDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a los usuarios del sistema")
public class UserDetailDTO {

	private Long id;

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

	@NotBlank(message = "El correo electronico es requerido")
	@Email(message = "El correo electronico debe ser valido")
	@ApiModelProperty(notes = "email", example = "user@gmail.com", required = true)
	private String email;

	@ApiModelProperty(notes = "createdAt", example = "2021-04-05T18:51:28.478+00:00")
	private Date createdAt;

	@ApiModelProperty(notes = "updatedAt", example = "2021-04-05T18:51:28.478+00:00")
	private Date updatedAt;

	@ApiModelProperty(notes = "roles", example = "ROLE_ADMIN")
	private List<RoleDTO> roles;

}
