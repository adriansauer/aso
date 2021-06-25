package com.py.aso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.py.aso.dto.DashboardDTO;
import com.py.aso.dto.ReportDTO;
import com.py.aso.service.ReportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador reportes")
@RequestMapping("/api")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@PostMapping("/reports")
	@ApiOperation(value = "Obtener detalles de un like del usuario a una publicación")
	public ReportDTO findByYear(@Validated @RequestBody final DashboardDTO dashboardDTO) throws Exception {
		return this.reportService.findByYear(dashboardDTO);
	}

}
