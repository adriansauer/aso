package com.py.aso.service.mapper;

public interface BaseMapper<Entity, DTO, DetailDTO, CreateDTO> {

	public Entity toEntity(final CreateDTO dto);

	public DTO toDTO(final Entity entity);

	public DetailDTO toDetailDTO(final Entity entity);

}