package com.py.aso.dto.create;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un bombero")
public class FiremanCreateDTO {

	@NotBlank(message = "El nombre del bombero es requerido")
	@ApiModelProperty(notes = "name", example = "Maria", required = true)
	private String name;

	@NotBlank(message = "El apellido del bombero es requerido")
	@Size(min = 0, max = 125, message = "El apellido puede contener como maximo 125 caracteres")
	@ApiModelProperty(notes = "lastname", example = "Perez", required = true)
	private String lastname;

	@NotBlank(message = "El codigo del bombero es requerido")
	@Size(min = 2, max = 50, message = "El codigo puede contener como maximo 50 caracteres")
	@ApiModelProperty(notes = "usercode", example = "ABCDE", required = true)
	private String usercode;

	@NotBlank(message = "La cedula de identidad es requerida")
	@Size(min = 5, max = 30, message = "La cedula de identidad puede contener como maximo 30 caracteres")
	@ApiModelProperty(notes = "ci", example = "1234567", required = true)
	private String ci;

	@ApiModelProperty(notes = "phone", example = "(0985)586 222")
	private String phone;

	@ApiModelProperty(notes = "description", example = "Es el primer bombero que participo en la asamblea")
	private String description;

	@Email(message = "El correo electronico debe ser valido")
	@ApiModelProperty(notes = "email", example = "bombero@gmail.com")
	private String email;

	@NotBlank(message = "La contraseña es requerida")
	@Min(value = 8, message = "La contraseña debe contener 8 caracteres como minimo")
	@ApiModelProperty(notes = "password", example = "pass1234-ABC_xzy", required = true)
	private String password;

	@NotBlank(message = "Repetir la contraseña es requerido")
	@ApiModelProperty(notes = "repeatPassword", example = "12345678", required = true)
	private String repeatPassword;

	@NotBlank(message = "La fecha de admisión del bombero en la brigada es requerida")
	@ApiModelProperty(notes = "admission", example = "2021-04-05T18:51:28.478+00:00", required = true)
	private Date admission;

	@NotBlank(message = "La fecha de nacimieneto del bombero es requerida")
	@ApiModelProperty(notes = "birthday", example = "2021-04-05T18:51:28.478+00:00", required = true)
	private Date birthday;

	@NotBlank(message = "La dirección del bombero es requerida")
	@ApiModelProperty(notes = "address", example = "Av. Jose L. Oviedo", required = true)
	private String address;

	@Positive(message = "El id de la ciudad debe ser mayor a 0")
	@ApiModelProperty(notes = "cityId", example = "5", required = true)
	private long cityId;

	@Positive(message = "El id del departamento debe ser mayor a 0")
	@ApiModelProperty(notes = "departamentId", example = "5", required = true)
	private long departamentId;

	@Positive(message = "El id del rango debe ser mayor a 0")
	@ApiModelProperty(notes = "rankId", example = "1", required = true)
	private long rankId;

	@Positive(message = "El id de la brigada debe ser mayor a 0")
	@ApiModelProperty(notes = "brigadeId", example = "1", required = true)
	private long brigadeId;

	@Positive(message = "El id de la imagen debe ser mayor a 0")
	@ApiModelProperty(notes = "imageId", example = "1", required = true)
	private long imageId;

}
