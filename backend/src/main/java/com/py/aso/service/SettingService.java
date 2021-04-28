package com.py.aso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.py.aso.dto.SettingDTO;
import com.py.aso.dto.create.SettingCreateDTO;
import com.py.aso.dto.detail.SettingDetailDTO;
import com.py.aso.dto.update.SettingUpdateDTO;
import com.py.aso.entity.SettingEntity;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.SettingRepository;
import com.py.aso.service.mapper.SettingMapper;

@Service
public class SettingService implements BaseService<SettingDTO, SettingDetailDTO, SettingCreateDTO, SettingUpdateDTO> {

	@Autowired
	private SettingMapper settingMapper;

	@Autowired
	private SettingRepository settingRepository;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<SettingDTO> findAll(final Pageable pageable) {
		return this.settingRepository.findAll(pageable)//
				.map(this.settingMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public SettingDetailDTO findById(final long id) throws Exception {
		return this.settingRepository.findById(id)//
				.map(this.settingMapper::toDetailDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("Setting", "id", id));
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public SettingDetailDTO findByKey(final String key) throws Exception {
		return this.settingRepository.findByKey(key)//
				.map(this.settingMapper::toDetailDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("Setting", "key", key));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public SettingDetailDTO save(final SettingCreateDTO dto) throws Exception {
		final SettingEntity entity = this.settingMapper.toCreateEntity(dto);
		return this.settingMapper.toDetailDTO(this.settingRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public SettingDetailDTO update(final long id, final SettingUpdateDTO dto) throws Exception {
		if (!this.settingRepository.existsById(id)) {
			throw new ResourceNotFoundException("Setting", "id", id);
		}
		SettingEntity entity = this.settingMapper.toUpdateEntity(dto);
		entity.setId(id);
		return this.settingMapper.toDetailDTO(this.settingRepository.save(entity));
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public SettingDetailDTO updateByKey(final String key, final SettingUpdateDTO dto) throws Exception {
		SettingEntity entity = this.settingRepository.findByKey(key)//
				.orElseThrow(() -> new ResourceNotFoundException("Setting", "key", key));
		entity.setValue(dto.getValue());
		return this.settingMapper.toDetailDTO(this.settingRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(final long id) throws Exception {
		if (!this.settingRepository.existsById(id)) {
			throw new ResourceNotFoundException("Setting", "id", id);
		}
		this.settingRepository.deleteById(id);
	}

}
