package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.dto.SettingDTO;
import com.py.aso.dto.create.SettingCreateDTO;
import com.py.aso.dto.detail.SettingDetailDTO;
import com.py.aso.dto.update.SettingUpdateDTO;
import com.py.aso.entity.SettingEntity;

@Component
public class SettingMapper
		implements BaseMapper<SettingEntity, SettingDTO, SettingDetailDTO, SettingCreateDTO, SettingUpdateDTO> {

	@Override
	public SettingDTO toDTO(SettingEntity entity) {
		SettingDTO dto = new SettingDTO();
		dto.setId(entity.getId());
		dto.setKey(entity.getKey());
		dto.setValue(entity.getValue());
		return dto;
	}

	@Override
	public SettingDetailDTO toDetailDTO(SettingEntity entity) {
		SettingDetailDTO dto = new SettingDetailDTO();
		dto.setId(entity.getId());
		dto.setKey(entity.getKey());
		dto.setValue(entity.getValue());
		return dto;
	}

	@Override
	public SettingEntity toEntity(SettingDTO dto) {
		SettingEntity entity = new SettingEntity();
		entity.setId(dto.getId());
		entity.setKey(dto.getKey());
		entity.setValue(dto.getValue());
		return entity;
	}

	@Override
	public SettingEntity toCreateEntity(SettingCreateDTO dto) {
		SettingEntity entity = new SettingEntity();
		entity.setKey(dto.getKey());
		entity.setValue(dto.getValue());
		return entity;
	}

	@Override
	public SettingEntity toUpdateEntity(SettingUpdateDTO dto) {
		SettingEntity entity = new SettingEntity();
		entity.setKey(dto.getKey());
		entity.setValue(dto.getValue());
		return entity;
	}

}
