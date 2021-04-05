package com.py.aso.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.py.aso.dto.RoleDTO;
import com.py.aso.dto.UserDTO;
import com.py.aso.dto.create.UserCreateDTO;
import com.py.aso.dto.detail.UserDetailDTO;
import com.py.aso.entity.RoleEntity;
import com.py.aso.entity.UserEntity;

@Component
public class UserMapper implements BaseMapper<UserEntity, UserDTO, UserDetailDTO, UserCreateDTO> {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public UserEntity toEntity(UserCreateDTO dto) {
		UserEntity entity = new UserEntity();
		entity.setName(dto.getName());
		entity.setLastname(dto.getLastname());
		entity.setEmail(dto.getEmail());
		entity.setUsercode(dto.getUsercode());
		List<RoleEntity> roles = dto.getRoles().stream().map(this.roleMapper::toEntity).collect(Collectors.toList());
		entity.setRoles(roles);
		return entity;
	}

	@Override
	public UserDTO toDTO(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setLastname(entity.getLastname());
		dto.setUsercode(entity.getUsercode());
		return dto;
	}

	@Override
	public UserDetailDTO toDetailDTO(UserEntity entity) {
		UserDetailDTO dto = new UserDetailDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setLastname(entity.getLastname());
		dto.setEmail(entity.getEmail());
		dto.setUsercode(entity.getUsercode());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		List<RoleDTO> roles = entity.getRoles()//
				.stream().map(this.roleMapper::toDTO).collect(Collectors.toList());
		dto.setRoles(roles);
		return dto;
	}

}
