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

import com.py.aso.dto.BrigadeHistoryDTO;
import com.py.aso.dto.create.BrigadeHistoryCreateDTO;
import com.py.aso.dto.detail.BrigadeHistoryDetailDTO;
import com.py.aso.dto.update.BrigadeHistoryUpdateDTO;
import com.py.aso.service.BrigadeHistoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de historias de brigadas")
@RequestMapping("/api")
public class BrigadeHistoryController implements
		BaseController<BrigadeHistoryDTO, BrigadeHistoryDetailDTO, BrigadeHistoryCreateDTO, BrigadeHistoryUpdateDTO> {

	@Autowired
	private BrigadeHistoryService brigadeHistoryService;

	@Override
	@GetMapping("/brigade-history")
	@ApiOperation(value = "Obtener todas las ciudades, permite paginacion")
	public Page<BrigadeHistoryDTO> index(final Pageable pageable) {
		return this.brigadeHistoryService.findAll(pageable);
	}

	@Override
	@GetMapping("/brigade-history/{id}")
	@ApiOperation(value = "Obtener una historia por el id")
	public BrigadeHistoryDetailDTO find(@PathVariable final long id) throws Exception {
		return this.brigadeHistoryService.findById(id);
	}

	@GetMapping("/brigade-history/bybrigadeid/{brigadeId}")
	@ApiOperation(value = "Obtener una historia por el id de la brigada")
	public BrigadeHistoryDetailDTO findByBrigadeId(@PathVariable final long brigadeId) throws Exception {
		return this.brigadeHistoryService.findByBrigadeId(brigadeId);
	}

	@Override
	@PostMapping("/brigade-history")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Crear una nueva historia")
	public BrigadeHistoryDetailDTO create(@Validated @RequestBody final BrigadeHistoryCreateDTO brigadeHistoryCreateDTO) throws Exception {
		return this.brigadeHistoryService.save(brigadeHistoryCreateDTO);
	}

	@Override
	@PutMapping("/brigade-history/{id}")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Actualizar una ciudad por el id, si no existe crea la historia")
	public BrigadeHistoryDetailDTO update(@PathVariable final long id,
			@Validated @RequestBody final BrigadeHistoryUpdateDTO brigadeHistoryUpdateDTO) throws Exception {
		return this.brigadeHistoryService.update(id, brigadeHistoryUpdateDTO);
	}

	@Override
	@DeleteMapping("/brigade-history/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
	@ApiOperation(value = "Eliminar una historia de una brigada por el id")
	public void deleted(@PathVariable final long id) throws Exception {
		this.brigadeHistoryService.delete(id);
	}

}
