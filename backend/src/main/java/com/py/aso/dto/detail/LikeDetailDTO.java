package com.py.aso.dto.detail;

import java.util.Date;

import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un like, se utiliza para crear un me gusta")
public class LikeDetailDTO {
	
	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;
	
	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Id de la publicación a la que se le dio me gusta", example = "1", required = true)
	private Long publicationId;
	
	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Id de usuario que dio el me gusta", example = "1", required = true)
	private Long userId;
	
	@ApiModelProperty(notes = "Fecha de la acción", example = "2021-05-05T23:11:28.478+00:00")
	private Date date;

}
