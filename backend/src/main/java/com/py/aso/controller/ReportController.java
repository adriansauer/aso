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
	
	@PostMapping("/reports/year-code")
	@ApiOperation(value = "Obtener las incidencias por el año y el codigo")
	public ReportDTO findByYearAndCode(@Validated @RequestBody final DashboardDTO dashboardDTO) throws Exception {
		return this.reportService.findByYearAndCode(dashboardDTO);
	}
	
	@PostMapping("/reports/year-user-code")
	@ApiOperation(value = "Obtener las incidencias por año el usuario y el codigo")
	public ReportDTO findByYearAndUserAndCode(@Validated @RequestBody final DashboardDTO dashboardDTO) throws Exception {
		return this.reportService.findByYearAndUserAndCode(dashboardDTO);
	}
	
	@PostMapping("/reports/year-user")
	@ApiOperation(value = "Obtener las incidencias por año y el usuario")
	public ReportDTO findByYearAndUser(@Validated @RequestBody final DashboardDTO dashboardDTO) throws Exception {
		return this.reportService.findByYearAndUser(dashboardDTO);
	}
	
	@PostMapping("/reports/code-user")
	@ApiOperation(value = "Obtener las incidencias por el codigo y el usuario")
	public ReportDTO findByCodeAndUser(@Validated @RequestBody final DashboardDTO dashboardDTO) throws Exception {
		return this.reportService.findByCodeAndUser(dashboardDTO);
	}

}
