package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.dto.RankDTO;
import com.py.aso.dto.create.RankCreateDTO;
import com.py.aso.dto.detail.RankDetailDTO;
import com.py.aso.dto.update.RankUpdateDTO;
import com.py.aso.entity.ImageEntity;
import com.py.aso.entity.RankEntity;

@Component
public class RankMapper implements BaseMapper<RankEntity, RankDTO, RankDetailDTO, RankCreateDTO, RankUpdateDTO> {

	@Override
	public RankDTO toDTO(final RankEntity entity) {
		RankDTO dto = new RankDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setImageId(entity.getImage().getId());
		return dto;
	}

	@Override
	public RankDetailDTO toDetailDTO(final RankEntity entity) {
		RankDetailDTO dto = new RankDetailDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setDescription(entity.getDescription());
		dto.setImageId(entity.getImage().getId());
		return dto;
	}

	@Override
	public RankEntity toEntity(final RankDTO dto) {
		RankEntity entity = new RankEntity();
		entity.setId(dto.getId());
		entity.setTitle(dto.getTitle());
		ImageEntity imageEntity = new ImageEntity();
		imageEntity.setId(dto.getImageId());
		entity.setImage(imageEntity);
		return entity;
	}

	public RankEntity toEntity(final RankDetailDTO dto) {
		RankEntity entity = new RankEntity();
		entity.setId(dto.getId());
		entity.setTitle(dto.getTitle());
		ImageEntity imageEntity = new ImageEntity();
		imageEntity.setId(dto.getImageId());
		entity.setImage(imageEntity);
		return entity;
	}

	@Override
	public RankEntity toCreateEntity(final RankCreateDTO dto) {
		RankEntity entity = new RankEntity();
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		ImageEntity imageEntity = new ImageEntity();
		entity.setImage(imageEntity);
		return entity;
	}

	@Override
	public RankEntity toUpdateEntity(final RankUpdateDTO dto) {
		RankEntity entity = new RankEntity();
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		ImageEntity imageEntity = new ImageEntity();
		entity.setImage(imageEntity);
		return entity;
	}

}
