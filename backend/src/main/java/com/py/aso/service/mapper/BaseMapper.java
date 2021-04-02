package com.py.aso.service.mapper;

public interface BaseMapper<Entity, DTO> {

	public Entity toEntity(final DTO dto);

	public DTO toDTO(final Entity entity);

}