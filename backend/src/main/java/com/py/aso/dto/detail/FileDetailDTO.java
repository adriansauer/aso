package com.py.aso.dto.detail;
import javax.validation.constraints.Positive;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(description = "Objeto que representa a un archivo, se utiliza para mostrar todos los datos del objeto")
public class FileDetailDTO {
	private Long id;
	private Long publicationId;
	private String name;
}
