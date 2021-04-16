package com.py.aso.service.mapper;

import com.py.aso.dto.FiremanDTO;
import com.py.aso.dto.create.FiremanCreateDTO;
import com.py.aso.dto.detail.FiremanDetailDTO;
import com.py.aso.dto.update.FiremanUpdateDTO;
import com.py.aso.entity.BrigadeEntity;
import com.py.aso.entity.CityEntity;
import com.py.aso.entity.DepartamentEntity;
import com.py.aso.entity.FiremanEntity;
import com.py.aso.entity.RankEntity;

public class FiremanMapper
		implements BaseMapper<FiremanEntity, FiremanDTO, FiremanDetailDTO, FiremanCreateDTO, FiremanUpdateDTO> {

	@Override
	public FiremanDTO toDTO(FiremanEntity entity) {
		FiremanDTO dto = new FiremanDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getUser().getName());
		dto.setLastname(entity.getUser().getLastname());
		dto.setUsercode(entity.getUser().getUsercode());
		dto.setCity(entity.getCity().getName());
		dto.setRankTitle(entity.getRank().getTitle());
		dto.setBrigadeName(entity.getBrigade().getUser().getName());
		dto.setImageId(entity.getImage().getId());
		dto.setBrigadeId(entity.getBrigade().getId());
		dto.setRankId(entity.getRank().getId());
		return dto;
	}

	@Override
	public FiremanDetailDTO toDetailDTO(FiremanEntity entity) {
		FiremanDetailDTO dto = new FiremanDetailDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getUser().getName());
		dto.setLastname(entity.getUser().getLastname());
		dto.setUsercode(entity.getUser().getUsercode());
		dto.setCi(entity.getCi());
		dto.setPhone(entity.getPhone());
		dto.setDescription(entity.getDescription());
		dto.setEmail(entity.getUser().getEmail());
		dto.setAdmission(entity.getAdmission());
		dto.setBirthday(entity.getBirthday());
		dto.setCreatedAt(entity.getUser().getCreatedAt());
		dto.setUpdatedAt(entity.getUser().getUpdatedAt());
		dto.setAddress(entity.getAddress());
		dto.setCityId(entity.getCity().getId());
		dto.setCity(entity.getCity().getName());
		dto.setDepartamentId(entity.getDepartament().getId());
		dto.setDepartament(entity.getDepartament().getName());
		dto.setRankTitle(entity.getRank().getTitle());
		dto.setRankId(entity.getRank().getId());
		dto.setBrigadeName(entity.getBrigade().getUser().getName());
		dto.setBrigadeId(entity.getBrigade().getId());
		dto.setImageId(entity.getImage().getId());
		dto.setUserId(entity.getUser().getId());
		return dto;
	}

	@Override
	public FiremanEntity toEntity(FiremanDTO dto) {
		FiremanEntity entity = new FiremanEntity();
		entity.setId(dto.getId());
		return entity;
	}

	@Override
	public FiremanEntity toCreateEntity(FiremanCreateDTO dto) {
		FiremanEntity entity = new FiremanEntity();
		entity.setAddress(dto.getAddress());
		entity.setDescription(dto.getDescription());
		entity.setBirthday(dto.getBirthday());
		entity.setAdmission(dto.getAdmission());
		entity.setPhone(dto.getPhone());
		entity.setCi(dto.getCi());
		BrigadeEntity brigateEntity = new BrigadeEntity();
		brigateEntity.setId(dto.getBrigadeId());
		entity.setBrigade(brigateEntity);
		DepartamentEntity departamentEntity = new DepartamentEntity();
		departamentEntity.setId(dto.getDepartamentId());
		entity.setDepartament(departamentEntity);
		CityEntity cityEntity = new CityEntity();
		cityEntity.setId(dto.getCityId());
		entity.setCity(cityEntity);
		RankEntity rankEntity = new RankEntity();
		rankEntity.setId(dto.getRankId());
		entity.setRank(rankEntity);
		return entity;
	}

	@Override
	public FiremanEntity toUpdateEntity(FiremanUpdateDTO dto) {
		FiremanEntity entity = new FiremanEntity();
		entity.setAddress(dto.getAddress());
		entity.setDescription(dto.getDescription());
		entity.setBirthday(dto.getBirthday());
		entity.setAdmission(dto.getAdmission());
		entity.setPhone(dto.getPhone());
		entity.setCi(dto.getCi());
		BrigadeEntity brigateEntity = new BrigadeEntity();
		brigateEntity.setId(dto.getBrigadeId());
		entity.setBrigade(brigateEntity);
		DepartamentEntity departamentEntity = new DepartamentEntity();
		departamentEntity.setId(dto.getDepartamentId());
		entity.setDepartament(departamentEntity);
		CityEntity cityEntity = new CityEntity();
		cityEntity.setId(dto.getCityId());
		entity.setCity(cityEntity);
		RankEntity rankEntity = new RankEntity();
		rankEntity.setId(dto.getRankId());
		entity.setRank(rankEntity);
		return entity;
	}

}
