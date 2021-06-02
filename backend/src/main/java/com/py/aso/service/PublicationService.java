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
import com.py.aso.dto.detail.BrigadeDetailDTO;
import com.py.aso.dto.detail.FiremanDetailDTO;
import com.py.aso.dto.detail.PublicationDetailDTO;
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
	private FiremanService firemanService;

	@Autowired
	private BrigadeService brigadeService;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<PublicationDTO> findAll(Pageable pageable) {
		try {
			String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0]
					.toString();
			if (role.equals("ROLE_USER")) {
				final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication()
						.getCredentials();
				final FiremanDetailDTO firemanDetailDTO = firemanService.findByUserId(userId);
				return this.publicationRepository.findAllByDeletedAndEnabledAndDestination(false, true,
						firemanDetailDTO.getBrigadeId(), pageable).map(this.publicationMapper::toDTO);
			} else if (role.equals("ROLE_BRIGADE")) {
				final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication()
						.getCredentials();
				final BrigadeDetailDTO brigadeDetailDTO = brigadeService.findByUserId(userId);
				return this.publicationRepository
						.findAllByDeletedAndEnabledAndDestination(false, true, brigadeDetailDTO.getId(), pageable)
						.map(this.publicationMapper::toDTO);
			} else if (role.equals("ROLE_SUPERUSER")) {
				return this.publicationRepository.findAllByDeletedAndEnabled(false, true, pageable)
						.map(this.publicationMapper::toDTO);
			}
		} catch (Exception ex) {
			return null;
		}
		return null;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<PublicationDTO> findAllByUserId(final long id, Pageable pageable) throws Exception {
		try {
			String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0]
					.toString();
			if (role.equals("ROLE_USER")) {
				final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication()
						.getCredentials();
				final FiremanDetailDTO firemanDetailDTO = firemanService.findByUserId(userId);
				return this.publicationRepository.findAllByUserIdAndDeletedAndEnabledAndDestination(id, false, true,
						firemanDetailDTO.getBrigadeId(), pageable).map(this.publicationMapper::toDTO);
			} else if (role.equals("ROLE_BRIGADE")) {
				final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication()
						.getCredentials();
				final BrigadeDetailDTO brigadeDetailDTO = brigadeService.findByUserId(userId);
				return this.publicationRepository.findAllByUserIdAndDeletedAndEnabledAndDestination(id, false, true,
						brigadeDetailDTO.getId(), pageable).map(this.publicationMapper::toDTO);
			} else if (role.equals("ROLE_SUPERUSER")) {
				return this.publicationRepository.findAllByUserIdAndDeletedAndEnabled(id, false, true, pageable)
						.map(this.publicationMapper::toDTO);
			}
		} catch (Exception ex) {
			return null;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PublicationDetailDTO findById(long id) throws Exception {
		try {
			String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0]
					.toString();
			if (role.equals("ROLE_USER")) {
				final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication()
						.getCredentials();
				final FiremanDetailDTO firemanDetailDTO = firemanService.findByUserId(userId);
				return this.publicationRepository
						.findByIdAndDeletedAndEnabledAndDestination(id, false, true, firemanDetailDTO.getBrigadeId())
						.map(this.publicationMapper::toDetailDTO)
						.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
			} else if (role.equals("ROLE_BRIGADE")) {
				final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication()
						.getCredentials();
				final BrigadeDetailDTO brigadeDetailDTO = brigadeService.findByUserId(userId);
				return this.publicationRepository
						.findByIdAndDeletedAndEnabledAndDestination(id, false, true, brigadeDetailDTO.getId())
						.map(this.publicationMapper::toDetailDTO)
						.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
			} else if (role.equals("ROLE_SUPERUSER")) {
				return this.publicationRepository.findByIdAndDeletedAndEnabled(id, false, true)
						.map(this.publicationMapper::toDetailDTO)
						.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
			}
		} catch (Exception ex) {
			return null;
		}
		return null;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PublicationDTO findById(long id, boolean var) throws Exception {
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public PublicationDetailDTO save(PublicationCreateDTO dto) throws Exception {

		// Obtiene datos del usuario logueado
		UserEntity userEntity = new UserEntity();
		userEntity.setId((long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials());

		// Creación de la publicación
		PublicationEntity entity = this.publicationMapper.toCreateEntity(dto);
		entity.setUser(userEntity);
		entity.setDeleted(false);
		entity.setCreated_at(new Date());

		// definicion de destino
		entity.setDestination(destination(dto.getDestination(), userEntity.getId(), dto.getBrigadeId()));

		// Guardando la publicacion
		return this.publicationMapper.toDetailDTO(this.publicationRepository.save(entity));
	}

	@Override
	public PublicationDetailDTO update(long id, PublicationUpdateDTO dto) throws Exception {
		PublicationEntity entity = null;
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
		if (role.equals("ROLE_USER")) {
			final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			final FiremanDetailDTO firemanDetailDTO = firemanService.findByUserId(userId);
			entity = this.publicationRepository
					.findByIdAndDeletedAndEnabledAndDestination(id, false, true, firemanDetailDTO.getBrigadeId())
					.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
		} else if (role.equals("ROLE_BRIGADE")) {
			final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			final BrigadeDetailDTO brigadeDetailDTO = brigadeService.findByUserId(userId);
			entity = this.publicationRepository
					.findByIdAndDeletedAndEnabledAndDestination(id, false, true, brigadeDetailDTO.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
		} else if (role.equals("ROLE_SUPERUSER")) {
			entity = this.publicationRepository.findByIdAndDeletedAndEnabled(id, false, true)
					.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
		}

		// Obtiene datos del usuario logueado
		UserEntity userEntity = new UserEntity();
		userEntity.setId((long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials());

		entity.setBody(dto.getBody());
		entity.setDestination(destination(dto.getDestination(), userEntity.getId(), dto.getBrigadeId()));
		entity.setUpdated_at(new Date());
		return this.publicationMapper.toDetailDTO(this.publicationRepository.save(entity));
	}

	@Override
	public void delete(long id) throws Exception {
		PublicationEntity entity = null;
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
		if (role.equals("ROLE_USER")) {
			final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			final FiremanDetailDTO firemanDetailDTO = firemanService.findByUserId(userId);
			entity = this.publicationRepository
					.findByIdAndDeletedAndEnabledAndDestination(id, false, true, firemanDetailDTO.getBrigadeId())
					.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
		} else if (role.equals("ROLE_BRIGADE")) {
			final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			final BrigadeDetailDTO brigadeDetailDTO = brigadeService.findByUserId(userId);
			entity = this.publicationRepository
					.findByIdAndDeletedAndEnabledAndDestination(id, false, true, brigadeDetailDTO.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
		} else if (role.equals("ROLE_SUPERUSER")) {
			entity = this.publicationRepository.findByIdAndDeletedAndEnabled(id, false, true)
					.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
		}
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
	 * @param brigadeId   Id de la brigada, en el caso de que el admin lo publique
	 *                    para una brigada
	 * @return Numero a que representa el destino de la publicacion
	 * @throws Exception Argumento invalidos
	 */
	private long destination(final String destination, final Long userId, final long brigadeId) throws Exception {
		if (PublicationDestinationValidation.DESTINATION_MY_BRIGADE.equals(destination)) {
			String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0]
					.toString();
			if (role.equals("ROLE_USER")) {
				final FiremanDetailDTO firemanDetailDTO = firemanService.findByUserId(userId);
				return firemanDetailDTO.getBrigadeId();
			} else if (role.equals("ROLE_BRIGADE")) {
				final BrigadeDetailDTO brigadeDetailDTO = brigadeService.findByUserId(userId);
				return brigadeDetailDTO.getId();
			} else if (role.equals("ROLE_SUPERUSER")) {
				if (0 >= brigadeId)
					throw new InvalidArgumentException("brigadeId", "brigadas existentes");
				// Valida que exista la brigada indicada
				brigadeService.findById(brigadeId);
				return brigadeId;
			}
		} else if (PublicationDestinationValidation.DESTINATION_ALL.equals(destination)) {
			return 0;
		} else if (PublicationDestinationValidation.DESTINATION_PUBLIC.equals(destination)) {
			return -1;
		}
		throw new InvalidArgumentException("destino", "[Publico, Todos, Mi Brigada]");

	}

}
