package com.py.aso.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.py.aso.dto.PublicationDTO;
import com.py.aso.dto.detail.PublicationDetailDTO;
import com.py.aso.dto.create.PublicationCreateDTO;
import com.py.aso.dto.update.PublicationUpdateDTO;

public class PublicationService implements BaseService<PublicationDTO, PublicationDetailDTO, PublicationCreateDTO, PublicationUpdateDTO> {

	@Override
	public Page<PublicationDTO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PublicationDetailDTO findById(long id) throws Exception {
		// TODO Auto-generated method stub
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
