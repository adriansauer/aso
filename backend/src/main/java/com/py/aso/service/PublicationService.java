package com.py.aso.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.py.aso.dto.PublicationDTO;
import com.py.aso.dto.detail.PublicationDetailDTO;
import com.py.aso.dto.create.PublicationCreateDTO;
import com.py.aso.dto.update.PublicationUpdateDTO;
import com.py.aso.repository.PublicationRepository;
import com.py.aso.service.mapper.PublicationMapper;

import com.py.aso.exception.ResourceNotFoundException;

public class PublicationService implements BaseService<PublicationDTO, PublicationDetailDTO, PublicationCreateDTO, PublicationUpdateDTO> {
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Autowired
	private PublicationMapper publicationMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<PublicationDTO> findAll(Pageable pageable) {
		return this.publicationRepository.findAll(pageable)//
				.map(this.publicationMapper::toDTO);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PublicationDetailDTO findByUserId(final long id, Pageable pageable) throws Exception {
		return (PublicationDetailDTO) this.publicationRepository.findAllByUserIdAndDeleted(id, false, false, pageable)
				.map(this.publicationMapper::toDetailDTO);
		//Falta agregar la excepción, cuando se agrega marca un error
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PublicationDetailDTO findById(long id) throws Exception {
		return null;
	}

	@Override
	public PublicationDetailDTO save(PublicationCreateDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PublicationDetailDTO update(long id, PublicationUpdateDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
