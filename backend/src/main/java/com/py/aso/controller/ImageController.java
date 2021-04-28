package com.py.aso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.py.aso.dto.ImageDTO;
import com.py.aso.dto.create.ImageCreateDTO;
import com.py.aso.dto.detail.ImageDetailDTO;
import com.py.aso.dto.update.ImageUpdateDTO;
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

	@GetMapping("/images/files/{id}")
	@ApiOperation(value = "Obtener una imagen por el id")
	public ResponseEntity<Resource> findFileById(@PathVariable final long id) throws Exception {
		final Resource file = this.imageService.findFileById(id);
		return ResponseEntity.ok()//
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")//
				.body(file);
	}

	@Override
	@PostMapping("/images")
	@ApiOperation(value = "Crear una imagen por defecto")
	public ImageDetailDTO create(@Validated @RequestBody final ImageCreateDTO imageCreateDTO) throws Exception {
		return this.imageService.save(imageCreateDTO);
	}

	@PostMapping(path = "/images/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiOperation(value = "Crear una nueva imagen")
	public ImageDetailDTO createFile(@RequestParam("file") final MultipartFile file,
			@RequestParam("name") final String name) throws Exception {
		return this.imageService.saveFile(file, name);
	}

	@Override
	@PutMapping("/images/{id}")
	@ApiOperation(value = "Actualizar los datos de una imagen por el id")
	public ImageDetailDTO update(@PathVariable final long id,
			@Validated @RequestBody final ImageUpdateDTO imageUpdateDTO) throws Exception {
		return this.imageService.update(id, imageUpdateDTO);
	}

	@PutMapping("/images/files/{id}")
	@ApiOperation(value = "Actualizar una imagen por el id")
	public ImageDetailDTO updateFile(@RequestParam("file") final MultipartFile file, @PathVariable final long id)
			throws Exception {
		return this.imageService.updateFileById(file, id);
	}

	@Override
	@DeleteMapping("/images/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Eliminar una imagen por el id")
	public void deleted(@PathVariable final long id) throws Exception {
		this.imageService.delete(id);
	}

}
