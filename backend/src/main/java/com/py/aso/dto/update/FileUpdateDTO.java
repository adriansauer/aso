package com.py.aso.dto.update;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(description = "Objeto que representa a un archivo, se utiliza para actualizar el objeto")
public class FileUpdateDTO {

	@NotBlank(message = "El nombre del archivo es requerido")
	@Size(min = 0, max = 255, message = "El nombre puede contener como maximo 255 caracteres")
	@ApiModelProperty(notes = "Nombre del archivo", example = "nombreArchito.extension", required = true)
	private String name;
}
