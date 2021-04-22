package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.dto.DepartamentDTO;
import com.py.aso.dto.create.DepartamentCreateDTO;
import com.py.aso.dto.detail.DepartamentDetailDTO;
import com.py.aso.dto.update.DepartamentUpdateDTO;
import com.py.aso.entity.DepartamentEntity;

@Component
public class DepartamentMapper implements
		BaseMapper<DepartamentEntity, DepartamentDTO, DepartamentDetailDTO, DepartamentCreateDTO, DepartamentUpdateDTO> {

	@Override
	public DepartamentDTO toDTO(final DepartamentEntity entity) {
		DepartamentDTO dto = new DepartamentDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public DepartamentDetailDTO toDetailDTO(final DepartamentEntity entity) {
		DepartamentDetailDTO dto = new DepartamentDetailDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public DepartamentEntity toEntity(final DepartamentDTO dto) {
		DepartamentEntity entity = new DepartamentEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}

	public DepartamentEntity toEntity(final DepartamentDetailDTO dto) {
		DepartamentEntity entity = new DepartamentEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public DepartamentEntity toCreateEntity(final DepartamentCreateDTO dto) {
		DepartamentEntity entity = new DepartamentEntity();
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public DepartamentEntity toUpdateEntity(final DepartamentUpdateDTO dto) {
		DepartamentEntity entity = new DepartamentEntity();
		entity.setName(dto.getName());
		return entity;
	}

}
