package com.py.aso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
	@GetMapping("/publication/{id}")
	@ApiOperation(value="Obtener una publicación por el id")
	public PublicationDetailDTO find(@PathVariable final long id) throws Exception {
		return this.publicationService.findById(id);
	}

	@Override
	@PostMapping("/publication")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE') or hasRole('ROLE_USER')")
	@ApiOperation(value = "Crear una nueva publicación")
	public PublicationDetailDTO create(@Validated @RequestBody final PublicationCreateDTO dto) throws Exception {
		return this.publicationService.save(dto);
	}

	@Override
	public PublicationDetailDTO update(long id, PublicationUpdateDTO dto) throws Exception {
		return null;
	}

	@Override
	public void deleted(long id) throws Exception {
	}

}
