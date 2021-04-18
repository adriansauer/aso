package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.dto.BrigadeDTO;
import com.py.aso.dto.create.BrigadeCreateDTO;
import com.py.aso.dto.detail.BrigadeDetailDTO;
import com.py.aso.dto.update.BrigadeUpdateDTO;
import com.py.aso.entity.BrigadeEntity;

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
		return entity;
	}

	@Override
	public BrigadeEntity toUpdateEntity(BrigadeUpdateDTO dto) {
		BrigadeEntity entity = new BrigadeEntity();
		entity.setAddress(dto.getAddress());
		entity.setDescription(dto.getDescription());
		entity.setCreation(dto.getCreation());
		entity.setPhone(dto.getPhone());
		return entity;
	}

}
