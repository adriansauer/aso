package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.dto.LikeDTO;
import com.py.aso.dto.create.LikeCreateDTO;
import com.py.aso.dto.detail.LikeDetailDTO;
import com.py.aso.dto.update.LikeUpdateDTO;
import com.py.aso.entity.LikeEntity;

@Component
public class LikeMapper implements BaseMapper<LikeEntity, LikeDTO, LikeDetailDTO, LikeCreateDTO, LikeUpdateDTO> {

	@Override
	public LikeDTO toDTO(LikeEntity entity) {
		LikeDTO dto = new LikeDTO();
		dto.setId(entity.getId());
		dto.setPublicationId(entity.getPublication().getId());
		dto.setUserId(entity.getUser().getId());
		dto.setDate(entity.getDate());
		return dto;
	}

	@Override
	public LikeDetailDTO toDetailDTO(LikeEntity entity) {
		LikeDetailDTO dto = new LikeDetailDTO();
		dto.setId(entity.getId());
		dto.setPublicationId(entity.getPublication().getId());
		dto.setUserId(entity.getUser().getId());
		dto.setDate(entity.getDate());
		return dto;
	}

	@Override
	public LikeEntity toEntity(LikeDTO dto) {
		LikeEntity entity = new LikeEntity();
		return entity;
	}

	@Override
	public LikeEntity toCreateEntity(LikeCreateDTO dto) {
		LikeEntity entity = new LikeEntity();
		return entity;
	}

	@Override
	public LikeEntity toUpdateEntity(LikeUpdateDTO dto) {
		LikeEntity entity = new LikeEntity();
		return entity;
	}

}
