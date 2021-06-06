package com.py.aso.dto.update;

import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un like, se utiliza para actualizar un me gusta")
public class LikeUpdateDTO {

	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Id de la publicación a la que se le dio me gusta", example = "1", required = true)
	private Long publicationId;

}
