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
	public RoleDTO toDTO(final RoleEntity entity) {
		RoleDTO dto = new RoleDTO();
		dto.setId(entity.getId());
		dto.setAuthority(entity.getAuthority());
		return dto;
	}

	@Override
	public RoleDetailDTO toDetailDTO(final RoleEntity entity) {
		RoleDetailDTO dto = new RoleDetailDTO();
		dto.setId(entity.getId());
		dto.setAuthority(entity.getAuthority());
		return dto;
	}

	@Override
	public RoleEntity toEntity(final RoleDTO dto) {
		RoleEntity entity = new RoleEntity();
		entity.setId(dto.getId());
		entity.setAuthority(dto.getAuthority());
		return entity;
	}

	@Override
	public RoleEntity toCreateEntity(final RoleCreateDTO dto) {
		RoleEntity entity = new RoleEntity();
		entity.setAuthority(dto.getAuthority());
		return entity;
	}

	@Override
	public RoleEntity toUpdateEntity(final RoleUpdateDTO dto) {
		RoleEntity entity = new RoleEntity();
		entity.setAuthority(dto.getAuthority());
		return entity;
	}

}
