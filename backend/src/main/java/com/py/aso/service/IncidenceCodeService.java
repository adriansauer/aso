package com.py.aso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.py.aso.dto.IncidenceCodeDTO;
import com.py.aso.dto.create.IncidenceCodeCreateDTO;
import com.py.aso.dto.detail.IncidenceCodeDetailDTO;
import com.py.aso.dto.update.IncidenceCodeUpdateDTO;
import com.py.aso.entity.IncidenceCodeEntity;
import com.py.aso.exception.ResourceExistsException;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.IncidenceCodeRepository;
import com.py.aso.service.mapper.IncidenceCodeMapper;

@Service
public class IncidenceCodeService implements BaseService<IncidenceCodeDTO,IncidenceCodeDetailDTO,IncidenceCodeCreateDTO,IncidenceCodeUpdateDTO>{
	
	@Autowired
	private IncidenceCodeRepository incidenceCodeRepository;
	
	@Autowired
	private IncidenceCodeMapper incidenceCodeMapper; 

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<IncidenceCodeDTO> findAll(final Pageable pageable) {
		return this.incidenceCodeRepository.findAllByDeleted(false, pageable)//
				.map(this.incidenceCodeMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public IncidenceCodeDetailDTO findById(final long id) throws Exception {
		return this.incidenceCodeRepository.findByIdAndDeleted(id, false)//
				.map(this.incidenceCodeMapper::toDetailDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("Incidence Code", "id", id));
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public IncidenceCodeDetailDTO findByCode(final String code) throws Exception {
		return this.incidenceCodeRepository.findByCodeAndDeleted(code, false)//
				.map(this.incidenceCodeMapper::toDetailDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("Incidence Code", "code", code));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public IncidenceCodeDetailDTO save(final IncidenceCodeCreateDTO dto) throws Exception {
		if(this.incidenceCodeRepository.existsByCodeAndDeleted(dto.getCode(), false)) {
			throw new ResourceExistsException(dto.getCode());
		}
		IncidenceCodeEntity entity = this.incidenceCodeMapper.toCreateEntity(dto);
		entity.setDeleted(false);
		return this.incidenceCodeMapper.toDetailDTO(this.incidenceCodeRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public IncidenceCodeDetailDTO update(final long id, final IncidenceCodeUpdateDTO dto) throws Exception {
		IncidenceCodeEntity entity = this.incidenceCodeRepository.findByIdAndDeleted(id, false)//
				.orElseThrow(() -> new ResourceNotFoundException("Incidence Code", "id", id));	
		if(this.incidenceCodeRepository.existsByCodeAndDeleted(dto.getCode(), false) && !dto.getCode().equalsIgnoreCase(entity.getCode())) {
			throw new ResourceExistsException(dto.getCode());
		}
		entity.setCode(dto.getCode());
		entity.setDescription(dto.getDescription());
		return this.incidenceCodeMapper.toDetailDTO(this.incidenceCodeRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(final long id) throws Exception {
		IncidenceCodeEntity entity = this.incidenceCodeRepository.findByIdAndDeleted(id, false)//
				.orElseThrow(() -> new ResourceNotFoundException("Incidence Code", "id", id));	
		entity.setDeleted(true);
		this.incidenceCodeRepository.save(entity);
		
	}

}
