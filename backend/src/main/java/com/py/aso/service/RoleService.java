package com.py.aso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.py.aso.dto.RoleDTO;
import com.py.aso.dto.create.RoleCreateDTO;
import com.py.aso.dto.detail.RoleDetailDTO;
import com.py.aso.dto.update.RoleUpdateDTO;
import com.py.aso.entity.RoleEntity;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.RoleRepository;
import com.py.aso.service.mapper.RoleMapper;

@Service
public class RoleService implements BaseService<RoleDTO, RoleDetailDTO, RoleCreateDTO, RoleUpdateDTO> {

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
	public RoleDetailDTO findById(final long id) throws Exception {
		return this.roleRepository.findById(id)//
				.map(this.roleMapper::toDetailDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RoleDetailDTO save(final RoleCreateDTO dto) throws Exception {
		final RoleEntity entity = this.roleMapper.toCreateEntity(dto);
		return this.roleMapper.toDetailDTO(this.roleRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RoleDetailDTO update(final long id, final RoleUpdateDTO dto) throws Exception {
		RoleEntity entity = this.roleRepository.findById(id)//
				.orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
		entity.setAuthority(dto.getAuthority());
		return this.roleMapper.toDetailDTO(this.roleRepository.save(entity));
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
