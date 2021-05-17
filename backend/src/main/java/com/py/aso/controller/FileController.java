package com.py.aso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	private FileService<?> fileService;
	
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

	@GetMapping("/files/files/{id}")
	@ApiOperation(value = "Obtener un archivo por el id")
	public ResponseEntity<Resource> findFileById(@PathVariable final long id) throws Exception {
		final Resource file = this.fileService.findFileById(id);
		System.out.println("controlador");
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@Override
	@PostMapping("/files")
	@ApiOperation(value = "Crear un archivo por defecto")
	public FileDetailDTO create(@Validated @RequestBody final FileCreateDTO dto) throws Exception {
		return this.fileService.save(dto);
	}
	
	@PostMapping(path = "/files/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiOperation(value = "Crear un nuevo archivo")
	public FileDetailDTO createFile(@RequestParam("file") final MultipartFile file,	@RequestParam("name") final String name) throws Exception {
		return this.fileService.saveFile(file, name);
	}

	@Override
	@PutMapping("/files/{id}")
	@ApiOperation(value = "Actualizar los datos del archivo por el id")
	public FileDetailDTO update(@PathVariable final long id, @Validated @RequestBody final FileUpdateDTO dto) throws Exception {
		return this.fileService.update(id, dto);
	}
	
	@PutMapping("/files/files/{id}")
	@ApiOperation(value = "Actualizar un archivo por el id")
	public FileDetailDTO updateFile(@RequestParam("file") final MultipartFile file, @PathVariable final long id) throws Exception {
		return this.fileService.updateFile(file, id);
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
