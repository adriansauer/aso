package com.py.aso.service.mapper;

import com.py.aso.dto.CityDTO;
import com.py.aso.dto.create.CityCreateDTO;
import com.py.aso.dto.detail.CityDetailDTO;
import com.py.aso.dto.update.CityUpdateDTO;
import com.py.aso.entity.CityEntity;

public class CityMapper implements BaseMapper<CityEntity, CityDTO, CityDetailDTO, CityCreateDTO, CityUpdateDTO> {

	@Override
	public CityDTO toDTO(CityEntity entity) {
		CityDTO dto = new CityDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public CityDetailDTO toDetailDTO(CityEntity entity) {
		CityDetailDTO dto = new CityDetailDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public CityEntity toEntity(CityDTO dto) {
		CityEntity entity = new CityEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public CityEntity toCreateEntity(CityCreateDTO dto) {
		CityEntity entity = new CityEntity();
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public CityEntity toUpdateEntity(CityUpdateDTO dto) {
		CityEntity entity = new CityEntity();
		entity.setName(dto.getName());
		return entity;
	}

}
