package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.dto.RoleDTO;
import com.py.aso.dto.create.RoleCreateDTO;
import com.py.aso.dto.detail.RoleDetailDTO;
import com.py.aso.dto.update.RoleUpdateDTO;
import com.py.aso.entity.RoleEntity;

@Component
public class RoleMapper implements BaseMapper<RoleEntity, RoleDTO, RoleDetailDTO, RoleCreateDTO, RoleUpdateDTO> {

	@Override
	public RoleDTO toDTO(RoleEntity entity) {
		RoleDTO dto = new RoleDTO();
		dto.setId(entity.getId());
		dto.setAuthority(entity.getAuthority());
		return dto;
	}

	@Override
	public RoleDetailDTO toDetailDTO(RoleEntity entity) {
		RoleDetailDTO dto = new RoleDetailDTO();
		dto.setId(entity.getId());
		dto.setAuthority(entity.getAuthority());
		return dto;
	}

	@Override
	public RoleEntity toEntity(RoleDTO dto) {
		RoleEntity entity = new RoleEntity();
		entity.setId(dto.getId());
		entity.setAuthority(dto.getAuthority());
		return entity;
	}

	@Override
	public RoleEntity toCreateEntity(RoleCreateDTO dto) {
		RoleEntity entity = new RoleEntity();
		entity.setAuthority(dto.getAuthority());
		return entity;
	}

	@Override
	public RoleEntity toUpdateEntity(RoleUpdateDTO dto) {
		RoleEntity entity = new RoleEntity();
		entity.setAuthority(dto.getAuthority());
		return entity;
	}

}
