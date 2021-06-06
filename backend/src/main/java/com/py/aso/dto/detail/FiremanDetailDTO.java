package com.py.aso.dto.detail;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un bombero, se utiliza para mostrar todos los datos del objeto")
public class FiremanDetailDTO {

	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@NotBlank(message = "El nombre del bombero es requerido")
	@ApiModelProperty(notes = "Nombre del Bombero", example = "Maria", required = true)
	private String name;

	@NotBlank(message = "El apellido del bombero es requerido")
	@Size(min = 0, max = 125, message = "El apellido puede contener como maximo 125 caracteres")
	@ApiModelProperty(notes = "Apellido del Bombero", example = "Perez", required = true)
	private String lastname;

	@NotBlank(message = "El codigo del bombero es requerido")
	@Size(min = 2, max = 50, message = "El codigo puede contener como maximo 50 caracteres")
	@ApiModelProperty(notes = "Codigo de usuario del Bombero", example = "ABCDE", required = true)
	private String usercode;

	@NotBlank(message = "La cedula de identidad es requerida")
	@Size(min = 5, max = 30, message = "La cedula de identidad puede contener como maximo 30 caracteres")
	@ApiModelProperty(notes = "Numero de Cedula de Identidad del Bombero", example = "1234567", required = true)
	private String ci;

	@ApiModelProperty(notes = "Telefono del Bombero", example = "(0985)586 222")
	private String phone;

	@ApiModelProperty(notes = "Descripción del Bombero", example = "Es el primer bombero que participo en la asamblea")
	private String description;

	@Email(message = "El correo electronico debe ser valido")
	@ApiModelProperty(notes = "Email del Bombero", example = "bombero@gmail.com")
	private String email;

	@NotBlank(message = "La fecha de admisión del bombero en la brigada es requerida")
	@ApiModelProperty(notes = "Fecha de admisión del Bombero al cuartel", example = "2021-04-05T18:51:28.478+00:00", required = true)
	private Date admission;

	@NotBlank(message = "La fecha de nacimieneto del bombero es requerida")
	@ApiModelProperty(notes = "Fecha de cumpleaños del Bombero", example = "2021-04-05T18:51:28.478+00:00", required = true)
	private Date birthday;

	@ApiModelProperty(notes = "Fecha de creación de recurso en el sistema", example = "2021-04-05T18:51:28.478+00:00")
	private Date createdAt;

	@ApiModelProperty(notes = "Fecha de modificación de recurso en el sistema", example = "2021-04-05T18:51:28.478+00:00")
	private Date updatedAt;

	@NotBlank(message = "La dirección del bombero es requerida")
	@ApiModelProperty(notes = "Dirección del domicilio del Bombero", example = "Av. Jose L. Oviedo", required = true)
	private String address;

	@NotBlank(message = "La ciudad de del bombero es requerida")
	@ApiModelProperty(notes = "Nombre de la ciudad donde se encuentra el Bombero", example = "Encarnación", required = true)
	private String city;

	@Positive(message = "El id de la ciudad debe ser mayor a 0")
	@ApiModelProperty(notes = "Id de la ciudad donde se encuentra el Bombero", example = "5", required = true)
	private long cityId;

	@NotBlank(message = "El departamento del bombero es requerida")
	@ApiModelProperty(notes = "Nombre del departamento donde se encuentra el Bombero", example = "Itapúa", required = true)
	private String departament;

	@Positive(message = "El id del departamento debe ser mayor a 0")
	@ApiModelProperty(notes = "Id del departamento donde se encuentra el Bombero", example = "5", required = true)
	private long departamentId;

	@NotBlank(message = "El nombre del rango es requerido")
	@ApiModelProperty(notes = "Titulo del rango del Bombero", example = "Comandante", required = true)
	private String rankTitle;

	@Positive(message = "El id del rango debe ser mayor a 0")
	@ApiModelProperty(notes = "Id del rango del Bombero", example = "1", required = true)
	private long rankId;

	@Positive(message = "El id de la brigada debe ser mayor a 0")
	@ApiModelProperty(notes = "Id de la brigada del Bombero", example = "1", required = true)
	private long brigadeId;

	@ApiModelProperty(notes = "Imagen de perfil en Base 64")
	private String image;

	@Positive(message = "El id del usuario debe ser mayor a 0")
	@ApiModelProperty(notes = "Id del usuario de perfil del Bombero", example = "5", required = true)
	private long userId;

}
