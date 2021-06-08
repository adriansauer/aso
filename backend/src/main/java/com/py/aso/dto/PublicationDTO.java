package com.py.aso.dto;

import java.util.Date;

import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa una publicación")
public class PublicationDTO {

	@Positive(message = "Debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@Positive(message = "Debe ser mayor a 0")
	@ApiModelProperty(notes = "Identificador del Usuario", example = "1")
	private Long userId;

	@ApiModelProperty(notes = "Fecha de creacion de la publicación", example = "2021-05-05T23:11:28.478+00:00")
	private Date createAt;
	
	@ApiModelProperty(notes = "Fecha de actualización de la publicación", example = "2021-05-05T23:11:28.478+00:00")
	private Date updateAt;

	@ApiModelProperty(notes = "Contenido de la publicación", example = "Contenido extenso")
	private String body;
	
	@ApiModelProperty(notes = "Destino de la publicación", example = "Destino")
	private String destination;
	
	@ApiModelProperty(notes = "Cantidad de like de la publicación", example = "1")
	private long likeQuantity;
	
	@ApiModelProperty(notes = "Si tiene el like del usuario", example = "true")
	private boolean iLike;
}
