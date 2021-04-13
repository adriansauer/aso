package com.py.aso.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.py.aso.dto.LoginDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador para optener el token")
public class LoginController {

	@PostMapping("/login")
	@ApiOperation(value = "Obtener un token a partir de un codigo de usuario y una contraseña")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void index(@Validated @RequestBody final LoginDTO loginDTO) {
	}

	@GetMapping("/istokenvalid")
	@ApiOperation(value = "Verificar si el token es valido")
	public void istokenvalid() throws Exception {
	}

}
