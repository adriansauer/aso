package com.py.aso.dto.create;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un bombero, se utiliza para crear")
public class FiremanCreateDTO {

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

	@NotBlank(message = "La contraseña es requerida")
	@Size(min = 8, max = 255, message = "La contraseña debe contener 8 caracteres como minimo y 255 caracteres como maximo")
	@ApiModelProperty(notes = "Contraseña", example = "pass1234-ABC_xzy", required = true)
	private String password;

	@NotBlank(message = "Repetir la contraseña es requerido")
	@Size(min = 8, max = 255, message = "La contraseña debe contener 8 caracteres como minimo y 255 caracteres como maximo")
	@ApiModelProperty(notes = "Contraseña repetida", example = "pass1234-ABC_xzy", required = true)
	private String repeatPassword;

	@NotNull(message = "La fecha de admisión del bombero en la brigada es requerida")
	@ApiModelProperty(notes = "Fecha de adminsión de Bombero al cuartel", example = "2021-04-05T18:51:28.478+00:00", required = true)
	private Date admission;

	@NotNull(message = "La fecha de nacimiento del bombero es requerida")
	@ApiModelProperty(notes = "Fecha de cumpleaños del Bombero", example = "2021-04-05T18:51:28.478+00:00", required = true)
	private Date birthday;

	@NotBlank(message = "La dirección del bombero es requerida")
	@ApiModelProperty(notes = "Dirección del domicilio del Bombero", example = "Av. Jose L. Oviedo", required = true)
	private String address;

	@Positive(message = "El id de la ciudad debe ser mayor a 0")
	@ApiModelProperty(notes = "Id de la ciudad donde se encuentra el Bombero", example = "5", required = true)
	private long cityId;

	@Positive(message = "El id del departamento debe ser mayor a 0")
	@ApiModelProperty(notes = "Id del departamento donde se encuentra el Bombero", example = "5", required = true)
	private long departamentId;

	@Positive(message = "El id del rango debe ser mayor a 0")
	@ApiModelProperty(notes = "Id del rango del Bombero", example = "1", required = true)
	private long rankId;

	@Positive(message = "El id de la brigada debe ser mayor a 0")
	@ApiModelProperty(notes = "Id de la brigada del Bombero", example = "1", required = true)
	private long brigadeId;
	
	@NotBlank(message = "La imagen de perfil en base64 es requerida")
	@ApiModelProperty(notes = "Imagen de perfil en Base 64", required = true)
	private String image;

}
