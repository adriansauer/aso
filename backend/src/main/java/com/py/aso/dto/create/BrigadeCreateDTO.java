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
@ApiModel(description = "Objeto que representa a una brigada, se utiliza para crear")
public class BrigadeCreateDTO {

	@NotBlank(message = "El nombre de la brigada es requerido")
	@ApiModelProperty(notes = "Nombre de la Brigada", example = "CVB de Encarnación", required = true)
	private String name;

	@NotBlank(message = "El codigo de usuario de la brigada es requerido")
	@Size(min = 2, max = 50, message = "El codigo puede contener como maximo 50 caracteres")
	@ApiModelProperty(notes = "Codigo de usuario de la Brigada", example = "ABCDE", required = true)
	private String usercode;

	@NotBlank(message = "La dirección de la brigada es requerida")
	@ApiModelProperty(notes = "Dirección del cuartel de la Brigada", example = "Av. Jose L. Oviedo", required = true)
	private String address;

	@ApiModelProperty(notes = "Telefono del cuartel de la Brigadas", example = "(0985)586 222")
	private String phone;

	@NotNull(message = "La fecha de creación de la brigada es requerida")
	@ApiModelProperty(notes = "Fecha de creación de la Brigada", example = "2021-04-05T18:51:28.478+00:00", required = true)
	private Date creation;

	@Email(message = "El correo electronico debe ser valido")
	@ApiModelProperty(notes = "Email de la Brigada", example = "brigada@gmail.com")
	private String email;

	@ApiModelProperty(notes = "Descripcion de la Brigada", example = "El CVB se especializa en rescate en altura")
	private String description;

	@NotBlank(message = "La contraseña es requerida")
	@Size(min = 8, max = 255, message = "La contraseña debe contener 8 caracteres como minimo y 255 caracteres como maximo")
	@ApiModelProperty(notes = "Contraseña", example = "pass1234-ABC_xzy", required = true)
	private String password;

	@NotBlank(message = "Repetir la contraseña es requerido")
	@Size(min = 8, max = 255, message = "La contraseña debe contener 8 caracteres como minimo y 255 caracteres como maximo")
	@ApiModelProperty(notes = "Contraseña repetida", example = "pass1234-ABC_xzy", required = true)
	private String repeatPassword;

	@Positive(message = "El id del departamento debe ser mayor a 0")
	@ApiModelProperty(notes = "Id del departamento donde se encuentra la Brigada", example = "5", required = true)
	private long departamentId;

	@Positive(message = "El id de la ciudad debe ser mayor a 0")
	@ApiModelProperty(notes = "Id de la ciudad donde se encuentra la Brigada", example = "5", required = true)
	private long cityId;
	
	@NotBlank(message = "La imagen de perfil en base64 es requerida")
	@ApiModelProperty(notes = "Imagen de perfil en Base 64", required = true)
	private String image;
}
