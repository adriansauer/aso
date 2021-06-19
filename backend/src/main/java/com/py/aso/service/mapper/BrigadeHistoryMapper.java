package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.dto.BrigadeHistoryDTO;
import com.py.aso.dto.create.BrigadeHistoryCreateDTO;
import com.py.aso.dto.detail.BrigadeHistoryDetailDTO;
import com.py.aso.dto.update.BrigadeHistoryUpdateDTO;
import com.py.aso.entity.BrigadeHistoryEntity;

@Component
public class BrigadeHistoryMapper implements BaseMapper<BrigadeHistoryEntity, BrigadeHistoryDTO, BrigadeHistoryDetailDTO, BrigadeHistoryCreateDTO, BrigadeHistoryUpdateDTO> {

	@Override
	public BrigadeHistoryDTO toDTO(final BrigadeHistoryEntity entity) {
		BrigadeHistoryDTO dto = new BrigadeHistoryDTO();
		dto.setId(entity.getId());
		dto.setText(entity.getText());
		dto.setBrigadeId(entity.getBrigade().getId());
		return dto;
	}

	@Override
	public BrigadeHistoryDetailDTO toDetailDTO(final BrigadeHistoryEntity entity) {
		BrigadeHistoryDetailDTO dto = new BrigadeHistoryDetailDTO();
		dto.setId(entity.getId());
		dto.setText(entity.getText());
		dto.setBrigadeId(entity.getBrigade().getId());
		return dto;
	}

	@Override
	public BrigadeHistoryEntity toEntity(final BrigadeHistoryDTO dto) {
		BrigadeHistoryEntity entity = new BrigadeHistoryEntity();
		entity.setText(dto.getText());
		return entity;
	}

	@Override
	public BrigadeHistoryEntity toCreateEntity(final BrigadeHistoryCreateDTO dto) {
		BrigadeHistoryEntity entity = new BrigadeHistoryEntity();
		entity.setText(dto.getText());
		return entity;
	}

	@Override
	public BrigadeHistoryEntity toUpdateEntity(final BrigadeHistoryUpdateDTO dto) {
		BrigadeHistoryEntity entity = new BrigadeHistoryEntity();
		entity.setText(dto.getText());
		return entity;
	}

}
