package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.dto.RoleDTO;
import com.py.aso.entity.RoleEntity;

@Component
public class RoleMapper implements BaseMapper<RoleEntity, RoleDTO> {

	@Override
	public RoleEntity toEntity(RoleDTO dto) {
		RoleEntity entity = new RoleEntity();
		entity.setId(dto.getId());
		entity.setAuthority(dto.getAuthority());
		return entity;
	}

	@Override
	public RoleDTO toDTO(RoleEntity entity) {
		RoleDTO dto = new RoleDTO();
		dto.setId(entity.getId());
		dto.setAuthority(entity.getAuthority());
		return dto;
	}

}
