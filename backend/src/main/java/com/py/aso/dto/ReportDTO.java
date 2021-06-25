package com.py.aso.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa un reporte de un año")
public class ReportDTO {
	
	private String code;
	
	private String description;
	
	private long year;
	
	private long quantity;
	
	private Months months;

}
