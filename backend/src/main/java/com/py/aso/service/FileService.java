package com.py.aso.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.py.aso.dto.FileDTO;
import com.py.aso.dto.PublicationDTO;
import com.py.aso.dto.create.FileCreateDTO;
import com.py.aso.dto.detail.FileDetailDTO;
import com.py.aso.dto.update.FileUpdateDTO;
import com.py.aso.entity.FileEntity;
import com.py.aso.entity.ImageEntity;
import com.py.aso.exception.FileProblemsException;
import com.py.aso.repository.FileRepository;
import com.py.aso.service.mapper.FileMapper;

import com.py.aso.properties.fileProperties;

@Service
public class FileService<X> implements BaseService<FileDTO, FileDetailDTO, FileCreateDTO, FileUpdateDTO>{

	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private FileMapper fileMapper;
	
	@Autowired
	private fileProperties fileProperties;
	
	private final String FILE= "file.png";

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<FileDTO> findAll(Pageable pageable) {
		return this.fileRepository.findAll(pageable)
				.map(this.fileMapper::toDTO);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<FileDTO> findByPublicationId(final long id, Pageable pageable) throws Exception {
		return this.fileRepository.findAllByPublicationIdAndDeleted(id, false, false, pageable)
				.map(this.fileMapper::toDTO);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public FileDetailDTO findAllByPublicationId(final long id, Pageable pageable) {
		return (FileDetailDTO) this.fileRepository.findAllByPublicationIdAndDeleted(id, false, false, pageable)
				.map(this.fileMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public FileDetailDTO findById(long id) throws Exception {
		/*
		 * return this.fileRepository.findById(id) .map(this.fileMapper::toDetailDTO);
		 */
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public FileDetailDTO save(FileCreateDTO dto) throws Exception {
		final Path newFilePath = Paths.get(this.fileProperties.getRoot(), FILE).normalize();
		final File newFile = new File(newFilePath.toUri());
		
		// Valida que la imagen exista.
		final boolean isFileExists = newFile.exists();
		if (!isFileExists) {
			throw new FileProblemsException("El archivo no se pudo crear");
		}
		// Se registra en la tabla images la imagen.
		FileEntity entity = new FileEntity();
		entity.setName(dto.getName());
		entity.setPath(newFilePath.toString());
		return this.fileMapper.toDetailDTO(this.fileRepository.save(entity));
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public FileDetailDTO saveFile(final String file, final String name) throws Exception {
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
