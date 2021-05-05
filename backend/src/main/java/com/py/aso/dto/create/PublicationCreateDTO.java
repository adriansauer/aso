package com.py.aso.dto.create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una publicación, se utiliza para crear")
public class PublicationCreateDTO {
	
	@Positive(message = "Debe ser mayor a 0")
	@ApiModelProperty(notes = "Identificador del Usuario", example = "1", required = true)
	private Long userId;

	@NotBlank(message = "El contenido de la publicación es requerido")
	@Size(min = 0, max = 255, message = "El contenido puede contener como maximo 255 caracteres")
	@ApiModelProperty(notes = "Contenido de la publicación", example = "Contenido extenso", required = true)
	private String body;
	
	@NotBlank(message = "El destino de la publicación es requerido")
	@Size(min = 0, max = 10, message = "El destiono puede contener como maximo 255 caracteres")
	@ApiModelProperty(notes = "Destino de la publicación", example = "Destino", required = true)
	private String destination;
}
