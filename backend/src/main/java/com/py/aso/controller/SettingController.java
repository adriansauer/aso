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

import com.py.aso.dto.SettingDTO;
import com.py.aso.service.SettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de configuraciones")
public class SettingController implements BaseController<SettingDTO> {

	@Autowired
	private SettingService settingService;

	@Override
	@GetMapping("/settings")
	@ApiOperation(value = "Obtener toda las configuraciones, permite paginacion")
	public Page<SettingDTO> index(final Pageable pageable) {
		return settingService.findAll(pageable);
	}

	@Override
	@GetMapping("/settings/{id}")
	@ApiOperation(value = "Obtener una configuracion por el id")
	public SettingDTO find(@PathVariable final long id) throws Exception {
		return settingService.findById(id);
	}

	@GetMapping("/settings/key/{key}")
	@ApiOperation(value = "Obtener una configuracion por el key")
	public SettingDTO find(@PathVariable final String key) throws Exception {
		return settingService.findByKey(key);
	}

	@Override
	@PostMapping("/settings")
	@ApiOperation(value = "Crear una nueva configuracion")
	public SettingDTO create(@Validated @RequestBody final SettingDTO dto) throws Exception {
		return settingService.save(dto);
	}

	@Override
	@PutMapping("/settings/{id}")
	@ApiOperation(value = "Actualizar una configuracion por el id")
	public SettingDTO update(@PathVariable final long id, @Validated @RequestBody final SettingDTO dto)
			throws Exception {
		return settingService.update(id, dto);
	}

	@PutMapping("/settings/key/{key}")
	@ApiOperation(value = "Actualizar una configuracion por el key")
	public SettingDTO updateByKey(@PathVariable final String key, @Validated @RequestBody final SettingDTO dto)
			throws Exception {
		return settingService.updateByKey(key, dto);
	}

	@Override
	@DeleteMapping("/settings/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Eliminar una configuracion por el id")
	public void deleted(@PathVariable final long id) throws Exception {
		settingService.delete(id);
	}

}
