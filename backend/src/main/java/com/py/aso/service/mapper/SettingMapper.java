package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.dto.SettingDTO;
import com.py.aso.dto.create.SettingCreateDTO;
import com.py.aso.entity.SettingEntity;

@Component
public class SettingMapper implements BaseMapper<SettingEntity, SettingDTO, SettingDTO, SettingCreateDTO> {

	@Override
	public SettingEntity toEntity(SettingCreateDTO dto) {
		SettingEntity entity = new SettingEntity();
		entity.setKey(dto.getKey());
		entity.setValue(dto.getValue());
		return entity;
	}

	@Override
	public SettingDTO toDTO(SettingEntity entity) {
		SettingDTO dto = new SettingDTO();
		dto.setId(entity.getId());
		dto.setKey(entity.getKey());
		dto.setValue(entity.getValue());
		return dto;
	}

	@Override
	public SettingDTO toDetailDTO(SettingEntity entity) {
		SettingDTO dto = new SettingDTO();
		dto.setId(entity.getId());
		dto.setKey(entity.getKey());
		dto.setValue(entity.getValue());
		return dto;
	}

}
