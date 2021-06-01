package com.py.aso.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.py.aso.dto.PublicationDTO;
import com.py.aso.dto.detail.FiremanDetailDTO;
import com.py.aso.dto.detail.PublicationDetailDTO;
import com.py.aso.dto.detail.UserDetailDTO;
import com.py.aso.dto.create.PublicationCreateDTO;
import com.py.aso.dto.update.PublicationUpdateDTO;
import com.py.aso.entity.PublicationEntity;
import com.py.aso.entity.UserEntity;
import com.py.aso.repository.PublicationRepository;
import com.py.aso.service.mapper.PublicationMapper;
import com.py.aso.validations.PublicationDestinationValidation;
import com.py.aso.exception.InvalidArgumentException;
import com.py.aso.exception.ResourceNotFoundException;

@Service
public class PublicationService
		implements BaseService<PublicationDTO, PublicationDetailDTO, PublicationCreateDTO, PublicationUpdateDTO> {

	@Autowired
	private PublicationRepository publicationRepository;

	@Autowired
	private PublicationMapper publicationMapper;

	@Autowired
	private FileService fileService;

	@Autowired
	private UserService userService;

	@Autowired
	private FiremanService firemanService;

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
		return this.publicationRepository.findById(id).map(this.publicationMapper::toDTO)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public PublicationDetailDTO save(PublicationCreateDTO dto) throws Exception {

		// Obtiene datos del usuario logueado
		UserEntity userEntity = new UserEntity();
		userEntity.setId((Long) SecurityContextHolder.getContext().getAuthentication().getCredentials());

		// Creación de la publicación
		PublicationEntity entity = this.publicationMapper.toCreateEntity(dto);
		entity.setUser(userEntity);
		entity.setDeleted(false);
		entity.setCreated_at(new Date());

		// definicion de destino
		entity.setDestination(destination(dto.getDestination(), userEntity.getId()));

		// Guardando la publicacion
		return this.publicationMapper.toDetailDTO(this.publicationRepository.save(entity));
	}

	@Override
	public PublicationDetailDTO update(long id, PublicationUpdateDTO dto) throws Exception {
		PublicationEntity entity = this.publicationRepository.findById(id).get();
		// Validación de usuario
		final UserDetailDTO userDTO = this.userService
				.findById((int) SecurityContextHolder.getContext().getAuthentication().getCredentials());
		if (entity.getUser().getId() != userDTO.getId())
			throw new AccessDeniedException("No es propietario de la publicación");

		entity.setBody(dto.getBody());
		// entity.setDestination(dto.getDestination());
		entity.setUpdated_at(new Date());
		return this.publicationMapper.toDetailDTO(this.publicationRepository.save(entity));
	}

	@Override
	public void delete(long id) throws Exception {
		PublicationEntity entity = this.publicationRepository.findById(id).get();
		// Validación de usuario
		final UserDetailDTO userDTO = this.userService
				.findById((int) SecurityContextHolder.getContext().getAuthentication().getCredentials());
		if (entity.getUser().getId() != userDTO.getId())
			throw new AccessDeniedException("No es propietario de la publicación");
		entity.setDeleted(true);
		this.publicationRepository.save(entity);
	}

	/**
	 * Metodo destination Verifica para quien esta dirigida la publicacion y segun
	 * eso asigna un numero. Todos -> 0 Publico -> -1 Mi Brigada -> 0 < i El numero
	 * que responde cuando es Mi Brigada corresponde a la brigada a la que esta
	 * publicacion es dirigida
	 * 
	 * @param destination Destino en palabras
	 * @param userId      Id del usuario que realiza la publicacion
	 * @return Numero a que representa el destino de la publicacion
	 * @throws Exception Argumento invalidos
	 */
	@SuppressWarnings("unlikely-arg-type")
	private long destination(final String destination, final Long userId) throws Exception {
		if (PublicationDestinationValidation.DESTINATION_MY_BRIGADE.equals(destination)) {
			final Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication()
					.getAuthorities();
			if (roles.contains("ROLE_USER")) {
				final FiremanDetailDTO firemanDetailDTO = firemanService.findByUserId(userId);
				return firemanDetailDTO.getBrigadeId();
			} else if (roles.contains("ROLE_BRIGADE")) {
				return userId;
			} else if (roles.contains("ROLE_SUPERUSER")) {
				return 0;
			}
		} else if (PublicationDestinationValidation.DESTINATION_ALL.equals(destination)) {
			return 0;
		} else if (PublicationDestinationValidation.DESTINATION_PUBLIC.equals(destination)) {
			return -1;
		}
		throw new InvalidArgumentException("destino", "[Publico, Todos, Mi Brigada]");

	}
}
