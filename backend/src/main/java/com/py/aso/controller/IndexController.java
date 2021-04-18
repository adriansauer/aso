package com.py.aso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Controlador que responde la vista principal")
public class IndexController {

	@GetMapping(value = "/")
	@ApiOperation(value = "Responde la pagina inicial")
	public String index() {
		return "index.html";
	}
}
