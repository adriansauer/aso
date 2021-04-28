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

import com.py.aso.dto.ImageDTO;
import com.py.aso.dto.create.ImageCreateDTO;
import com.py.aso.dto.detail.ImageDetailDTO;
import com.py.aso.dto.update.ImageUpdateDTO;
import com.py.aso.entity.ImageEntity;
import com.py.aso.exception.FileProblemsException;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.properties.ImageFileProperties;
import com.py.aso.repository.ImageRepository;
import com.py.aso.service.mapper.ImageMapper;

@Service
public class ImageService implements BaseService<ImageDTO, ImageDetailDTO, ImageCreateDTO, ImageUpdateDTO> {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private ImageMapper imageMapper;

	@Autowired
	private ImageFileProperties imageFileProperties;

	private final String IMAGE_FIREMAN = "perfil.png";
	private final String IMAGE_BRIGADE = "perfil_brigade.png";
	private final String IMAGE = "image.png";

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
		ImageEntity entity = this.imageRepository.findById(id)//
				.orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));

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

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ImageDetailDTO save(final ImageCreateDTO dto) throws Exception {
		// Crea el path de la imagen por defecto.
		final Path newFilePath = Paths.get(this.imageFileProperties.getRoot(), IMAGE).normalize();
		final File newFile = new File(newFilePath.toUri());

		// Valida que la imagen exista.
		final boolean isFileExists = newFile.exists();
		if (!isFileExists) {
			throw new FileProblemsException("La imagen no se pudo crear");
		}

		// Se registra en la tabla images la imagen.
		ImageEntity entity = new ImageEntity();
		entity.setName(dto.getName());
		entity.setPath(newFilePath.toString());
		return this.imageMapper.toDetailDTO(this.imageRepository.save(entity));
	}

	/**
	 * Metodo saveFile Guarda una imagen en los archivos y lo registra en la base de
	 * datos en la tabla images
	 * 
	 * @param file Archivo de la imagen
	 * @param name Nombre de la imagen
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ImageDetailDTO saveFile(final MultipartFile file, final String name) throws Exception {

		// Valida que el archivo recibido exista.
		if (file.getSize() <= 0) {
			throw new FileProblemsException("Se necesita una imagen");
		}

		// Crea el path y lo guarda en los archivos
		final Path newFilePath = Paths.get(this.imageFileProperties.getRoot(), nameFile(file.getOriginalFilename()))
				.normalize();
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

		// Se registra en la base de datos en la tabla images.
		ImageEntity entity = new ImageEntity();
		entity.setName(name);
		entity.setPath(newFilePath.toString());
		return this.imageMapper.toDetailDTO(this.imageRepository.save(entity));
	}

	/**
	 * Metodo saveFile Guarda una imagen en los archivos y lo registra en la base de
	 * datos en la tabla images
	 * 
	 * @param file Nombre del archivo de la imagen
	 * @param name Nombre de la imagen
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ImageDetailDTO saveFile(final String file, final String name) throws Exception {

		// Crea el path y lo guarda en los archivos
		final Path newFilePath = Paths.get(this.imageFileProperties.getRoot(), file).normalize();
		final File newFile = new File(newFilePath.toUri());
		final boolean isFileExists = newFile.exists();

		// Validad si se existe la imagen
		if (!isFileExists) {
			throw new FileProblemsException("La imagen no existe");
		}

		// Se registra en la base de datos en la tabla images.
		ImageEntity entity = new ImageEntity();
		entity.setName(name);
		entity.setPath(newFilePath.toString());
		return this.imageMapper.toDetailDTO(this.imageRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ImageDetailDTO update(final long id, final ImageUpdateDTO dto) throws Exception {
		ImageEntity entity = this.imageRepository.findById(id)//
				.orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));
		entity.setName(dto.getName());
		return this.imageMapper.toDetailDTO(this.imageRepository.save(entity));
	}

	/**
	 * Metodo updateFileById Remplaza el archivo existente por el nuevo archivo
	 * Verifica si el archivo existente es la imagen de perfil, entonce no es
	 * eliminado.
	 * 
	 * @param file Nuevo archivo
	 * @param id   Id del archivo viejo
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ImageDetailDTO updateFileById(final MultipartFile file, final long id) throws Exception {

		// Valida que exista el archivo recibido.
		if (file.getSize() <= 0) {
			throw new FileProblemsException("Se necesita una imagen");
		}

		// Busca en la base de datos los datos de archivo viejo.
		ImageEntity entity = this.imageRepository.findById(id)//
				.orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));

		// Verifica que el archivo viejo exista.
		final Path oldPath = Paths.get(entity.getPath());
		if (!Files.exists(oldPath)) {
			throw new ResourceNotFoundException("Image", "id", id);
		}

		// Crea el paht y el archivo para la nueva imagen
		final Path newFilePath = Paths.get(this.imageFileProperties.getRoot(), nameFile(file.getOriginalFilename()))
				.normalize();
		final File newFile = new File(newFilePath.toUri());
		final boolean isFileCreated = newFile.createNewFile();

		// Verifica si se creo el nuevo archivo
		if (!isFileCreated) {
			throw new FileProblemsException("La imagen no se pudo crear");
		}

		// Se escribe el nuevo archivo
		try (final FileOutputStream fout = new FileOutputStream(newFile)) {
			fout.write(file.getBytes());
		} catch (IOException ex) {
			throw new FileProblemsException("La imagen no se pudo guardar");
		}

		// Se elimina el viejo archivo
		final File oldFile = new File(oldPath.toUri());
		if (!(oldFile.getName().equals(this.IMAGE) || oldFile.getName().equals(this.IMAGE_BRIGADE)
				|| oldFile.getName().equals(this.IMAGE_FIREMAN))) {
			final boolean isFileDelete = oldFile.delete();
			if (!isFileDelete) {
				throw new FileProblemsException("La imagen no se cambiar");
			}
		}

		// Se remplaza el path viejo por el nuevo
		entity.setPath(newFilePath.toString());
		return this.imageMapper.toDetailDTO(this.imageRepository.save(entity));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(final long id) throws Exception {
		ImageEntity entity = this.imageRepository.findById(id)//
				.orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));
		final Path oldPath = Paths.get(entity.getPath());
		final File oldFile = new File(oldPath.toUri());
		if (!(oldFile.getName().equals(this.IMAGE) || !oldFile.getName().equals(this.IMAGE_BRIGADE)
				|| !oldFile.getName().equals(this.IMAGE_FIREMAN))) {
			final boolean isFileDelete = oldFile.delete();
			if (!isFileDelete) {
				throw new FileProblemsException("La imagen no se cambiar");
			}
		}
		this.imageRepository.delete(entity);
	}

	/**
	 * Metodo nameFile Crea un nombre de un archivo a partir del nombre de usuario,s
	 * la fecha y el nombre del archivo.
	 * 
	 * @param fileName Nombre del archivo recibidos
	 * @return
	 */
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
