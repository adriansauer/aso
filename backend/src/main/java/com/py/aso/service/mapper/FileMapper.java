package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.dto.FileDTO;
import com.py.aso.dto.create.FileCreateDTO;
import com.py.aso.dto.detail.FileDetailDTO;
import com.py.aso.dto.update.FileUpdateDTO;
import com.py.aso.entity.FileEntity;

@Component
public class FileMapper implements BaseMapper<FileEntity, FileDTO, FileDetailDTO, FileCreateDTO, FileUpdateDTO> {

	@Override
	public FileDTO toDTO(FileEntity entity) {
		FileDTO dto = new FileDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPublicationId(entity.getPublication().getId());
		return dto;
	}

	@Override
	public FileDetailDTO toDetailDTO(FileEntity entity) {
		FileDetailDTO dto = new FileDetailDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPublicationId(entity.getPublication().getId());
		return dto;
	}

	@Override
	public FileEntity toEntity(FileDTO dto) {
		FileEntity entity = new FileEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public FileEntity toCreateEntity(FileCreateDTO dto) {
		FileEntity entity = new FileEntity();
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public FileEntity toUpdateEntity(FileUpdateDTO dto) {
		return null;
	}
}
