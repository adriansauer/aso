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
	public DepartamentDTO toDTO(DepartamentEntity entity) {
		DepartamentDTO dto = new DepartamentDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public DepartamentDetailDTO toDetailDTO(DepartamentEntity entity) {
		DepartamentDetailDTO dto = new DepartamentDetailDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public DepartamentEntity toEntity(DepartamentDTO dto) {
		DepartamentEntity entity = new DepartamentEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public DepartamentEntity toCreateEntity(DepartamentCreateDTO dto) {
		DepartamentEntity entity = new DepartamentEntity();
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public DepartamentEntity toUpdateEntity(DepartamentUpdateDTO dto) {
		DepartamentEntity entity = new DepartamentEntity();
		entity.setName(dto.getName());
		return entity;
	}

}
