package com.py.aso.dto.create;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una imagen")
public class ImageCreateDTO {

	@NotBlank(message = "El nombre de la imagen es requerida")
	@ApiModelProperty(notes = "name", example = "Perfil de Juan", required = true)
	private String name;

}
