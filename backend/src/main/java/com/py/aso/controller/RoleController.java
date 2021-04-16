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

import com.py.aso.dto.RoleDTO;
import com.py.aso.dto.create.RoleCreateDTO;
import com.py.aso.dto.detail.RoleDetailDTO;
import com.py.aso.dto.update.RoleUpdateDTO;
import com.py.aso.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de roles")
@RequestMapping("/api")
public class RoleController implements BaseController<RoleDTO, RoleDetailDTO, RoleCreateDTO, RoleUpdateDTO> {

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
	public RoleDetailDTO find(@PathVariable final long id) throws Exception {
		return this.roleService.findById(id);
	}

	@Override
	@PostMapping("/roles")
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
	@ApiOperation(value = "Crear un nuevo rol")
	public RoleDetailDTO create(@Validated @RequestBody final RoleCreateDTO roleCreateDTO) throws Exception {
		return this.roleService.save(roleCreateDTO);
	}

	@Override
	@PutMapping("/roles/{id}")
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
	@ApiOperation(value = "Actualizar un rol por el id")
	public RoleDetailDTO update(@PathVariable final long id, @Validated @RequestBody final RoleUpdateDTO roleUpdateDTO)
			throws Exception {
		return this.roleService.update(id, roleUpdateDTO);
	}

	@Override
	@DeleteMapping("/roles/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
	@ApiOperation(value = "Eliminar un rol por el id")
	public void deleted(@PathVariable final long id) throws Exception {
		this.roleService.delete(id);
	}
}
