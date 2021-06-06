package com.py.aso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.py.aso.dto.FileDTO;
import com.py.aso.dto.create.FileCreateDTO;
import com.py.aso.dto.detail.FileDetailDTO;
import com.py.aso.dto.update.FileUpdateDTO;
import com.py.aso.entity.FileEntity;
import com.py.aso.entity.PublicationEntity;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.FileRepository;
import com.py.aso.service.mapper.FileMapper;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FileService implements BaseService<FileDTO, FileDetailDTO, FileCreateDTO, FileUpdateDTO> {

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private FileMapper fileMapper;

	@Autowired
	private PublicationService publicationService;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<FileDTO> findAll(final Pageable pageable) {
		return this.fileRepository.findAllByDeleted(false, pageable).map(this.fileMapper::toDTO);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<FileDTO> findAllByPublicationId(final long id, Pageable pageable) throws Exception {
		return this.fileRepository.findAllByPublicationIdAndDeleted(id, false, pageable).map(this.fileMapper::toDTO);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<FileDTO> findByPublicationId(final long id) throws Exception {
		return this.fileRepository.findAllByPublicationIdAndDeleted(id, false).stream().map(this.fileMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public FileDetailDTO findById(final long id) throws Exception {
		return this.fileRepository.findByIdAndDeleted(id, false).map(this.fileMapper::toDetailDTO)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
	}

	// Solo registra el archivo en la base de datos, no hace copia del archivo
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public FileDetailDTO save(FileCreateDTO dto) throws Exception {

		// Validad que exista la publicacion y que sea del usuario
		this.publicationService.findMyPublicationById(dto.getPublicationId());

		// Se registra el archivo en la base de datos
		FileEntity entity = this.fileMapper.toCreateEntity(dto);
		PublicationEntity publicationEntity = new PublicationEntity();
		publicationEntity.setId(dto.getPublicationId());
		entity.setPublication(publicationEntity);
		return this.fileMapper.toDetailDTO(this.fileRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public FileDetailDTO update(final long id, final FileUpdateDTO dto) throws Exception {

		// Se busca el archivo
		FileEntity entity = this.fileRepository.findByIdAndDeleted(id, false)
				.orElseThrow(() -> new ResourceNotFoundException("File", "id", id));

		// Validad que exista la publicacion y que sea del usuario
		this.publicationService.findMyPublicationById(entity.getPublication().getId());

		entity.setName(dto.getName());
		entity.setFile(dto.getFile());
		return this.fileMapper.toDetailDTO(this.fileRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws Exception {
		// Se busca el archivo
		FileEntity entity = this.fileRepository.findByIdAndDeleted(id, false)
				.orElseThrow(() -> new ResourceNotFoundException("File", "id", id));

		// Validad que exista la publicacion y que sea del usuario
		this.publicationService.findMyPublicationById(entity.getPublication().getId());
		// Se elimina el archivo
		this.fileRepository.delete(entity);
	}

}
