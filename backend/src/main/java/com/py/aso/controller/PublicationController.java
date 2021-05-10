package com.py.aso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

import com.py.aso.dto.PublicationDTO;
import com.py.aso.dto.create.PublicationCreateDTO;
import com.py.aso.dto.detail.PublicationDetailDTO;
import com.py.aso.dto.update.PublicationUpdateDTO;
import com.py.aso.service.PublicationService;

import io.swagger.annotations.ApiOperation;

public class PublicationController implements BaseController<PublicationDTO, PublicationDetailDTO, PublicationCreateDTO, PublicationUpdateDTO>{
	@Autowired
	private PublicationService publicationService;
	
	@Override
	@GetMapping("publication")
	@ApiOperation(value="Obtener todas las publicaciones, permite paginación")
	public Page<PublicationDTO> index(Pageable pageable) {
		return this.publicationService.findAll(pageable);
	}

	@Override
	public PublicationDetailDTO find(long id) throws Exception {
		return null;
	}

	@Override
	public PublicationDetailDTO create(PublicationCreateDTO dto) throws Exception {
		return null;
	}

	@Override
	public PublicationDetailDTO update(long id, PublicationUpdateDTO dto) throws Exception {
		return null;
	}

	@Override
	public void deleted(long id) throws Exception {
	}

}
