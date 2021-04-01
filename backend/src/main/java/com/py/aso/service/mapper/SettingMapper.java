package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.dto.SettingDTO;
import com.py.aso.entity.SettingEntity;

@Component
public class SettingMapper implements BaseMapper<SettingEntity, SettingDTO> {

	@Override
	public SettingEntity toEntity(SettingDTO dto) {
		return SettingEntity.builder()//
				.key(dto.getKey())//
				.value(dto.getValue())//
				.build();
	}

	@Override
	public SettingDTO toDTO(SettingEntity entity) {
		return SettingDTO.builder()//
				.key(entity.getKey())//
				.value(entity.getValue())//
				.build();
	}

}
