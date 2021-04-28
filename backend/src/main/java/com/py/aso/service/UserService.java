package com.py.aso.service;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.py.aso.dto.PasswordDTO;
import com.py.aso.dto.UserDTO;
import com.py.aso.dto.create.UserCreateDTO;
import com.py.aso.dto.detail.UserDetailDTO;
import com.py.aso.dto.update.UserUpdateDTO;
import com.py.aso.entity.UserEntity;
import com.py.aso.exception.InvalidPasswordException;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.UserRepository;
import com.py.aso.service.mapper.RoleMapper;
import com.py.aso.service.mapper.UserMapper;

@Service
public class UserService implements BaseService<UserDTO, UserDetailDTO, UserCreateDTO, UserUpdateDTO> {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Autowired
	private BrigadeService brigadeService;

	@Autowired
	private FiremanService firemanService;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<UserDTO> findAll(final Pageable pageable) {
		return this.userRepository.findAllByEnabled(true, pageable)//
				.map(this.userMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserDetailDTO findById(final long id) throws Exception {
		UserEntity userEntity = this.userRepository.findByIdAndEnabled(id, true)//
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		UserDetailDTO userDetailDTO = this.userMapper.toDetailDTO(userEntity);

		// Busca id de los detalles
		final long firemanId = this.firemanService.existsByUserId(id);
		if (-1 != firemanId) {
			userDetailDTO.setDetailId(firemanId);
		} else {
			final long brigadeId = this.brigadeService.existsByUserId(id);
			userDetailDTO.setDetailId(brigadeId);
		}
		return userDetailDTO;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public boolean isActive(final long id) throws Exception {
		return this.userRepository.existsByIdAndEnabled(id, true);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserDetailDTO findByUsercode(final String usercode) throws Exception {
		UserEntity userEntity = this.userRepository.findByUsercodeAndEnabled(usercode, true)//
				.orElseThrow(() -> new ResourceNotFoundException("User", "usercode", usercode));
		UserDetailDTO userDetailDTO = this.userMapper.toDetailDTO(userEntity);
		final long firemanId = this.firemanService.existsByUserId(userEntity.getId());

		// Busca id de los detalles
		if (-1 != firemanId) {
			userDetailDTO.setDetailId(firemanId);
		} else {
			final long brigadeId = this.brigadeService.existsByUserId(userEntity.getId());
			userDetailDTO.setDetailId(brigadeId);
		}
		return userDetailDTO;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserDetailDTO save(final UserCreateDTO dto) throws Exception {
		if (!dto.getPassword().equals(dto.getRepeatPassword())) {
			throw new InvalidPasswordException("La contraseña no son iguales");
		}
		UserEntity entity = this.userMapper.toCreateEntity(dto);
		entity.setEnabled(true);
		entity.setPassword(this.passwordEncode.encode(dto.getPassword()));
		entity.setCreatedAt(new Date());
		return this.userMapper.toDetailDTO(this.userRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserDetailDTO update(final long id, final UserUpdateDTO dto) throws Exception {
		UserEntity entity = this.userRepository.findByIdAndEnabled(id, true)//
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		entity.setName(dto.getName());
		entity.setLastname(dto.getLastname());
		entity.setEmail(dto.getEmail());
		entity.setUsercode(dto.getUsercode());
		entity.setRoles(dto.getRoles().stream().map(this.roleMapper::toEntity).collect(Collectors.toList()));
		entity.setUpdatedAt(new Date());
		return this.userMapper.toDetailDTO(this.userRepository.save(entity));
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserDetailDTO updatePass(final long id, final PasswordDTO dto) throws Exception {
		if (!dto.getNewPassword().equals(dto.getRepeatPassword())) {
			throw new InvalidPasswordException("La contraseña no son iguales");
		}
		UserEntity entity = this.userRepository.findByIdAndEnabled(id, true)//
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		if (!this.passwordEncode.matches(dto.getOldPassword(), entity.getPassword())) {
			throw new InvalidPasswordException("La contraseña antigua no coincide");
		}
		if (this.passwordEncode.matches(dto.getNewPassword(), entity.getPassword())) {
			throw new InvalidPasswordException("La contraseña a cambiar no puede ser repetida a la antigua");
		}
		entity.setPassword(this.passwordEncode.encode(dto.getNewPassword()));
		entity.setUpdatedAt(new Date());
		return this.userMapper.toDetailDTO(this.userRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(final long id) throws Exception {
		UserEntity entity = this.userRepository.findByIdAndEnabled(id, true)//
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		entity.setEnabled(false);
		this.userRepository.save(entity);
	}

}
