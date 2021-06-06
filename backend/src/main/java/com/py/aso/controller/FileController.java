package com.py.aso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.py.aso.dto.FileDTO;
import com.py.aso.dto.create.FileCreateDTO;
import com.py.aso.dto.detail.FileDetailDTO;
import com.py.aso.dto.update.FileUpdateDTO;
import com.py.aso.service.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de archivos")
@RequestMapping("/api")
public class FileController implements BaseController<FileDTO, FileDetailDTO, FileCreateDTO, FileUpdateDTO> {

	@Autowired
	private FileService fileService;
	
	@Override
	@GetMapping("files")
	@ApiOperation(value="Obtener todas los archivos, permite paginación")
	public Page<FileDTO> index(Pageable pageable) {
		return this.fileService.findAll(pageable);
	}

	@Override
	@GetMapping("/files/{id}")
	@ApiOperation(value="Obtener un archivo por el id")
	public FileDetailDTO find(@PathVariable final long id) throws Exception {
		return this.fileService.findById(id);
	}

	@Override
	@PostMapping("/files")
	@ApiOperation(value = "Registra un archivo en la base de datos, no hace copia del archivo")
	public FileDetailDTO create(@Validated @RequestBody final FileCreateDTO fileCreateDTO) throws Exception {
		return this.fileService.save(fileCreateDTO);
	}

	@Override
	@PutMapping("/files/{id}")
	@ApiOperation(value = "Actualizar los datos del archivo por el id")
	public FileDetailDTO update(@PathVariable final long id, @Validated @RequestBody final FileUpdateDTO fileUpdateDTO) throws Exception {
		return this.fileService.update(id, fileUpdateDTO);
	}


	@Override
	@DeleteMapping("/files/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Eliminar un archivo por el id")
	public void deleted(@PathVariable final long id) throws Exception {
		this.fileService.delete(id);
	}
}
