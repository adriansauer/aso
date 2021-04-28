package com.py.aso.service.mapper;

import org.springframework.stereotype.Component;

import com.py.aso.dto.FiremanDTO;
import com.py.aso.dto.create.FiremanCreateDTO;
import com.py.aso.dto.create.UserCreateDTO;
import com.py.aso.dto.detail.FiremanDetailDTO;
import com.py.aso.dto.update.FiremanUpdateDTO;
import com.py.aso.dto.update.UserUpdateDTO;
import com.py.aso.entity.BrigadeEntity;
import com.py.aso.entity.CityEntity;
import com.py.aso.entity.DepartamentEntity;
import com.py.aso.entity.FiremanEntity;
import com.py.aso.entity.RankEntity;

@Component
public class FiremanMapper
		implements BaseMapper<FiremanEntity, FiremanDTO, FiremanDetailDTO, FiremanCreateDTO, FiremanUpdateDTO> {

	@Override
	public FiremanDTO toDTO(final FiremanEntity entity) {
		FiremanDTO dto = new FiremanDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getUser().getName());
		dto.setLastname(entity.getUser().getLastname());
		dto.setUsercode(entity.getUser().getUsercode());
		dto.setCity(entity.getCity().getName());
		dto.setRankTitle(entity.getRank().getTitle());
		dto.setImageId(entity.getImage().getId());
		dto.setBrigadeId(entity.getBrigade().getId());
		dto.setRankId(entity.getRank().getId());
		return dto;
	}

	@Override
	public FiremanDetailDTO toDetailDTO(final FiremanEntity entity) {
		FiremanDetailDTO dto = new FiremanDetailDTO();
		dto.setName(entity.getUser().getName());
		dto.setLastname(entity.getUser().getLastname());
		dto.setEmail(entity.getUser().getEmail());
		dto.setUsercode(entity.getUser().getUsercode());
		dto.setId(entity.getId());
		dto.setCi(entity.getCi());
		dto.setCreatedAt(entity.getUser().getCreatedAt());
		dto.setUpdatedAt(entity.getUser().getUpdatedAt());
		dto.setPhone(entity.getPhone());
		dto.setDescription(entity.getDescription());
		dto.setAdmission(entity.getAdmission());
		dto.setBirthday(entity.getBirthday());
		dto.setAddress(entity.getAddress());
		dto.setCityId(entity.getCity().getId());
		dto.setCity(entity.getCity().getName());
		dto.setDepartamentId(entity.getDepartament().getId());
		dto.setDepartament(entity.getDepartament().getName());
		dto.setRankId(entity.getRank().getId());
		dto.setRankTitle(entity.getRank().getTitle());
		dto.setBrigadeId(entity.getBrigade().getId());
		dto.setImageId(entity.getImage().getId());
		dto.setUserId(entity.getUser().getId());
		return dto;
	}

	@Override
	public FiremanEntity toEntity(final FiremanDTO dto) {
		FiremanEntity entity = new FiremanEntity();
		entity.setId(dto.getId());
		return entity;
	}

	@Override
	public FiremanEntity toCreateEntity(final FiremanCreateDTO dto) {
		FiremanEntity entity = new FiremanEntity();
		entity.setAddress(dto.getAddress());
		entity.setDescription(dto.getDescription());
		entity.setBirthday(dto.getBirthday());
		entity.setAdmission(dto.getAdmission());
		entity.setPhone(dto.getPhone());
		entity.setCi(dto.getCi());
		return entity;
	}

	@Override
	public FiremanEntity toUpdateEntity(final FiremanUpdateDTO dto) {
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

	public UserCreateDTO toUserCreateDTO(final FiremanCreateDTO dto) {
		UserCreateDTO userDTO = new UserCreateDTO();
		userDTO.setName(dto.getName());
		userDTO.setLastname(dto.getLastname());
		userDTO.setUsercode(dto.getUsercode());
		userDTO.setEmail(dto.getEmail());
		userDTO.setPassword(dto.getPassword());
		userDTO.setRepeatPassword(dto.getRepeatPassword());
		return userDTO;
	}

	public UserUpdateDTO toUserUpdateDTO(final FiremanUpdateDTO dto) {
		UserUpdateDTO userDTO = new UserUpdateDTO();
		userDTO.setName(dto.getName());
		userDTO.setLastname(dto.getLastname());
		userDTO.setUsercode(dto.getUsercode());
		userDTO.setEmail(dto.getEmail());
		return userDTO;
	}

}
