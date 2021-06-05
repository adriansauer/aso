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
@ApiModel(description = "Objeto que representa a una brigada, se utiliza para mostrar todos los datos del objeto")
public class BrigadeDetailDTO {

	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@NotBlank(message = "El nombre de la brigada es requerido")
	@ApiModelProperty(notes = "Nombre de la Brigada", example = "CVB de Encarnación", required = true)
	private String name;

	@NotBlank(message = "El codigo de la brigada es requerido")
	@Size(min = 2, max = 50, message = "El codigo puede contener como maximo 50 caracteres")
	@ApiModelProperty(notes = "Codigo de usuario de la Brigada", example = "ABCDE", required = true)
	private String usercode;

	@NotBlank(message = "La dirección de la brigada es requerida")
	@ApiModelProperty(notes = "Dirección del cuartel de la Brigada", example = "Esq. Juan A. Villar y Argentina", required = true)
	private String address;

	@ApiModelProperty(notes = "Telefono del cuartel de la Brigadas", example = "(0985)586 222")
	private String phone;

	@ApiModelProperty(notes = "Dirección del cuartel de la Brigada", example = "El CVB es el mejor de la zona")
	private String description;

	@Email(message = "El correo electronico debe ser valido")
	@ApiModelProperty(notes = "Email de la Brigada", example = "brigada@gmail.com")
	private String email;

	@PositiveOrZero(message = "La cantidad de miembros debe ser igual o mayor a 0")
	@ApiModelProperty(notes = "numberMember", example = "5", required = true)
	private int numberMember;

	@NotBlank(message = "La fecha de creación de la brigada es requerida")
	@ApiModelProperty(notes = "Fecha de creación de la Brigada", example = "2021-04-05T18:51:28.478+00:00", required = true)
	private Date creation;

	@ApiModelProperty(notes = "Fecha de creación de recurso en el sistema", example = "2021-04-05T18:51:28.478+00:00")
	private Date createdAt;

	@ApiModelProperty(notes = "Fecha de modificación de recurso en el sistema", example = "2021-04-05T18:51:28.478+00:00")
	private Date updatedAt;

	@NotBlank(message = "La imagen de perfil en base64 es requerida")
	@ApiModelProperty(notes = "Imagen de perfil en Base 64", required = true)
	private String image;

	@Positive(message = "El id del departamento debe ser mayor a 0")
	@ApiModelProperty(notes = "Id del departamento donde se encuentra la Brigada", example = "5", required = true)
	private long departamentId;

	@NotBlank(message = "El nombre del departamento de la brigada es requerida")
	@ApiModelProperty(notes = "Nombre del departamento donde se encuentra la Brigada", example = "Itapúa", required = true)
	private String departament;

	@Positive(message = "El id de la ciudad debe ser mayor a 0")
	@ApiModelProperty(notes = "Id de la ciudad donde se encuentra la Brigada", example = "5", required = true)
	private long cityId;

	@NotBlank(message = "El nombre de la ciudad de la brigada es requerida")
	@ApiModelProperty(notes = "Nombre de la ciudad donde se encuentra la Brigada", example = "Encarnación", required = true)
	private String city;

	@Positive(message = "El id del usuario debe ser mayor a 0")
	@ApiModelProperty(notes = "Id del usuario de la Brigada", example = "5", required = true)
	private long userId;

}
