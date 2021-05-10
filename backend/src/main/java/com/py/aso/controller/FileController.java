package com.py.aso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

	@Override
	public FileDetailDTO create(FileCreateDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
