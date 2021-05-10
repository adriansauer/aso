package com.py.aso.dto.create;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(description = "Objeto que representa a un archivo, se utiliza para crear")
public class FileCreateDTO {

	@Positive(message = "Debe ser mayor a 0")
	@ApiModelProperty(notes = "Identificador de la publicación", example = "1")
	private Long publicationId;

	@NotBlank(message = "El nombre del archivo es requerido")
	@Size(min = 0, max = 255, message = "El nombre puede contener como maximo 255 caracteres")
	@ApiModelProperty(notes = "Nombre del archivo", example = "nombreArchito.extension", required = true)
	private String name;
}
