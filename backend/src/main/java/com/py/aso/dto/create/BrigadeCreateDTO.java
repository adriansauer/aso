package com.py.aso.dto.create;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una brigada")
public class BrigadeCreateDTO {

	@NotBlank(message = "El nombre de la brigada es requerido")
	@ApiModelProperty(notes = "name", example = "CVB de Encarnación", required = true)
	private String name;

	@NotBlank(message = "El codigo de la brigada es requerido")
	@Size(min = 2, max = 50, message = "El codigo puede contener como maximo 50 caracteres")
	@ApiModelProperty(notes = "usercode", example = "ABCDE", required = true)
	private String usercode;

	@NotBlank(message = "El departamento de la brigada es requerido")
	@ApiModelProperty(notes = "departament", example = "Itapúa", required = true)
	private String address;

	@ApiModelProperty(notes = "phone", example = "(0985)586 222")
	private String phone;

	@Email(message = "El correo electronico debe ser valido")
	@ApiModelProperty(notes = "email", example = "brigada@gmail.com")
	private String email;

	@ApiModelProperty(notes = "description", example = "El CVB es el mejor de la zona")
	private String description;

	@NotBlank(message = "La contraseña es requerida")
	@Min(value = 8, message = "La contraseña debe contener 8 caracteres como minimo")
	@ApiModelProperty(notes = "password", example = "pass1234-ABC_xzy", required = true)
	private String password;

	@NotBlank(message = "Repetir la contraseña es requerido")
	@ApiModelProperty(notes = "repeatPassword", example = "12345678", required = true)
	private String repeatPassword;

	@ApiModelProperty(notes = "imageId", example = "5")
	private long imageId;

	@Positive(message = "El id del departamento debe ser mayor a 0")
	@ApiModelProperty(notes = "departamentId", example = "5", required = true)
	private long departamentId;

	@Positive(message = "El id de la ciudad debe ser mayor a 0")
	@ApiModelProperty(notes = "cityId", example = "5", required = true)
	private long cityId;
}
