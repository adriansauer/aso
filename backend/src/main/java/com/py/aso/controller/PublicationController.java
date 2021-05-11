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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.py.aso.dto.PublicationDTO;
import com.py.aso.dto.create.PublicationCreateDTO;
import com.py.aso.dto.detail.PublicationDetailDTO;
import com.py.aso.dto.update.PublicationUpdateDTO;
import com.py.aso.service.PublicationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de publicaciones")
@RequestMapping("/api")
public class PublicationController implements BaseController<PublicationDTO, PublicationDetailDTO, PublicationCreateDTO, PublicationUpdateDTO>{
	@Autowired
	private PublicationService publicationService;
	
	@Override
	@GetMapping("/publication")
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
	@PostMapping("/publication/{id}")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE') or hasRole('ROLE_USER')")
	@ApiOperation(value = "Actualiza una publicación indicado por el id")
	public PublicationDetailDTO update(@PathVariable final long id, @Validated @RequestBody PublicationUpdateDTO dto) throws Exception {
		return this.publicationService.update(id, dto);
	}

	@Override
	@DeleteMapping("/publication/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Eliminar una publicación idicada el id")
	public void deleted(@PathVariable final long id) throws Exception {
		this.publicationService.delete(id);
	}

}
