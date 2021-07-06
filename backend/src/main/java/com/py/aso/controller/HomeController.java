package com.py.aso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Controlador que responde la vista principal")
public class HomeController {

	@RequestMapping(value = "/home")
	@ApiOperation(value = "Responde la pagina inicial")
	public String home() {
		return "index.html";
	}
}
