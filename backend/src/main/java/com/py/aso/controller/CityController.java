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

import com.py.aso.dto.CityDTO;
import com.py.aso.dto.create.CityCreateDTO;
import com.py.aso.dto.detail.CityDetailDTO;
import com.py.aso.dto.update.CityUpdateDTO;
import com.py.aso.service.CityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de ciudades del paraguay")
@RequestMapping("/api")
public class CityController implements BaseController<CityDTO, CityDetailDTO, CityCreateDTO, CityUpdateDTO> {

	@Autowired
	private CityService cityService;

	@Override
	@GetMapping("/cities")
	@ApiOperation(value = "Obtener todas las ciudades, permite paginacion")
	public Page<CityDTO> index(final Pageable pageable) {
		return this.cityService.findAll(pageable);
	}

	@Override
	@GetMapping("/cities/{id}")
	@ApiOperation(value = "Obtener una ciudad por el id")
	public CityDetailDTO find(@PathVariable final long id) throws Exception {
		return this.cityService.findById(id);
	}

	@Override
	@PostMapping("/cities")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Crear una nueva ciudad")
	public CityDetailDTO create(@Validated @RequestBody CityCreateDTO cityCreateDTO) throws Exception {
		return this.cityService.save(cityCreateDTO);
	}

	@Override
	@PutMapping("/cities/{id}")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Actualizar una ciudad por el id")
	public CityDetailDTO update(@PathVariable final long id, @Validated @RequestBody CityUpdateDTO cityUpdateDTO)
			throws Exception {
		return this.cityService.update(id, cityUpdateDTO);
	}

	@Override
	@DeleteMapping("/cities/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
	@ApiOperation(value = "Eliminar una ciudad por el id")
	public void deleted(@PathVariable final long id) throws Exception {
		this.cityService.delete(id);
	}

}
