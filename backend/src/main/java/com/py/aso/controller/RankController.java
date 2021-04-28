package com.py.aso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.py.aso.dto.RankDTO;
import com.py.aso.dto.create.RankCreateDTO;
import com.py.aso.dto.detail.RankDetailDTO;
import com.py.aso.dto.update.RankUpdateDTO;
import com.py.aso.service.RankService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de rangos")
@RequestMapping("/api")
public class RankController implements BaseController<RankDTO, RankDetailDTO, RankCreateDTO, RankUpdateDTO> {

	@Autowired
	private RankService rankService;

	@Override
	@GetMapping("/ranges")
	@ApiOperation(value = "Obtener todos los rangos, permite paginacion")
	public Page<RankDTO> index(final Pageable pageable) {
		return this.rankService.findAll(pageable);
	}

	@Override
	@GetMapping("/ranges/{id}")
	@ApiOperation(value = "Obtener un rango por el id")
	public RankDetailDTO find(final long id) throws Exception {
		return this.rankService.findById(id);
	}

	@Override
	@PostMapping("/ranges")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Crear un nuevo rango")
	public RankDetailDTO create(final RankCreateDTO rankCreateDTO) throws Exception {
		return this.rankService.save(rankCreateDTO);
	}

	@Override
	@PutMapping("/ranges/{id}")
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Actualizar un rango por el id")
	public RankDetailDTO update(final long id, final RankUpdateDTO rankUpdateDTO) throws Exception {
		return this.rankService.update(id, rankUpdateDTO);
	}

	@Override
	@DeleteMapping("/ranges/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_SUPERUSER') or hasRole('ROLE_BRIGADE')")
	@ApiOperation(value = "Eliminar un rango por el id")
	public void deleted(final long id) throws Exception {
		this.rankService.delete(id);
	}

}
