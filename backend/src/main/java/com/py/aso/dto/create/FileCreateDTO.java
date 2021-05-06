package com.py.aso.dto.create;
import javax.validation.constraints.Positive;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(description = "Objeto que representa a un archivo, se utiliza para crear")
public class FileCreateDTO {
	private Long publicationId;
	private String name;
}
