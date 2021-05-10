package com.py.aso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.py.aso.dto.FileDTO;
import com.py.aso.dto.PublicationDTO;
import com.py.aso.dto.create.FileCreateDTO;
import com.py.aso.dto.detail.FileDetailDTO;
import com.py.aso.dto.update.FileUpdateDTO;
import com.py.aso.repository.FileRepository;
import com.py.aso.service.mapper.FileMapper;

@Service
public class FileService implements BaseService<FileDTO, FileDetailDTO, FileCreateDTO, FileUpdateDTO>{

	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private FileMapper fileMapper;

	@Override
	public Page<FileDTO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Page<FileDetailDTO> findByPublicationId(final long id, Pageable pageable) {
		return null;
	}
	
	public FileDetailDTO findAllByPublicationId(final long id) {
		return null;
	}

	@Override
	public FileDetailDTO findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileDetailDTO save(FileCreateDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public FileDetailDTO saveFile(final MultipartFile file, final String name) {
		return null;
	}

	@Override
	public FileDetailDTO update(long id, FileUpdateDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public FileDetailDTO updateFile() {
		return null;
	}

	@Override
	public void delete(long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
