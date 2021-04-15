package com.py.aso.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<DTO, DetailDTO, CreateDTO, UpdateDTO> {

	public Page<DTO> findAll(final Pageable pageable);

	public DetailDTO findById(final long id) throws Exception;

	public DetailDTO save(final CreateDTO dto) throws Exception;

	public DetailDTO update(final long id, final UpdateDTO dto) throws Exception;

	public void delete(final long id) throws Exception;
}
