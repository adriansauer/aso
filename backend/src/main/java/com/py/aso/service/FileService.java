package com.py.aso.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.py.aso.exception.ResourceNotFoundException;
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
	private final String FILE_PUBLICATION = "publication_file.png";

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
	
	//Guarda la imagen en el directorio
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public FileDetailDTO saveFile(final MultipartFile file, final String name) throws Exception {
		// Valida que el archivo recibido exista.
		if (file.getSize() <= 0) {
			throw new FileProblemsException("Se necesita una imagen");
		}

		// Crea el path y lo guarda en los archivos
		final Path newFilePath = Paths.get(this.fileProperties.getRoot(), nameFile(file.getOriginalFilename())).normalize();
		final File newFile = new File(newFilePath.toUri());
		final boolean isFileCreated = newFile.createNewFile();

		// Validad si se creo correctamente
		if (!isFileCreated) {
			throw new FileProblemsException("La imagen no se pudo crear");
		}

		// Se escribe el archivo para guardar la imagen
		try (final FileOutputStream fout = new FileOutputStream(newFile)) {
			fout.write(file.getBytes());
		} catch (IOException ex) {
			throw new FileProblemsException("La imagen no se pudo guardar");
		}

		// Se registra en la base de datos en la tabla files.
		FileEntity entity = new FileEntity();
		entity.setName(name);
		entity.setPath(newFilePath.toString());
		return this.fileMapper.toDetailDTO(this.fileRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public FileDetailDTO update(long id, FileUpdateDTO dto) throws Exception {
		FileEntity entity = this.fileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));
		entity.setName(dto.getName());
		return this.fileMapper.toDetailDTO(this.fileRepository.save(entity));
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public FileDetailDTO updateFile(final MultipartFile file, final long id) throws Exception {
		// Valida que exista el archivo recibido.
		if (file.getSize() <= 0) {
			throw new FileProblemsException("Se necesita un archivo");
		}
		// Busca en la base de datos los datos de archivo viejo.
		FileEntity entity = this.fileRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Archivo", "id", id));
		// Verifica que el archivo viejo exista.
		final Path oldPath = Paths.get(entity.getPath());
		if (!Files.exists(oldPath)) {
			throw new ResourceNotFoundException("Archivo", "id", id);
		}

		// Crea el paht y el archivo para la nueva imagen
		final Path newFilePath = Paths.get(this.fileProperties.getRoot(), nameFile(file.getOriginalFilename())).normalize();
		final File newFile = new File(newFilePath.toUri());
		final boolean isFileCreated = newFile.createNewFile();
		// Verifica si se creo el nuevo archivo
		if (!isFileCreated)
			throw new FileProblemsException("El archivo no se pudo crear");
		// Se escribe el nuevo archivo
		try (final FileOutputStream fout = new FileOutputStream(newFile)) {
			fout.write(file.getBytes());
		} catch (IOException ex) {
			throw new FileProblemsException("La archivo no se pudo guardar");
		}
		// Se elimina el viejo archivo
		final File oldFile = new File(oldPath.toUri());
		if (!(oldFile.getName().equals(this.FILE) || oldFile.getName().equals(this.FILE_PUBLICATION) || oldFile.getName().equals(this.FILE_PUBLICATION))) {
			final boolean isFileDelete = oldFile.delete();
			if (!isFileDelete) {
				throw new FileProblemsException("El archivo no se cambio");
			}
		}
		// Se remplaza el path viejo por el nuevo
		entity.setPath(newFilePath.toString());
		return this.fileMapper.toDetailDTO(this.fileRepository.save(entity));
	}

	@Override
	public void delete(long id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private String nameFile(final String fileName) {
		Date date = new Date();
		final String user = SecurityContextHolder.getContext().getAuthentication().getName();
		StringBuffer sb = new StringBuffer();
		sb.append(user);
		sb.append('-');
		sb.append(date.getTime());
		sb.append('-');
		sb.append(fileName);
		return sb.toString();
	}

}
