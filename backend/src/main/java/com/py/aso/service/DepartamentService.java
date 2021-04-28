package com.py.aso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.py.aso.dto.DepartamentDTO;
import com.py.aso.dto.create.DepartamentCreateDTO;
import com.py.aso.dto.detail.DepartamentDetailDTO;
import com.py.aso.dto.update.DepartamentUpdateDTO;
import com.py.aso.entity.DepartamentEntity;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.DepartamentRepository;
import com.py.aso.service.mapper.DepartamentMapper;

@Service
public class DepartamentService
		implements BaseService<DepartamentDTO, DepartamentDetailDTO, DepartamentCreateDTO, DepartamentUpdateDTO> {

	@Autowired
	private DepartamentRepository departamentRepository;

	@Autowired
	private DepartamentMapper departamentMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<DepartamentDTO> findAll(final Pageable pageable) {
		return this.departamentRepository.findAllByDeleted(false, pageable)//
				.map(this.departamentMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public DepartamentDetailDTO findById(final long id) throws Exception {
		return this.departamentRepository.findByIdAndDeleted(id, false)//
				.map(this.departamentMapper::toDetailDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("Departament", "id", id));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public DepartamentDetailDTO save(final DepartamentCreateDTO dto) throws Exception {
		final DepartamentEntity entity = this.departamentMapper.toCreateEntity(dto);
		entity.setDeleted(false);
		return this.departamentMapper.toDetailDTO(this.departamentRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public DepartamentDetailDTO update(final long id, final DepartamentUpdateDTO dto) throws Exception {
		DepartamentEntity entity = this.departamentRepository.findByIdAndDeleted(id, false)//
				.orElseThrow(() -> new ResourceNotFoundException("Departament", "id", id));
		entity.setName(dto.getName());
		return this.departamentMapper.toDetailDTO(this.departamentRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(final long id) throws Exception {
		if (!this.departamentRepository.existsByIdAndDeleted(id, false)) {
			throw new ResourceNotFoundException("Departament", "id", id);
		}
		DepartamentEntity entity = this.departamentRepository.findById(id).get();
		entity.setDeleted(true);
		this.departamentRepository.save(entity);
	}

}
