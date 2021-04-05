package com.py.aso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.py.aso.dto.RoleDTO;
import com.py.aso.entity.RoleEntity;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.RoleRepository;
import com.py.aso.service.mapper.RoleMapper;

@Service
public class RoleService implements BaseService<RoleDTO> {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleMapper roleMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<RoleDTO> findAll(final Pageable pageable) {
		return this.roleRepository.findAll(pageable)//
				.map(this.roleMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public RoleDTO findById(final long id) throws Exception {
		return this.roleRepository.findById(id)//
				.map(this.roleMapper::toDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RoleDTO save(final RoleDTO dto) throws Exception {
		final RoleEntity entity = this.roleMapper.toEntity(dto);
		return this.roleMapper.toDTO(this.roleRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RoleDTO update(final long id, final RoleDTO dto) throws Exception {
		if (!this.roleRepository.existsById(id)) {
			throw new ResourceNotFoundException("Role", "id", id);
		}
		RoleEntity entity = this.roleRepository.findById(id).get();
		entity.setAuthority(dto.getAuthority());
		return this.roleMapper.toDTO(this.roleRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(final long id) throws Exception {
		if (!this.roleRepository.existsById(id)) {
			throw new ResourceNotFoundException("Role", "id", id);
		}
		this.roleRepository.deleteById(id);
	}

}
