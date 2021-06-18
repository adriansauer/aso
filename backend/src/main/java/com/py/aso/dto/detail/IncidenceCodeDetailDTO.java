package com.py.aso.dto.detail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un codigo de incidencia, se utiliza para obtener detalles.")
public class IncidenceCodeDetailDTO {
	
	@Positive(message = "El id debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@NotBlank(message = "El codigo de incidencia")
	@Size(min = 1, max = 50, message = "El codigo de incidencia puede contener como maximo 50 caracteres")
	@ApiModelProperty(notes = "Codigo de incidencia", example = "AL-2536", required = true)
	private String code;
	
	@NotBlank(message = "Description del codigo de incidencia")
	@Size(min = 1, max = 255, message = "La descripción de codigo de incidencia puede contener como maximo 255 caracteres")
	@ApiModelProperty(notes = "Descripción de codigo de incidencia", example = "Incendio", required = true)
	private String description;

}