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

import com.py.aso.dto.UserDTO;
import com.py.aso.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de los usuarios activos")
public class UserController implements BaseController<UserDTO> {

	@Autowired
	private UserService userService;

	@Override
	@GetMapping("/users")
	@ApiOperation(value = "Obtener todos los usuarios activos, permite paginacion")
	public Page<UserDTO> index(final Pageable pageable) {
		return this.userService.findAll(pageable);
	}

	@Override
	@GetMapping("/users/{id}")
	@ApiOperation(value = "Obtener un usuario activo por el id")
	public UserDTO find(@PathVariable final long id) throws Exception {
		return this.userService.findById(id);
	}

	@Override
	@PostMapping("/users")
	@ApiOperation(value = "Crear un nuevo usuario")
	public UserDTO create(@Validated @RequestBody final UserDTO dto) throws Exception {
		return this.userService.save(dto);
	}

	@Override
	@PutMapping("/users/{id}")
	@ApiOperation(value = "Actualizar un usuario activo")
	public UserDTO update(@PathVariable final long id, @Validated @RequestBody final UserDTO dto) throws Exception {
		return this.userService.update(id, dto);
	}

	@Override
	@DeleteMapping("/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Eliminar un usuario activo")
	public void deleted(@PathVariable final long id) throws Exception {
		this.userService.delete(id);
	}

}
