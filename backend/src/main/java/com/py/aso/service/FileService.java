package com.py.aso.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
import com.py.aso.dto.detail.UserDetailDTO;
import com.py.aso.dto.update.FileUpdateDTO;
import com.py.aso.entity.FileEntity;
import com.py.aso.entity.PublicationEntity;
import com.py.aso.exception.FileProblemsException;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.FileRepository;
import com.py.aso.service.mapper.FileMapper;
import com.py.aso.service.mapper.PublicationMapper;
import com.py.aso.properties.FileProperties;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class FileService implements BaseService<FileDTO, FileDetailDTO, FileCreateDTO, FileUpdateDTO>{

	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private FileMapper fileMapper;
	
	@Autowired
	private FileProperties fileProperties;
	
	@Autowired
	private PublicationMapper publicationMapper;
	@Autowired
	private PublicationService publicationService;

	@Autowired
	private UserService userService;
	
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
		return this.fileRepository.findAllByPublicationIdAndDeleted(id, false, pageable)
				.map(this.fileMapper::toDTO);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<FileDTO> findByPublicationId(final long id) throws Exception {
		return this.fileRepository.findAllByPublicationIdAndDeleted(id, false)
				.stream().map(this.fileMapper::toDTO).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public FileDetailDTO findAllByPublicationId(final long id, Pageable pageable) {
		return (FileDetailDTO) this.fileRepository.findAllByPublicationIdAndDeleted(id, false, pageable)
				.map(this.fileMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public FileDetailDTO findById(long id) throws Exception {
		return this.fileRepository.findById(id)
				.map(this.fileMapper::toDetailDTO)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
	}
	
	/**
	 * Metodo findFileById Busca una imagen por el id de la tabla images, esta tabla
	 * contiene el path de la imagen.
	 * 
	 * @param id Id de la imagen
	 * @return UrlResource
	 * @throws Exception
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Resource findFileById(final long id) throws ResourceNotFoundException {
		// Valida que exista la imagen en la tabla images.
//		ImageEntity entity = this.imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));
		FileEntity entity = this.fileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));

		// Comprueba que el path sea correcto.
		final Path path = Paths.get(entity.getPath());
		if (!Files.exists(path)) {
			throw new ResourceNotFoundException("Image", "id", id);
		}
		try {
			// Retorna el path de donde esta la imagen.
			return new UrlResource(path.toUri());
		} catch (MalformedURLException ex) {
			throw new ResourceNotFoundException("Image", "id", id);
		}
	}

	//Solo registra el archivo en la base de datos, no hace copia del archivo
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public FileDetailDTO save(FileCreateDTO dto) throws Exception {
		//Validación de usuario
		final UserDetailDTO userDTO = this.userService.findById((int) SecurityContextHolder.getContext().getAuthentication().getCredentials());
				
		//Validación de la publicacion
		final PublicationDTO publicationDTO = publicationService.findById(dto.getPublicationId(), false);
		if ( publicationDTO.getUserId() != userDTO.getId() )
			throw new FileProblemsException("No es propietario de la publicación");
		
		final PublicationEntity publicationEntity = this.publicationMapper.toEntity(publicationDTO);
		final Path newFilePath = Paths.get(this.fileProperties.getRoot(), FILE).normalize();

		// Se registra el archivo en la base de datos
		FileEntity entity = new FileEntity();
		entity.setName(dto.getName());
		entity.setPath(newFilePath.toString());
		entity.setPublication(publicationEntity);
		return this.fileMapper.toDetailDTO(this.fileRepository.save(entity));
	}
	
	//Guarda el archivo en el directorio y registra en la base de datos
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public FileDetailDTO save(final MultipartFile file, final String name, long publicationId) throws Exception {
		//Validación de usuario
		final UserDetailDTO userDTO = this.userService.findById((int) SecurityContextHolder.getContext().getAuthentication().getCredentials());
						
		//Validación de la publicacion
		final PublicationDTO publicationDTO = publicationService.findById(publicationId, false);
		if ( publicationDTO.getUserId() != userDTO.getId() )
			throw new FileProblemsException("No es propietario de la publicación");

		// Valida que el archivo recibido exista.
		if ( file.getSize() <= 0 )
			throw new FileProblemsException("Se necesita un archivo");

		// Crea el path y guarda archivos
		final Path newFilePath	= Paths.get(this.fileProperties.getRoot(), nameFile(file.getOriginalFilename())).normalize();
		final File newFile		= new File(newFilePath.toUri());
		final boolean isFileCreated = newFile.createNewFile();

		// Validad si se creo correctamente
		if ( !isFileCreated )
			throw new FileProblemsException("El archivo no se guardo");

		// Se escribe el archivo para guardar la imagen
		try (final FileOutputStream fout = new FileOutputStream(newFile)) {
			fout.write(file.getBytes());
		} catch (IOException ex) {
			throw new FileProblemsException("El archivo no se guardo");
		}

		// Se registra el archivo en la base de datos
		final PublicationEntity publicationEntity = this.publicationMapper.toEntity(publicationDTO);
		FileEntity entity = new FileEntity();
		entity.setName(name);
		entity.setPath(newFilePath.toString());
		entity.setPublication(publicationEntity);
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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws Exception {
		FileEntity entity = this.fileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("File", "id", id));
		final Path oldPath = Paths.get(entity.getPath());
		final File oldFile = new File(oldPath.toUri());
		if (!(oldFile.getName().equals(this.FILE) || !oldFile.getName().equals(this.FILE_PUBLICATION) || !oldFile.getName().equals(this.FILE_PUBLICATION))) {
			final boolean isFileDelete = oldFile.delete();
			if (!isFileDelete)
				throw new FileProblemsException("El archivo no se cambio");
		}
		this.fileRepository.delete(entity);
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
