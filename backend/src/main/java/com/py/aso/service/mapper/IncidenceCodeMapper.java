package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.dto.IncidenceCodeDTO;
import com.py.aso.dto.create.IncidenceCodeCreateDTO;
import com.py.aso.dto.detail.IncidenceCodeDetailDTO;
import com.py.aso.dto.update.IncidenceCodeUpdateDTO;
import com.py.aso.entity.IncidenceCodeEntity;

@Component
public class IncidenceCodeMapper implements BaseMapper<IncidenceCodeEntity, IncidenceCodeDTO, IncidenceCodeDetailDTO, IncidenceCodeCreateDTO, IncidenceCodeUpdateDTO> {

	@Override
	public IncidenceCodeDTO toDTO(IncidenceCodeEntity entity) {
		IncidenceCodeDTO dto = new IncidenceCodeDTO();
		dto.setId(entity.getId());
		dto.setCode(entity.getCode());
		dto.setDescription(entity.getDescription());
		return dto;
	}

	@Override
	public IncidenceCodeDetailDTO toDetailDTO(IncidenceCodeEntity entity) {
		IncidenceCodeDetailDTO dto = new IncidenceCodeDetailDTO();
		dto.setId(entity.getId());
		dto.setCode(entity.getCode());
		dto.setDescription(entity.getDescription());
		return dto;
	}

	@Override
	public IncidenceCodeEntity toEntity(IncidenceCodeDTO dto) {
		IncidenceCodeEntity entity = new IncidenceCodeEntity();
		entity.setCode(dto.getCode());
		entity.setDescription(dto.getDescription());
		return entity;
	}

	@Override
	public IncidenceCodeEntity toCreateEntity(IncidenceCodeCreateDTO dto) {
		IncidenceCodeEntity entity = new IncidenceCodeEntity();
		entity.setCode(dto.getCode());
		entity.setDescription(dto.getDescription());
		return entity;
	}

	@Override
	public IncidenceCodeEntity toUpdateEntity(IncidenceCodeUpdateDTO dto) {
		IncidenceCodeEntity entity = new IncidenceCodeEntity();
		entity.setCode(dto.getCode());
		entity.setDescription(dto.getDescription());
		return entity;
	}

}
