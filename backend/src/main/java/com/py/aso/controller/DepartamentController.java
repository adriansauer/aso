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

import com.py.aso.dto.DepartamentDTO;
import com.py.aso.dto.create.DepartamentCreateDTO;
import com.py.aso.dto.detail.DepartamentDetailDTO;
import com.py.aso.dto.update.DepartamentUpdateDTO;
import com.py.aso.service.DepartamentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de departamentos del paraguay")
@RequestMapping("/api")
public class DepartamentController
		implements BaseController<DepartamentDTO, DepartamentDetailDTO, DepartamentCreateDTO, DepartamentUpdateDTO> {

	@Autowired
	private DepartamentService departamentService;

	@Override
	@GetMapping("/departaments")
	@ApiOperation(value = "Obtener todos los departamentos, permite paginacion")
	public Page<DepartamentDTO> index(final Pageable pageable) {
		return this.departamentService.findAll(pageable);
	}

	@Override
	@GetMapping("/departaments/{id}")
	@ApiOperation(value = "Obtener un departamento por el id")
	public DepartamentDetailDTO find(@PathVariable final long id) throws Exception {
		return this.departamentService.findById(id);
	}

	@Override
	@PostMapping("/departaments")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Crear un nuevo departamento")
	public DepartamentDetailDTO create(@Validated @RequestBody final DepartamentCreateDTO departamentCreateDTO)
			throws Exception {
		return this.departamentService.save(departamentCreateDTO);
	}

	@Override
	@PutMapping("/departaments/{id}")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Actualizar un departamento por el id")
	public DepartamentDetailDTO update(@PathVariable final long id,
			@Validated @RequestBody final DepartamentUpdateDTO departamentUpdateDTO) throws Exception {
		return this.departamentService.update(id, departamentUpdateDTO);
	}

	@Override
	@DeleteMapping("/departaments/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
	@ApiOperation(value = "Eliminar un departamento por el id")
	public void deleted(@PathVariable final long id) throws Exception {
		this.departamentService.delete(id);
	}

}
