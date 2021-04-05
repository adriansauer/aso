package com.py.aso.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface BaseController<DTO, DetailDTO, CreateDTO> {

	public Page<DTO> index(final Pageable pageable);

	public DetailDTO find(@PathVariable final long id) throws Exception;

	public DetailDTO create(@Validated @RequestBody final CreateDTO dto) throws Exception;

	public DetailDTO update(@PathVariable final long id, @Validated @RequestBody final CreateDTO dto) throws Exception;

	public void deleted(@PathVariable final long id) throws Exception;
}
