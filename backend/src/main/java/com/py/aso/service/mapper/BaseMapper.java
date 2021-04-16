package com.py.aso.service.mapper;

public interface BaseMapper<Entity, DTO, DetailDTO, CreateDTO, UpdateDTO> {

	public DTO toDTO(final Entity entity);

	public DetailDTO toDetailDTO(final Entity entity);

	public Entity toEntity(final DTO dto);

	public Entity toCreateEntity(final CreateDTO dto);

	public Entity toUpdateEntity(final UpdateDTO dto);

}