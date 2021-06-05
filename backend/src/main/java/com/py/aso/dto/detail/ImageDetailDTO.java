package com.py.aso.dto.detail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a una imagen, se utiliza para mostrar todos los datos del objeto")
public class ImageDetailDTO {

	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@NotBlank(message = "El nombre de la imagen es requerida")
	@ApiModelProperty(notes = "Nombre de la Imagen", example = "Perfil de Juan", required = true)
	private String name;

	@ApiModelProperty(notes = "Imagen en base 64")
	private String file;

}
