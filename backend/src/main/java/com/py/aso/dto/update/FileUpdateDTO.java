package com.py.aso.dto.update;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(description = "Objeto que representa a un archivo, se utiliza para actualizar el objeto")
public class FileUpdateDTO {
	private String name;
}
