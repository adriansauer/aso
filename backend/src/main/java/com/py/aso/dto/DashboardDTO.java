package com.py.aso.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a un dashboard")
public class DashboardDTO {

	@ApiModelProperty(notes = "Codigo de insidencia", example = "10.70")
	private String code;
	
	@ApiModelProperty(notes = "Año del dashboard", example = "2021")
	private long year;
	
	@ApiModelProperty(notes = "El id del usuario", example = "2021")
	private long userId;
	
}
