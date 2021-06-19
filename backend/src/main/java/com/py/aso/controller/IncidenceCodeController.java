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

import com.py.aso.dto.IncidenceCodeDTO;
import com.py.aso.dto.create.IncidenceCodeCreateDTO;
import com.py.aso.dto.detail.IncidenceCodeDetailDTO;
import com.py.aso.dto.update.IncidenceCodeUpdateDTO;
import com.py.aso.service.IncidenceCodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de codigos de incidencia")
@RequestMapping("/api")
public class IncidenceCodeController implements BaseController<IncidenceCodeDTO,IncidenceCodeDetailDTO,IncidenceCodeCreateDTO,IncidenceCodeUpdateDTO> {
	
	@Autowired
	private IncidenceCodeService incidenceCodeService;

	@Override
	@GetMapping("incidence-code")
	@ApiOperation(value="Obtener todos los codigos de incidencias, permite paginación")
	public Page<IncidenceCodeDTO> index(final Pageable pageable) {
		return this.incidenceCodeService.findAll(pageable);
	}

	@Override
	@GetMapping("/incidence-code/{id}")
	@ApiOperation(value="Obtener un codigo de incidencia por el id")
	public IncidenceCodeDetailDTO find(@PathVariable final long id) throws Exception {
		return this.incidenceCodeService.findById(id);
	}
	
	@GetMapping("/incidence-code/bycode/{code}")
	@ApiOperation(value="Obtener un codigo de incidencia por el codigo")
	public IncidenceCodeDetailDTO findByCode(@PathVariable final String code) throws Exception {
		return this.incidenceCodeService.findByCode(code);
	}

	@Override
	@PostMapping("/incidence-code")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Crea un nuevo codigo de incidencia")
	public IncidenceCodeDetailDTO create(@Validated @RequestBody final IncidenceCodeCreateDTO incidenceCodeCreateDTO) throws Exception {
		return this.incidenceCodeService.save(incidenceCodeCreateDTO);
	}

	@Override
	@PutMapping("/incidence-code/{id}")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Actualiza un codigo de incidencia por el id")
	public IncidenceCodeDetailDTO update(@PathVariable final long id, @Validated @RequestBody final IncidenceCodeUpdateDTO incidenceCodeUpdateDTO) throws Exception {
		return this.incidenceCodeService.update(id, incidenceCodeUpdateDTO);
	}

	@Override
	@DeleteMapping("/incidence-code/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
	@ApiOperation(value = "Eliminar un codigo de incidencia por el id")
	public void deleted(@PathVariable final long id) throws Exception {
		this.incidenceCodeService.delete(id);
	}

}
