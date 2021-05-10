package com.py.aso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.py.aso.dto.FileDTO;
import com.py.aso.dto.create.FileCreateDTO;
import com.py.aso.dto.detail.FileDetailDTO;
import com.py.aso.dto.update.FileUpdateDTO;
import com.py.aso.service.FileService;

import io.swagger.annotations.ApiOperation;

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
	
	@GetMapping("/files/files/{id}")
	@ApiOperation(value = "Obtener un archivo por el id")
	public ResponseEntity<Resource> findFileById(@PathVariable final long id) throws Exception {
		final Resource file = (Resource) this.fileService.findById(id);
		return ResponseEntity.ok()//
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")//
				.body(file);
	}

	@Override
	@PostMapping("/files")
	@ApiOperation(value = "Crear un archivo por defecto")
	public FileDetailDTO create(@Validated @RequestBody final FileCreateDTO dto) throws Exception {
		return this.fileService.save(dto);
	}

	@Override
	public FileDetailDTO update(long id, FileUpdateDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleted(long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
