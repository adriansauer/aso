package com.py.aso.dto;

import java.util.Date;

import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una brigada, se utiliza para listar")
public class BrigadeDTO {

	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@ApiModelProperty(notes = "Nombre de la Brigada", example = "CVB de Encarnación")
	private String name;

	@ApiModelProperty(notes = "Codigo de usuario de la Brigada", example = "ABCDE")
	private String usercode;

	@ApiModelProperty(notes = "Nombre del departamento", example = "Itapúa")
	private String departament;

	@ApiModelProperty(notes = "Bombre de la ciudad", example = "Encarnación")
	private String city;

	@ApiModelProperty(notes = "Fecha de creacion de la Brigada", example = "2021-04-05T18:51:28.478+00:00")
	private Date creation;

	@ApiModelProperty(notes = "Cantidad de Miembros", example = "5")
	private int numberMember;

	@ApiModelProperty(notes = "Id de la imagen del perfil", example = "1")
	private long imageId;

}
