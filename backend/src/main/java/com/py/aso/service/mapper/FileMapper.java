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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileDetailDTO toDetailDTO(FileEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileEntity toEntity(FileDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileEntity toCreateEntity(FileCreateDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileEntity toUpdateEntity(FileUpdateDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
}
