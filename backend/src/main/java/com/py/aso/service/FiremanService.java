package com.py.aso.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.py.aso.dto.FiremanDTO;
import com.py.aso.dto.RoleDTO;
import com.py.aso.dto.create.FiremanCreateDTO;
import com.py.aso.dto.create.UserCreateDTO;
import com.py.aso.dto.detail.FiremanDetailDTO;
import com.py.aso.dto.detail.ImageDetailDTO;
import com.py.aso.dto.detail.UserDetailDTO;
import com.py.aso.dto.update.FiremanUpdateDTO;
import com.py.aso.dto.update.UserUpdateDTO;
import com.py.aso.entity.BrigadeEntity;
import com.py.aso.entity.CityEntity;
import com.py.aso.entity.DepartamentEntity;
import com.py.aso.entity.FiremanEntity;
import com.py.aso.entity.ImageEntity;
import com.py.aso.entity.RankEntity;
import com.py.aso.entity.UserEntity;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.FiremanRepository;
import com.py.aso.service.mapper.BrigadeMapper;
import com.py.aso.service.mapper.CityMapper;
import com.py.aso.service.mapper.DepartamentMapper;
import com.py.aso.service.mapper.FiremanMapper;
import com.py.aso.service.mapper.RankMapper;
import com.py.aso.service.mapper.RoleMapper;
import com.py.aso.service.mapper.UserMapper;

@Service
public class FiremanService implements BaseService<FiremanDTO, FiremanDetailDTO, FiremanCreateDTO, FiremanUpdateDTO> {

	@Autowired
	private FiremanRepository firemanRepository;

	@Autowired
	private FiremanMapper firemanMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private BrigadeService brigadeService;

	@Autowired
	private BrigadeMapper brigadeMapper;

	@Autowired
	private CityService cityService;

	@Autowired
	private CityMapper cityMapper;

	@Autowired
	private DepartamentService departamentService;

	@Autowired
	private DepartamentMapper departamentMapper;

	@Autowired
	private RankService rankService;

	@Autowired
	private RankMapper rankMapper;

	@Autowired
	private ImageService imageService;

	@Autowired
	private RoleMapper roleMapper;

	private final long ROLE_FIREMAN = 1L;
	private final String IMAGE_FIREMAN = "perfil.png";

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<FiremanDTO> findAll(final Pageable pageable) {
		return this.firemanRepository.findAllByEnabled(true, pageable)//
				.map(this.firemanMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public FiremanDetailDTO findById(final long id) throws Exception {
		return this.firemanRepository.findByIdAndEnabled(id, true)//
				.map(this.firemanMapper::toDetailDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("Fireman", "id", id));
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<FiremanDTO> findByBrigadeId(final long id, final Pageable pageable) throws Exception {
		return this.firemanRepository.findAllByBrigadeIdAndEnabled(id, true, pageable)//
				.map(this.firemanMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public FiremanDetailDTO save(final FiremanCreateDTO dto) throws Exception {

		// Validacion de ciudad, departamento, brigada y rango
		final CityEntity cityEntity = this.cityMapper.toEntity(this.cityService.findById(dto.getCityId()));
		final DepartamentEntity departamentEntity = this.departamentMapper
				.toEntity(this.departamentService.findById(dto.getDepartamentId()));
		final BrigadeEntity brigadeEntity = this.brigadeMapper
				.toEntity(this.brigadeService.findById(dto.getBrigadeId()));
		final RankEntity rankEntity = this.rankMapper.toEntity(this.rankService.findById(dto.getRankId()));

		// Creando el usuario
		UserCreateDTO userCreateDTO = this.firemanMapper.toUserCreateDTO(dto);
		List<RoleDTO> listRoles = new ArrayList<>();
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(ROLE_FIREMAN);
		listRoles.add(roleDTO);
		userCreateDTO.setRoles(listRoles);
		UserDetailDTO userDetailDTO = this.userService.save(userCreateDTO);

		// Creando una imagen
		ImageDetailDTO imageDetailDTO = this.imageService.saveFile(this.IMAGE_FIREMAN, dto.getName());

		// Creando usuario para relacionar
		UserEntity userEntity = this.userMapper.toEntity(userDetailDTO);

		// Creando Imagen para relacionar
		ImageEntity imageEntity = new ImageEntity();
		imageEntity.setId(imageDetailDTO.getId());

		// Creando un bombero
		FiremanEntity entity = this.firemanMapper.toCreateEntity(dto);
		entity.setUser(userEntity);
		entity.setImage(imageEntity);
		entity.setCity(cityEntity);
		entity.setBrigade(brigadeEntity);
		entity.setRank(rankEntity);
		entity.setDepartament(departamentEntity);

		// Aumentando la cantidad de integrantes en la brigada
		this.brigadeService.addAMember(entity.getBrigade().getId());

		return this.firemanMapper.toDetailDTO(this.firemanRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public FiremanDetailDTO update(final long id, final FiremanUpdateDTO dto) throws Exception {

		// Buscando el bombero a editar
		FiremanEntity entity = this.firemanRepository.findByIdAndEnabled(id, true)//
				.orElseThrow(() -> new ResourceNotFoundException("Fireman", "id", id));

		// Validacion de ciudad, departamento, brigada y rango
		final CityEntity cityEntity = this.cityMapper.toEntity(this.cityService.findById(dto.getCityId()));
		final DepartamentEntity departamentEntity = this.departamentMapper
				.toEntity(this.departamentService.findById(dto.getDepartamentId()));
		final BrigadeEntity brigadeEntity = this.brigadeMapper
				.toEntity(this.brigadeService.findById(dto.getBrigadeId()));
		final RankEntity rankEntity = this.rankMapper.toEntity(this.rankService.findById(dto.getRankId()));

		// Se actualiza el usuario
		UserUpdateDTO userUpdateDTO = this.firemanMapper.toUserUpdateDTO(dto);
		userUpdateDTO.setRoles(
				entity.getUser().getRoles().stream().map(this.roleMapper::toDTO).collect(Collectors.toList()));
		this.userService.update(entity.getUser().getId(), userUpdateDTO);

		// Actualizando el bombero
		entity.setAddress(dto.getAddress());
		entity.setPhone(dto.getPhone());
		entity.setCi(dto.getCi());
		entity.setDescription(dto.getDescription());
		entity.setBirthday(dto.getBirthday());
		entity.setAdmission(dto.getAdmission());
		entity.setCity(cityEntity);
		if (entity.getBrigade().getId() != dto.getBrigadeId()) {
			this.brigadeService.subtractAMember(entity.getBrigade().getId());
			this.brigadeService.addAMember(dto.getBrigadeId());
		}
		entity.setBrigade(brigadeEntity);
		entity.setRank(rankEntity);
		entity.setDepartament(departamentEntity);

		return this.firemanMapper.toDetailDTO(this.firemanRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(final long id) throws Exception {
		final FiremanEntity entity = this.firemanRepository.findByIdAndEnabled(id, true)//
				.orElseThrow(() -> new ResourceNotFoundException("Fireman", "id", id));
		this.brigadeService.subtractAMember(entity.getBrigade().getId());
		this.userService.delete(entity.getUser().getId());
	}

}
