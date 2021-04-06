package com.py.aso.service;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.py.aso.dto.UserDTO;
import com.py.aso.dto.create.UserCreateDTO;
import com.py.aso.dto.detail.UserDetailDTO;
import com.py.aso.entity.UserEntity;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.UserRepository;
import com.py.aso.service.mapper.RoleMapper;
import com.py.aso.service.mapper.UserMapper;

@Service
public class UserService implements BaseService<UserDTO, UserDetailDTO, UserCreateDTO> {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<UserDTO> findAll(final Pageable pageable) {
		return this.userRepository.findAllByEnabled(true, pageable)//
				.map(this.userMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserDetailDTO findById(final long id) throws Exception {
		return this.userRepository.findByIdAndEnabled(id, true)//
				.map(this.userMapper::toDetailDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserDetailDTO save(final UserCreateDTO dto) throws Exception {
		UserEntity entity = this.userMapper.toEntity(dto);
		entity.setEnabled(true);
		entity.setPassword(this.passwordEncoder.encode(dto.getPassword()));
		entity.setCreatedAt(new Date());
		return this.userMapper.toDetailDTO(this.userRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserDetailDTO update(final long id, final UserCreateDTO dto) throws Exception {
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
	public UserDetailDTO updatePass(final long id, final UserCreateDTO dto) throws Exception {
		UserEntity entity = this.userRepository.findByIdAndEnabled(id, true)//
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		entity.setPassword(this.passwordEncoder.encode(dto.getPassword()));
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
