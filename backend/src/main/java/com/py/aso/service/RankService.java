package com.py.aso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.py.aso.dto.RankDTO;
import com.py.aso.dto.create.ImageCreateDTO;
import com.py.aso.dto.create.RankCreateDTO;
import com.py.aso.dto.detail.ImageDetailDTO;
import com.py.aso.dto.detail.RankDetailDTO;
import com.py.aso.dto.update.ImageUpdateDTO;
import com.py.aso.dto.update.RankUpdateDTO;
import com.py.aso.entity.ImageEntity;
import com.py.aso.entity.RankEntity;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.RankRepository;
import com.py.aso.service.mapper.RankMapper;

@Service
public class RankService implements BaseService<RankDTO, RankDetailDTO, RankCreateDTO, RankUpdateDTO> {

	@Autowired
	private RankRepository rankRepository;

	@Autowired
	private RankMapper rankMapper;

	@Autowired
	private ImageService imageService;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<RankDTO> findAll(final Pageable pageable) {
		return this.rankRepository.findAllByDeleted(false, pageable)//
				.map(this.rankMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public RankDetailDTO findById(final long id) throws Exception {
		return this.rankRepository.findByIdAndDeleted(id, false)//
				.map(this.rankMapper::toDetailDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("Rank", "id", id));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RankDetailDTO save(final RankCreateDTO dto) throws Exception {

		// Crea una imagen
		ImageCreateDTO imageCreateDTO = new ImageCreateDTO();
		imageCreateDTO.setName(dto.getTitle());
		imageCreateDTO.setFile(dto.getImage());
		ImageDetailDTO imageDTO = this.imageService.save(imageCreateDTO);

		// Genera la entidad para relacionar con el rango
		ImageEntity imageEntity = new ImageEntity();
		imageEntity.setId(imageDTO.getId());

		// Crea el rango
		RankEntity entity = this.rankMapper.toCreateEntity(dto);
		entity.setImage(imageEntity);
		entity.setDeleted(false);
		return this.rankMapper.toDetailDTO(this.rankRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RankDetailDTO update(final long id, final RankUpdateDTO dto) throws Exception {
		RankEntity entity = this.rankRepository.findByIdAndDeleted(id, false)//
				.orElseThrow(() -> new ResourceNotFoundException("Rank", "id", id));

		// Actualiza la imagen 
		ImageUpdateDTO imageUpdateDTO = new ImageUpdateDTO();
		imageUpdateDTO.setName(dto.getTitle());
		imageUpdateDTO.setFile(dto.getImage());
		this.imageService.update(entity.getImage().getId(), imageUpdateDTO);

		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		return this.rankMapper.toDetailDTO(this.rankRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(final long id) throws Exception {
		RankEntity entity = this.rankRepository.findByIdAndDeleted(id, false)//
				.orElseThrow(() -> new ResourceNotFoundException("Rank", "id", id));
		this.rankRepository.deleteById(entity.getId());
		this.imageService.delete(entity.getImage().getId());

	}

}
