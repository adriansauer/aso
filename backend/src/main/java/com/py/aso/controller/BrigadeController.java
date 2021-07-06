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

import com.py.aso.dto.BrigadeDTO;
import com.py.aso.dto.create.BrigadeCreateDTO;
import com.py.aso.dto.detail.BrigadeDetailDTO;
import com.py.aso.dto.update.BrigadeUpdateDTO;
import com.py.aso.service.BrigadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de brigadas")
@RequestMapping("/api")
public class BrigadeController
		implements BaseController<BrigadeDTO, BrigadeDetailDTO, BrigadeCreateDTO, BrigadeUpdateDTO> {

	@Autowired
	private BrigadeService brigadeService;

	@Override
	@GetMapping("/brigades")
	@ApiOperation(value = "Obtener todas las brigadas, permite paginacion")
	public Page<BrigadeDTO> index(final Pageable pageable) {
		return this.brigadeService.findAll(pageable);
	}
	
	@GetMapping("/public/brigades")
	@ApiOperation(value = "Obtener todas las brigadas, permite paginacion")
	public Page<BrigadeDTO> indexPublic(final Pageable pageable) {
		return this.brigadeService.findAll(pageable);
	}

	@Override
	@GetMapping("/brigades/{id}")
	@ApiOperation(value = "Obtener una brigada por el id")
	public BrigadeDetailDTO find(@PathVariable final long id) throws Exception {
		return this.brigadeService.findById(id);
	}

	@Override
	@PostMapping("/brigades")
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
	@ApiOperation(value = "Crear una nueva brigada")
	public BrigadeDetailDTO create(@Validated @RequestBody final BrigadeCreateDTO brigadeCreateDTO) throws Exception {
		return this.brigadeService.save(brigadeCreateDTO);
	}

	@Override
	@PutMapping("/brigades/{id}")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Actualizar una brigada por el id")
	public BrigadeDetailDTO update(@PathVariable final long id,
			@Validated @RequestBody final BrigadeUpdateDTO brigadeUpdateDTO) throws Exception {
		return this.brigadeService.update(id, brigadeUpdateDTO);
	}

	@Override
	@DeleteMapping("/brigades/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
	@ApiOperation(value = "Eliminar una brigada por el id")
	public void deleted(@PathVariable final long id) throws Exception {
		this.brigadeService.delete(id);
	}

}
