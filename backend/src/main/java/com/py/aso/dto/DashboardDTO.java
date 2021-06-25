package com.py.aso.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un dashboard")
public class DashboardDTO {

	@NotBlank(message = "El codigo de la insidencia es requerido")
	@ApiModelProperty(notes = "Codigo de insidencia", example = "10.70", required = true)
	private String code;
	
	@Positive(message = "El año es requerido")
	@ApiModelProperty(notes = "Año del dashboard", example = "2021", required = true)
	private long year;
	
}
