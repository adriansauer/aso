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

import com.py.aso.dto.ImageDTO;
import com.py.aso.dto.create.ImageCreateDTO;
import com.py.aso.dto.detail.ImageDetailDTO;
import com.py.aso.dto.update.ImageUpdateDTO;
import com.py.aso.exception.NotAvailableException;
import com.py.aso.service.ImageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de images")
@RequestMapping("/api")
public class ImageController implements BaseController<ImageDTO, ImageDetailDTO, ImageCreateDTO, ImageUpdateDTO> {

	@Autowired
	private ImageService imageService;

	@Override
	@GetMapping("/images")
	@ApiOperation(value = "Obtener datos de todas las imagenes, permite paginación")
	public Page<ImageDTO> index(final Pageable pageable) {
		return this.imageService.findAll(pageable);
	}

	@Override
	@GetMapping("/images/{id}")
	@ApiOperation(value = "Obtener los datos de la imagen por el id")
	public ImageDetailDTO find(@PathVariable final long id) throws Exception {
		return this.imageService.findById(id);
	}

	@Override
	@PostMapping("/images")
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
	@ApiOperation(value = "Crear una imagen por defecto")
	public ImageDetailDTO create(@Validated @RequestBody final ImageCreateDTO imageCreateDTO) throws Exception {
		throw new NotAvailableException();
	}

	@Override
	@PutMapping("/images/{id}")
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
	@ApiOperation(value = "Actualizar los datos de una imagen por el id")
	public ImageDetailDTO update(@PathVariable final long id,
			@Validated @RequestBody final ImageUpdateDTO imageUpdateDTO) throws Exception {
		throw new NotAvailableException();
	}

	@Override
	@DeleteMapping("/images/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_SUPERUSER')")
	@ApiOperation(value = "Eliminar una imagen por el id")
	public void deleted(@PathVariable final long id) throws Exception {
		throw new NotAvailableException();
	}

}
