package com.py.aso.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Objeto que representa a la candidades de cada mes del año")
public class Months {
	
	@ApiModelProperty(notes = "Mes de enero", example = "1")
	private long january;
	
	@ApiModelProperty(notes = "Mes de febrero", example = "1")
	private long february;
	
	@ApiModelProperty(notes = "Mes de marzo", example = "1")
	private long march;
	
	@ApiModelProperty(notes = "Mes de abril", example = "1")
	private long april;
	
	@ApiModelProperty(notes = "Mes de mayo", example = "1")
	private long may;
	
	@ApiModelProperty(notes = "Mes de junio", example = "1")
	private long june;
	
	@ApiModelProperty(notes = "Mes de julio", example = "1")
	private long july;
	
	@ApiModelProperty(notes = "Mes de agosto", example = "1")
	private long august;
	
	@ApiModelProperty(notes = "Mes de septiembre", example = "1")
	private long september;
	
	@ApiModelProperty(notes = "Mes de octubre", example = "1")
	private long october;
	
	@ApiModelProperty(notes = "Mes de noviembre", example = "1")
	private long november;
	
	@ApiModelProperty(notes = "Mes de diciembre", example = "1")
	private long december;
	
}