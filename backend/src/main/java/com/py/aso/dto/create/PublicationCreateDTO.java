package com.py.aso.dto.create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.py.aso.validations.PublicationDestination;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una publicación, se utiliza para crear")
public class PublicationCreateDTO {

	@NotBlank(message = "El contenido de la publicación es requerido")
	@Size(min = 0, max = 255, message = "El contenido puede contener como maximo 255 caracteres")
	@ApiModelProperty(notes = "Contenido de la publicación", example = "Contenido extenso", required = true)
	private String body;
	
	@NotBlank(message = "El destinatario de la publicación es requerido")
	@PublicationDestination(message = "Como destino solo se permite [Publico, Todos, Mi Brigada]")
	@ApiModelProperty(notes = "Destino de la publicación", example = "Destino", required = true)
	private String destination;
}
