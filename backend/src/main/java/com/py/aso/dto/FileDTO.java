package com.py.aso.dto;
import javax.validation.constraints.Positive;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(description = "Objeto que representa un archivo")
public class FileDTO {

	@Positive(message = "Debe ser mayor a 0")
	@ApiModelProperty(notes = "Numero identificador", example = "1", required = true)
	private Long id;

	@ApiModelProperty(notes = "Nombre del archivo", example = "nombreArchito.extension")
	private String name;
}
