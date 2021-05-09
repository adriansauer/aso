package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.entity.PublicationEntity;
import com.py.aso.dto.PublicationDTO;
import com.py.aso.dto.detail.PublicationDetailDTO;
import com.py.aso.dto.create.PublicationCreateDTO;
import com.py.aso.dto.update.PublicationUpdateDTO;

@Component
public class PublicationMapper implements BaseMapper<PublicationEntity, PublicationDTO, PublicationDetailDTO, PublicationCreateDTO, PublicationUpdateDTO> {

	@Override
	public PublicationDTO toDTO(PublicationEntity entity) {
		PublicationDTO dto = new PublicationDTO();
		dto.setId(entity.getId());
		dto.setCreateAt(entity.getCreate_at());
		dto.setUpdateAt(entity.getUpdate_at());
		dto.setBody(entity.getBody());
		dto.setDestination(entity.getDestination());
		dto.setUserId(entity.getUser().getId());
		return dto;
	}

	@Override
	public PublicationDetailDTO toDetailDTO(PublicationEntity entity) {
		PublicationDetailDTO dto = new PublicationDetailDTO();
		dto.setBody(entity.getBody());
		dto.setCreateAt(entity.getCreate_at());
		dto.setDestination(entity.getDestination());
		dto.setId(entity.getId());
		dto.setUpdateAt(entity.getUpdate_at());
		dto.setUserId(entity.getUser().getId());
		return dto;
	}

	@Override
	public PublicationEntity toEntity(PublicationDTO dto) {
		PublicationEntity entity = new PublicationEntity();
		entity.setBody(dto.getBody());
		entity.setCreate_at(dto.getCreateAt());
		entity.setDestination(dto.getDestination());
		entity.setId(dto.getId());
		entity.setUpdate_at(dto.getUpdateAt());
		return entity;
	}

	@Override
	public PublicationEntity toCreateEntity(PublicationCreateDTO dto) {
		PublicationEntity entity = new PublicationEntity();
		entity.setBody(dto.getBody());
		entity.setDestination(dto.getDestination());
		return entity;
	}

	@Override
	public PublicationEntity toUpdateEntity(PublicationUpdateDTO dto) {
		return null;
	}
}
