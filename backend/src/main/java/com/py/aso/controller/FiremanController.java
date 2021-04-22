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

import com.py.aso.dto.FiremanDTO;
import com.py.aso.dto.create.FiremanCreateDTO;
import com.py.aso.dto.detail.FiremanDetailDTO;
import com.py.aso.dto.update.FiremanUpdateDTO;
import com.py.aso.service.FiremanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de bomberos")
@RequestMapping("/api")
public class FiremanController
		implements BaseController<FiremanDTO, FiremanDetailDTO, FiremanCreateDTO, FiremanUpdateDTO> {

	@Autowired
	private FiremanService firemanService;

	@Override
	@GetMapping("/fireman")
	@ApiOperation(value = "Obtener todas las ciudades, permite paginacion")
	public Page<FiremanDTO> index(final Pageable pageable) {
		return this.firemanService.findAll(pageable);
	}

	@Override
	@GetMapping("/fireman/{id}")
	@ApiOperation(value = "Obtener un bombero por el id")
	public FiremanDetailDTO find(@PathVariable final long id) throws Exception {
		return this.firemanService.findById(id);
	}

	@GetMapping("/fireman/by/brigade/{id}")
	@ApiOperation(value = "Obtener los bomberos por brigada, permite paginacion")
	public Page<FiremanDTO> findByBrigade(@PathVariable final long id, final Pageable pageable) throws Exception {
		return this.firemanService.findByBrigadeId(id, pageable);
	}

	@Override
	@PostMapping("/fireman")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Crear un nuevo bombero")
	public FiremanDetailDTO create(@Validated @RequestBody final FiremanCreateDTO firemanCreateDTO) throws Exception {
		return this.firemanService.save(firemanCreateDTO);
	}

	@Override
	@PutMapping("/fireman/{id}")
	@ApiOperation(value = "Actualizar un bombero por el id")
	public FiremanDetailDTO update(@PathVariable final long id,
			@Validated @RequestBody final FiremanUpdateDTO firemanUpdateDTO) throws Exception {
		return this.firemanService.update(id, firemanUpdateDTO);
	}

	@Override
	@DeleteMapping("/fireman/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Eliminar un bombero por el id")
	public void deleted(@PathVariable final long id) throws Exception {
		this.firemanService.delete(id);
	}

}
