package com.py.aso.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.py.aso.dto.ImageDTO;
import com.py.aso.dto.create.ImageCreateDTO;
import com.py.aso.dto.detail.ImageDetailDTO;
import com.py.aso.dto.update.ImageUpdateDTO;
import com.py.aso.entity.ImageEntity;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.ImageRepository;
import com.py.aso.service.mapper.ImageMapper;

@Service
public class ImageService implements BaseService<ImageDTO, ImageDetailDTO, ImageCreateDTO, ImageUpdateDTO> {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private ImageMapper imageMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<ImageDTO> findAll(final Pageable pageable) {
		return this.imageRepository.findAll(pageable)//
				.map(this.imageMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ImageDetailDTO findById(final long id) throws Exception {
		return this.imageRepository.findById(id)//
				.map(this.imageMapper::toDetailDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ImageDetailDTO save(final ImageCreateDTO dto) throws Exception {
		return this.imageMapper.toDetailDTO(this.imageRepository.save(this.imageMapper.toCreateEntity(dto)));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ImageDetailDTO update(final long id, final ImageUpdateDTO dto) throws Exception {
		ImageEntity entity = this.imageRepository.findById(id)//
				.orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));
		entity.setName(dto.getName());
		entity.setFile(dto.getFile());
		return this.imageMapper.toDetailDTO(this.imageRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(final long id) throws Exception {
		final ImageEntity entity = this.imageRepository.findById(id)//
				.orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));
		this.imageRepository.deleteById(entity.getId());
	}

}
