package com.py.aso.dto.update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una publicación, utilizado para actualizar el objeto")
public class PublicationUpdateDTO {

	@NotBlank(message = "El contenido de la publicación es requerido")
	@Size(min = 0, max = 255, message = "El contenido puede contener como maximo 255 caracteres")
	@ApiModelProperty(notes = "Contenido de la publicación", example = "Contenido extenso", required = true)
	private String body;

	@NotBlank(message = "El destinatario de la publicación es requerido")
	@Size(min = 0, max = 10, message = "El destino puede contener como maximo 10 caracteres")
	@ApiModelProperty(notes = "Destino de la publicación", example = "Destino", required = true)
	private String destination;

	@ApiModelProperty(notes = "Id de la brigada a la que esta dirigida la publicación", example = "5")
	private long brigadeId;

	@ApiModelProperty(notes = "Identificador del codigo de insidente", example = "1")
	private Long incidenceCodeId;

}
