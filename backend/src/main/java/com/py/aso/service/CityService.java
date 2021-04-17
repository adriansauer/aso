package com.py.aso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.py.aso.dto.CityDTO;
import com.py.aso.dto.create.CityCreateDTO;
import com.py.aso.dto.detail.CityDetailDTO;
import com.py.aso.dto.update.CityUpdateDTO;
import com.py.aso.entity.CityEntity;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.CityRepository;
import com.py.aso.service.mapper.CityMapper;

@Service
public class CityService implements BaseService<CityDTO, CityDetailDTO, CityCreateDTO, CityUpdateDTO> {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CityMapper cityMapper;

	@Override
	public Page<CityDTO> findAll(final Pageable pageable) {
		return this.cityRepository.findAll(pageable)//
				.map(this.cityMapper::toDTO);
	}

	@Override
	public CityDetailDTO findById(final long id) throws Exception {
		return this.cityRepository.findById(id)//
				.map(this.cityMapper::toDetailDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("City", "id", id));
	}

	@Override
	public CityDetailDTO save(final CityCreateDTO dto) throws Exception {
		CityEntity entity = this.cityMapper.toCreateEntity(dto);
		entity.setDeleted(false);
		return this.cityMapper.toDetailDTO(this.cityRepository.save(entity));
	}

	@Override
	public CityDetailDTO update(final long id, final CityUpdateDTO dto) throws Exception {
		if (!this.cityRepository.existsById(id)) {
			throw new ResourceNotFoundException("City", "id", id);
		}
		CityEntity entity = this.cityRepository.findById(id).get();
		entity.setName(dto.getName());
		return this.cityMapper.toDetailDTO(this.cityRepository.save(entity));
	}

	@Override
	public void delete(final long id) throws Exception {
		if (!this.cityRepository.existsById(id)) {
			throw new ResourceNotFoundException("City", "id", id);
		}
		CityEntity entity = this.cityRepository.findById(id).get();
		entity.setDeleted(true);
		this.cityRepository.save(entity);

	}

}
