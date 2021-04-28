package com.py.aso.dto.detail;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.py.aso.dto.RoleDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a los usuarios del sistema, se utiliza para mostrar todos los datos del objeto")
public class UserDetailDTO {

	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

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

	@NotBlank(message = "El correo electronico es requerido")
	@Email(message = "El correo electronico debe ser valido")
	@ApiModelProperty(notes = "Email del Usuario", example = "user@gmail.com", required = true)
	private String email;

	@ApiModelProperty(notes = "Fecha de creación de recurso en el sistema", example = "2021-04-05T18:51:28.478+00:00")
	private Date createdAt;

	@ApiModelProperty(notes = "Fecha de modificación de recurso en el sistema", example = "2021-04-05T18:51:28.478+00:00")
	private Date updatedAt;

	@ApiModelProperty(notes = "Id del detalle de usuario,  ya sea u bombero o una brigada", example = "1")
	private Long detailId;

	@ApiModelProperty(notes = "Lista de roles del Usuario", example = "[{id: 1}]")
	private List<RoleDTO> roles;

}
