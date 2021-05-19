package com.py.aso.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.py.aso.dto.PublicationDTO;
import com.py.aso.dto.detail.PublicationDetailDTO;
import com.py.aso.dto.create.PublicationCreateDTO;
import com.py.aso.dto.update.PublicationUpdateDTO;
import com.py.aso.entity.PublicationEntity;
import com.py.aso.entity.UserEntity;
import com.py.aso.repository.PublicationRepository;
import com.py.aso.service.mapper.PublicationMapper;
import com.py.aso.service.mapper.UserMapper;
import com.py.aso.exception.InvalidAmountException;
import com.py.aso.exception.ResourceNotFoundException;

@Service
public class PublicationService implements BaseService<PublicationDTO, PublicationDetailDTO, PublicationCreateDTO, PublicationUpdateDTO> {
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Autowired
	private PublicationMapper publicationMapper;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserService userService;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<PublicationDTO> findAll(Pageable pageable) {
		return this.publicationRepository.findAllByDeletedAndEnabled(false, true, pageable)
				.map(this.publicationMapper::toDTO);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PublicationDetailDTO findAllByUserId(final long id, Pageable pageable) throws Exception {
		return (PublicationDetailDTO) this.publicationRepository.findAllByUserIdAndDeleted(id, false, false, pageable)
				.map(this.publicationMapper::toDetailDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PublicationDetailDTO findById(long id) throws Exception {
		PublicationDetailDTO publicationDetailDTO = this.publicationRepository.findById(id)
				.map(this.publicationMapper::toDetailDTO)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
		
		publicationDetailDTO.setFiles(this.fileService.findByPublicationId(id));
		
		return publicationDetailDTO;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PublicationDTO findById(long id, boolean var) throws Exception {
		return this.publicationRepository.findById(id)
				.map(this.publicationMapper::toDTO)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public PublicationDetailDTO save(PublicationCreateDTO dto) throws Exception {
		
		//Validación del atributo destination
		if (!dto.getDestination().contentEquals("Todos") && !dto.getDestination().contentEquals("Publico") && !dto.getDestination().contentEquals("MiBrigada"))
			throw new InvalidAmountException("Destino inválido,");
		
		//Validación de usuario
		final UserEntity userEntity = this.userMapper.toEntity(this.userService.findById((int) SecurityContextHolder.getContext().getAuthentication().getCredentials()));

		//Creación de la publicación
		PublicationEntity entity = this.publicationMapper.toCreateEntity(dto);
		entity.setUser(userEntity);
		entity.setDeleted(false);
		entity.setCreated_at(new Date());
		return this.publicationMapper.toDetailDTO(this.publicationRepository.save(entity));
	}

	@Override
	public PublicationDetailDTO update(long id, PublicationUpdateDTO dto) throws Exception {
		PublicationEntity entity = this.publicationRepository.findById(id).get();
		entity.setBody(dto.getBody());
		entity.setDestination(dto.getDestination());
		entity.setUpdated_at(new Date());
		return this.publicationMapper.toDetailDTO(this.publicationRepository.save(entity));
	}

	@Override
	public void delete(long id) throws Exception {
		PublicationEntity entity = this.publicationRepository.findById(id).get();
		entity.setDeleted(true);
		this.publicationRepository.save(entity);
	}
}
