package com.py.aso.dto.detail;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una brigada")
public class BrigadeDetailDTO {

	private Long id;

	@NotBlank(message = "El nombre de la brigada es requerido")
	@ApiModelProperty(notes = "name", example = "CVB de Encarnación", required = true)
	private String name;

	@NotBlank(message = "El codigo de la brigada es requerido")
	@Size(min = 2, max = 50, message = "El codigo puede contener como maximo 50 caracteres")
	@ApiModelProperty(notes = "usercode", example = "ABCDE", required = true)
	private String usercode;

	@NotBlank(message = "La dirección de la brigada es requerida")
	@ApiModelProperty(notes = "address", example = "Esq. Juan A. Villar y Argentina", required = true)
	private String address;

	@ApiModelProperty(notes = "phone", example = "(0985)586 222")
	private String phone;

	@ApiModelProperty(notes = "description", example = "El CVB es el mejor de la zona")
	private String description;

	@Email(message = "El correo electronico debe ser valido")
	@ApiModelProperty(notes = "email", example = "brigada@gmail.com")
	private String email;

	@PositiveOrZero(message = "La cantidad de miembros debe ser igual o mayor a 0")
	@ApiModelProperty(notes = "numberMember", example = "5", required = true)
	private int numberMember;

	@NotBlank(message = "La fecha de creación de la brigada es requerida")
	@ApiModelProperty(notes = "creation", example = "2021-04-05T18:51:28.478+00:00", required = true)
	private Date creation;

	@ApiModelProperty(notes = "createdAt", example = "2021-04-05T18:51:28.478+00:00")
	private Date createdAt;

	@ApiModelProperty(notes = "updatedAt", example = "2021-04-05T18:51:28.478+00:00")
	private Date updatedAt;

	@ApiModelProperty(notes = "imageId", example = "5")
	private long imageId;

	@Positive(message = "El id del departamento debe ser mayor a 0")
	@ApiModelProperty(notes = "departamentId", example = "5", required = true)
	private long departamentId;

	@Positive(message = "El id de la ciudad debe ser mayor a 0")
	@ApiModelProperty(notes = "cityId", example = "5", required = true)
	private long cityId;

	@Positive(message = "El id del usuario debe ser mayor a 0")
	@ApiModelProperty(notes = "userId", example = "5", required = true)
	private long userId;

}
