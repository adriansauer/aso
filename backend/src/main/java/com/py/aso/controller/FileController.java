package com.py.aso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.py.aso.dto.FileDTO;
import com.py.aso.dto.create.FileCreateDTO;
import com.py.aso.dto.detail.FileDetailDTO;
import com.py.aso.dto.update.FileUpdateDTO;
import com.py.aso.service.FileService;

public class FileController implements BaseController<FileDTO, FileDetailDTO, FileCreateDTO, FileUpdateDTO> {

	@Autowired
	private FileService fileService;
	
	@Override
	public Page<FileDTO> index(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileDetailDTO find(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
