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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.py.aso.dto.CountDTO;
import com.py.aso.dto.LikeDTO;
import com.py.aso.dto.create.LikeCreateDTO;
import com.py.aso.dto.detail.LikeDetailDTO;
import com.py.aso.dto.update.LikeUpdateDTO;
import com.py.aso.exception.NotAvailableException;
import com.py.aso.service.LikeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de likes")
@RequestMapping("/api")
public class LikeController implements BaseController<LikeDTO, LikeDetailDTO, LikeCreateDTO, LikeUpdateDTO> {

	@Autowired
	private LikeService likeService;

	@Override
	@GetMapping("/likes")
	@ApiOperation(value = "Obtener todos los likes, permite paginación")
	public Page<LikeDTO> index(final Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("/likes/{publicationId}")
	@ApiOperation(value = "Obtener detalles de un like del usuario a una publicación")
	public LikeDetailDTO find(@PathVariable final long publicationId) throws Exception {
		return this.likeService.findLikeByPublicationIdAndUserId(publicationId);
	}

	@GetMapping("/likes/publication/{publicationId}")
	@ApiOperation(value = "Obtener los likes de una publicación")
	public CountDTO count(@PathVariable final long publicationId) throws Exception {
		return this.likeService.countLikeByPublicationIdAndUserId(publicationId);
	}

	@Override
	@PostMapping("/likes")
	@ApiOperation(value = "Crear un like para una publicación por parte del usuario")
	public LikeDetailDTO create(@Validated @RequestBody final LikeCreateDTO dto) throws Exception {
		return this.likeService.save(dto);
	}

	@Override
	@PutMapping("/likes/{id}")
	@ApiOperation(value = "Actualizar un like por el id")
	public LikeDetailDTO update(@PathVariable final long id, @Validated @RequestBody final LikeUpdateDTO dto)
			throws Exception {
		throw new NotAvailableException();
	}

	@Override
	@DeleteMapping("/likes/{publicationId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Eliminar un like por el id de la publicación")
	public void deleted(@PathVariable final long publicationId) throws Exception {
		this.likeService.delete(publicationId);
	}

}
