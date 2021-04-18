package com.py.aso.dto;

import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un bombero, se utiliza para listar")
public class FiremanDTO {

	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@ApiModelProperty(notes = "Nombre del bombero", example = "Juan")
	private String name;

	@ApiModelProperty(notes = "Apellido del Bombero", example = "Perez")
	private String lastname;

	@ApiModelProperty(notes = "Codigo del Bombero", example = "ASO-1234")
	private String usercode;

	@ApiModelProperty(notes = "Nombre de la ciudad de residencia", example = "Encarnación")
	private String city;

	@ApiModelProperty(notes = "Nombre del rango del Bombero", example = "Comandante")
	private String rankTitle;

	@ApiModelProperty(notes = "Nombre de la brigada a la que pertenece", example = "CVB de Encarnación")
	private String brigadeName;

	@ApiModelProperty(notes = "Id de la imagen del perfil", example = "1")
	private long imageId;

	@ApiModelProperty(notes = "Id de la brigada a la que pertenece", example = "1")
	private long brigadeId;

	@ApiModelProperty(notes = "Id del rango", example = "1")
	private long rankId;
}
