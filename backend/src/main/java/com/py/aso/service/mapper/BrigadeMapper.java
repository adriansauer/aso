package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.dto.BrigadeDTO;
import com.py.aso.dto.create.BrigadeCreateDTO;
import com.py.aso.dto.create.UserCreateDTO;
import com.py.aso.dto.detail.BrigadeDetailDTO;
import com.py.aso.dto.update.BrigadeUpdateDTO;
import com.py.aso.dto.update.UserUpdateDTO;
import com.py.aso.entity.BrigadeEntity;
import com.py.aso.entity.CityEntity;
import com.py.aso.entity.DepartamentEntity;

@Component
public class BrigadeMapper
		implements BaseMapper<BrigadeEntity, BrigadeDTO, BrigadeDetailDTO, BrigadeCreateDTO, BrigadeUpdateDTO> {

	@Override
	public BrigadeDTO toDTO(BrigadeEntity entity) {
		BrigadeDTO dto = new BrigadeDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getUser().getName());
		dto.setUsercode(entity.getUser().getUsercode());
		dto.setDepartament(entity.getDepartament().getName());
		dto.setCity(entity.getCity().getName());
		dto.setCreation(entity.getCreation());
		dto.setNumberMember(entity.getNumberMember());
		dto.setImageId(entity.getImage().getId());
		return dto;
	}

	@Override
	public BrigadeDetailDTO toDetailDTO(BrigadeEntity entity) {
		BrigadeDetailDTO dto = new BrigadeDetailDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getUser().getName());
		dto.setUsercode(entity.getUser().getUsercode());
		dto.setAddress(entity.getAddress());
		dto.setPhone(entity.getPhone());
		dto.setDescription(entity.getDescription());
		dto.setEmail(entity.getUser().getEmail());
		dto.setNumberMember(entity.getNumberMember());
		dto.setCreation(entity.getCreation());
		dto.setCreatedAt(entity.getUser().getCreatedAt());
		dto.setUpdatedAt(entity.getUser().getUpdatedAt());
		dto.setImageId(entity.getImage().getId());
		dto.setDepartamentId(entity.getDepartament().getId());
		dto.setDepartament(entity.getDepartament().getName());
		dto.setCityId(entity.getCity().getId());
		dto.setCity(entity.getCity().getName());
		dto.setUserId(entity.getUser().getId());
		return dto;
	}

	@Override
	public BrigadeEntity toEntity(BrigadeDTO dto) {
		BrigadeEntity entity = new BrigadeEntity();
		entity.setId(dto.getId());
		return entity;
	}

	@Override
	public BrigadeEntity toCreateEntity(BrigadeCreateDTO dto) {
		BrigadeEntity entity = new BrigadeEntity();
		entity.setAddress(dto.getAddress());
		entity.setDescription(dto.getDescription());
		entity.setCreation(dto.getCreation());
		entity.setPhone(dto.getPhone());
		DepartamentEntity departamentEntity = new DepartamentEntity();
		departamentEntity.setId(dto.getDepartamentId());
		entity.setDepartament(departamentEntity);
		CityEntity cityEntity = new CityEntity();
		cityEntity.setId(dto.getCityId());
		entity.setCity(cityEntity);
		return entity;
	}

	@Override
	public BrigadeEntity toUpdateEntity(BrigadeUpdateDTO dto) {
		BrigadeEntity entity = new BrigadeEntity();
		entity.setAddress(dto.getAddress());
		entity.setDescription(dto.getDescription());
		entity.setCreation(dto.getCreation());
		entity.setPhone(dto.getPhone());
		entity.getCity().setId(dto.getCityId());
		entity.getDepartament().setId(dto.getDepartamentId());
		return entity;
	}

	public UserCreateDTO toUserCreateDTO(BrigadeCreateDTO dto) {
		UserCreateDTO userDTO = new UserCreateDTO();
		userDTO.setName(dto.getName());
		userDTO.setUsercode(dto.getUsercode());
		userDTO.setEmail(dto.getEmail());
		userDTO.setPassword(dto.getPassword());
		userDTO.setRepeatPassword(dto.getRepeatPassword());
		return userDTO;
	}

	public UserUpdateDTO toUserUpdateDTO(BrigadeUpdateDTO dto) {
		UserUpdateDTO userDTO = new UserUpdateDTO();
		userDTO.setName(dto.getName());
		userDTO.setUsercode(dto.getUsercode());
		userDTO.setEmail(dto.getEmail());
		return userDTO;
	}

}
