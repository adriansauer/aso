package com.py.aso.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un bombero")
public class FiremanDTO {

	private Long id;

	@NotBlank(message = "El nombre de la brigada es requerida")
	@ApiModelProperty(notes = "name", example = "CVB de Encarnación", required = true)
	private String name;

	@Size(min = 0, max = 125, message = "El apellido puede contener como maximo 125 caracteres")
	@ApiModelProperty(notes = "lastname", example = "Perez")
	private String lastname;

	@NotBlank(message = "El codigo de usuario es requerido")
	@Size(min = 2, max = 50, message = "El codigo puede contener como maximo 50 caracteres")
	@ApiModelProperty(notes = "usercode", example = "ABCDE", required = true)
	private String usercode;

	@NotBlank(message = "La ciudad de la brigada es requerida")
	@ApiModelProperty(notes = "city", example = "Encarnación", required = true)
	private String city;

	@NotBlank(message = "El nombre del rango es requerido")
	@ApiModelProperty(notes = "titleRank", example = "Comandante", required = true)
	private String rankTitle;

	@NotBlank(message = "El nombre de la brigada es requerida")
	@ApiModelProperty(notes = "brigadeName", example = "CVB de Encarnación", required = true)
	private String brigadeName;

	@ApiModelProperty(notes = "imageId", example = "1", required = true)
	private long imageId;

	@ApiModelProperty(notes = "brigadeId", example = "1", required = true)
	private long brigadeId;

	@ApiModelProperty(notes = "rankId", example = "1", required = true)
	private long rankId;
}
