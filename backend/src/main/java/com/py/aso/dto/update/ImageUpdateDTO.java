package com.py.aso.dto.update;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una imagen, utilizado para actualizar el objeto")
public class ImageUpdateDTO {

	@NotBlank(message = "El nombre de la imagen es requerida")
	@ApiModelProperty(notes = "Nombre de la Imagen", example = "Perfil de Juan", required = true)
	private String name;
	
	@NotBlank(message = "La imagen en base64 es requerida")
	@ApiModelProperty(notes = "Imagen en Base 64", required = true)
	private String file;
}
