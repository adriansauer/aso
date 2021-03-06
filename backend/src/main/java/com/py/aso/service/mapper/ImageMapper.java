package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.dto.ImageDTO;
import com.py.aso.dto.create.ImageCreateDTO;
import com.py.aso.dto.detail.ImageDetailDTO;
import com.py.aso.dto.update.ImageUpdateDTO;
import com.py.aso.entity.ImageEntity;

@Component
public class ImageMapper implements BaseMapper<ImageEntity, ImageDTO, ImageDetailDTO, ImageCreateDTO, ImageUpdateDTO> {

	@Override
	public ImageDTO toDTO(final ImageEntity entity) {
		ImageDTO dto = new ImageDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public ImageDetailDTO toDetailDTO(final ImageEntity entity) {
		ImageDetailDTO dto = new ImageDetailDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setFile(entity.getFile());
		return dto;
	}

	@Override
	public ImageEntity toEntity(final ImageDTO dto) {
		ImageEntity entity = new ImageEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public ImageEntity toCreateEntity(final ImageCreateDTO dto) {
		ImageEntity entity = new ImageEntity();
		entity.setName(dto.getName());
		entity.setFile(dto.getFile());
		return entity;
	}

	@Override
	public ImageEntity toUpdateEntity(final ImageUpdateDTO dto) {
		ImageEntity entity = new ImageEntity();
		entity.setName(dto.getName());
		entity.setFile(dto.getFile());
		return entity;
	}

}
