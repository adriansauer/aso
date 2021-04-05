package com.py.aso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.py.aso.dto.RoleDTO;
import com.py.aso.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de roles")
public class RoleController implements BaseController<RoleDTO> {

	@Autowired
	private RoleService roleService;

	@Override
	@GetMapping("/roles")
	@ApiOperation(value = "Obtener todos los roles, permite paginacion")
	public Page<RoleDTO> index(final Pageable pageable) {
		return this.roleService.findAll(pageable);
	}

	@Override
	@GetMapping("/roles/{id}")
	@ApiOperation(value = "Obtener un rol por el id")
	public RoleDTO find(@PathVariable final long id) throws Exception {
		return this.roleService.findById(id);
	}

	@Override
	@PostMapping("/roles")
	@ApiOperation(value = "Crear un nuevo rol")
	public RoleDTO create(@Validated @RequestBody final RoleDTO dto) throws Exception {
		return this.roleService.save(dto);
	}

	@Override
	@PutMapping("/roles/{id}")
	@ApiOperation(value = "Actualizar un rol por el id")
	public RoleDTO update(@PathVariable final long id, @Validated @RequestBody final RoleDTO dto) throws Exception {
		return this.roleService.update(id, dto);
	}

	@Override
	@DeleteMapping("/roles/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Eliminar un rol por el id")
	public void deleted(@PathVariable final long id) throws Exception {
		this.roleService.delete(id);
	}
}
