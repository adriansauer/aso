package com.py.aso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.py.aso.dto.UserDTO;
import com.py.aso.entity.UserEntity;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.UserRepository;
import com.py.aso.service.mapper.UserMapper;

@Service
public class UserService implements BaseService<UserDTO> {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<UserDTO> findAll(final Pageable pageable) {
		return this.userRepository.findAllByEnabled(true, pageable)//
				.map(this.userMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserDTO findById(final long id) throws Exception {
		return this.userRepository.findByIdAndEnabled(id, true)//
				.map(this.userMapper::toDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserDTO save(final UserDTO dto) throws Exception {
		UserEntity entity = this.userMapper.toEntity(dto);
		entity.setEnabled(true);
		entity.setPassword(dto.getPassword());
		return this.userMapper.toDTO(this.userRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserDTO update(final long id, final UserDTO dto) throws Exception {
		UserEntity originalEntity = this.userRepository.findByIdAndEnabled(id, true)//
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		UserEntity entity = this.userMapper.toEntity(dto);
		entity.setPassword(originalEntity.getPassword());
		entity.setId(id);
		return this.userMapper.toDTO(this.userRepository.save(entity));
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
