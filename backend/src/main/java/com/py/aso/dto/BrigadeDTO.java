package com.py.aso.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una brigada")
public class BrigadeDTO {

	private Long id;

	@NotBlank(message = "El nombre de la brigada es requerida")
	@ApiModelProperty(notes = "name", example = "CVB de Encarnación", required = true)
	private String name;

	@NotBlank(message = "El codigo de usuario es requerido")
	@Size(min = 2, max = 50, message = "El codigo puede contener como maximo 50 caracteres")
	@ApiModelProperty(notes = "usercode", example = "ABCDE", required = true)
	private String usercode;

	@NotBlank(message = "El departamento de la brigada es requerido")
	@ApiModelProperty(notes = "departament", example = "Itapúa", required = true)
	private String departament;

	@NotBlank(message = "La ciudad de la brigada es requerida")
	@ApiModelProperty(notes = "city", example = "Encarnación", required = true)
	private String city;

	@NotBlank(message = "La fecha de creación de la brigada es requerida")
	@ApiModelProperty(notes = "creation", example = "2021-04-05T18:51:28.478+00:00", required = true)
	private Date creation;

	@PositiveOrZero(message = "La cantidad de miembros debe ser igual o mayor a 0")
	@ApiModelProperty(notes = "numberMember", example = "5", required = true)
	private int numberMember;

	@ApiModelProperty(notes = "imageId", example = "1")
	private long imageId;

}
