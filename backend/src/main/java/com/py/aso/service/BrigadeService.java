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

import com.py.aso.dto.BrigadeDTO;
import com.py.aso.dto.RoleDTO;
import com.py.aso.dto.create.BrigadeCreateDTO;
import com.py.aso.dto.create.ImageCreateDTO;
import com.py.aso.dto.create.UserCreateDTO;
import com.py.aso.dto.detail.BrigadeDetailDTO;
import com.py.aso.dto.detail.ImageDetailDTO;
import com.py.aso.dto.detail.UserDetailDTO;
import com.py.aso.dto.update.BrigadeUpdateDTO;
import com.py.aso.dto.update.ImageUpdateDTO;
import com.py.aso.dto.update.UserUpdateDTO;
import com.py.aso.entity.BrigadeEntity;
import com.py.aso.entity.CityEntity;
import com.py.aso.entity.DepartamentEntity;
import com.py.aso.entity.ImageEntity;
import com.py.aso.entity.UserEntity;
import com.py.aso.exception.InvalidAmountException;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.BrigadeRepository;
import com.py.aso.service.mapper.BrigadeMapper;
import com.py.aso.service.mapper.CityMapper;
import com.py.aso.service.mapper.DepartamentMapper;
import com.py.aso.service.mapper.RoleMapper;

@Service
public class BrigadeService implements BaseService<BrigadeDTO, BrigadeDetailDTO, BrigadeCreateDTO, BrigadeUpdateDTO> {

	@Autowired
	private BrigadeRepository brigadeRepository;

	@Autowired
	private BrigadeMapper brigadeMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private CityService cityService;

	@Autowired
	private CityMapper cityMapper;

	@Autowired
	private DepartamentService departamentService;

	@Autowired
	private DepartamentMapper departamentMapper;

	private final long ROLE_BRIGADE = 2L;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<BrigadeDTO> findAll(final Pageable pageable) {
		return this.brigadeRepository.findAllByEnabled(true, pageable)//
				.map(this.brigadeMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public BrigadeDetailDTO findById(final long id) throws Exception {
		return this.brigadeRepository.findByIdAndEnabled(id, true)//
				.map(this.brigadeMapper::toDetailDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("Brigade", "id", id));
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public BrigadeDetailDTO findByUserId(final long id) throws Exception {
		return this.brigadeRepository.findByUserIdAndEnabled(id, true)//
				.map(this.brigadeMapper::toDetailDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("Brigade", "id", id));
	}

	/**
	 * Verifica si existe una brigada con un id de usuario especifico Retorna el id
	 * si lo encuentra y si no existe retorna -1
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long existsByUserId(final long id) throws Exception {
		try {
			BrigadeEntity entity = this.brigadeRepository.findByUserIdAndEnabled(id, true).get();
			return entity.getId();
		} catch (Exception ex) {
			return -1;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public BrigadeDetailDTO save(final BrigadeCreateDTO dto) throws Exception {

		// Validacion de ciudad y departamento
		final CityEntity cityEntity = this.cityMapper.toEntity(this.cityService.findById(dto.getCityId()));
		final DepartamentEntity departamentEntity = this.departamentMapper
				.toEntity(this.departamentService.findById(dto.getDepartamentId()));

		// Creando el usuario
		UserCreateDTO userCreateDTO = this.brigadeMapper.toUserCreateDTO(dto);
		List<RoleDTO> listRoles = new ArrayList<>();
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(ROLE_BRIGADE);
		listRoles.add(roleDTO);
		userCreateDTO.setRoles(listRoles);
		UserDetailDTO userDetailDTO = this.userService.save(userCreateDTO);

		// Crea una imagen
		ImageCreateDTO imageCreateDTO = new ImageCreateDTO();
		imageCreateDTO.setName(dto.getName());
		imageCreateDTO.setFile(dto.getImage());
		ImageDetailDTO imageDetailDTO = this.imageService.save(imageCreateDTO);

		// Creando usuario para relacionar
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userDetailDTO.getId());

		// Creando Imagen para relacionar
		ImageEntity imageEntity = new ImageEntity();
		imageEntity.setId(imageDetailDTO.getId());

		// Creando la brigada
		BrigadeEntity entity = this.brigadeMapper.toCreateEntity(dto);
		entity.setUser(userEntity);
		entity.setImage(imageEntity);
		entity.setNumberMember(0);
		entity.setCity(cityEntity);
		entity.setDepartament(departamentEntity);

		// Guardando la brigada
		BrigadeDetailDTO brigadeDetailDTO = this.brigadeMapper.toDetailDTO(this.brigadeRepository.save(entity));

		// Cargando los datos para retornar
		brigadeDetailDTO.setName(userDetailDTO.getName());
		brigadeDetailDTO.setUsercode(userDetailDTO.getUsercode());
		brigadeDetailDTO.setEmail(userDetailDTO.getEmail());
		brigadeDetailDTO.setCreatedAt(userDetailDTO.getCreatedAt());
		return brigadeDetailDTO;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public BrigadeDetailDTO update(final long id, final BrigadeUpdateDTO dto) throws Exception {

		// Se busca si existe la brigada
		BrigadeEntity brigadeEntity = this.brigadeRepository.findByIdAndEnabled(id, true)//
				.orElseThrow(() -> new ResourceNotFoundException("Brigade", "id", id));

		// Validacion de ciudad y departamento
		final CityEntity cityEntity = this.cityMapper.toEntity(this.cityService.findById(dto.getCityId()));
		final DepartamentEntity departamentEntity = this.departamentMapper
				.toEntity(this.departamentService.findById(dto.getDepartamentId()));

		// Se actualiza el usuario
		UserUpdateDTO userUpdateDTO = this.brigadeMapper.toUserUpdateDTO(dto);
		userUpdateDTO.setRoles(
				brigadeEntity.getUser().getRoles().stream().map(this.roleMapper::toDTO).collect(Collectors.toList()));
		UserDetailDTO userDetailDTO = this.userService.update(brigadeEntity.getUser().getId(), userUpdateDTO);

		// Actualiza la imagen
		ImageUpdateDTO imageUpdateDTO = new ImageUpdateDTO();
		imageUpdateDTO.setName(dto.getName());
		imageUpdateDTO.setFile(dto.getImage());
		this.imageService.update(brigadeEntity.getImage().getId(), imageUpdateDTO);

		// Actualizando la brigada
		brigadeEntity.setAddress(dto.getAddress());
		brigadeEntity.setCreation(dto.getCreation());
		brigadeEntity.setPhone(dto.getPhone());
		brigadeEntity.setDescription(dto.getDescription());
		brigadeEntity.setCity(cityEntity);
		brigadeEntity.setDepartament(departamentEntity);

		// Guardando los cambios
		BrigadeDetailDTO brigadeDetailDTO = this.brigadeMapper.toDetailDTO(this.brigadeRepository.save(brigadeEntity));

		// Cargando los datos para retornar
		brigadeDetailDTO.setName(userDetailDTO.getName());
		brigadeDetailDTO.setUsercode(userDetailDTO.getUsercode());
		brigadeDetailDTO.setEmail(userDetailDTO.getEmail());
		brigadeDetailDTO.setCreatedAt(userDetailDTO.getCreatedAt());
		brigadeDetailDTO.setUpdatedAt(userDetailDTO.getUpdatedAt());
		return brigadeDetailDTO;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(final long id) throws Exception {
		final BrigadeEntity brigadeEntity = this.brigadeRepository.findByIdAndEnabled(id, true)//
				.orElseThrow(() -> new ResourceNotFoundException("Brigade", "id", id));
		this.userService.delete(brigadeEntity.getUser().getId());
		this.imageService.delete(brigadeEntity.getImage().getId());
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void subtractAMember(final long id) throws Exception {
		final BrigadeEntity entity = this.brigadeRepository.findByIdAndEnabled(id, true)//
				.orElseThrow(() -> new ResourceNotFoundException("Brigade", "id", id));
		if (0 >= entity.getNumberMember()) {
			throw new InvalidAmountException("Cantidad de miembros de la brigada");
		}
		entity.setNumberMember(entity.getNumberMember() - 1);
		this.brigadeRepository.save(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addAMember(final long id) throws Exception {
		final BrigadeEntity entity = this.brigadeRepository.findByIdAndEnabled(id, true)//
				.orElseThrow(() -> new ResourceNotFoundException("Brigade", "id", id));
		entity.setNumberMember(entity.getNumberMember() + 1);
		this.brigadeRepository.save(entity);
	}

}
