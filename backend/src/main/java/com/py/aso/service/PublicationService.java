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

	@Autowired
	private FileService fileService;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<PublicationDTO> findAll(Pageable pageable) {
		try {
			// Obtiene los roles del usuario logueado del contexto.
			String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0]
					.toString();
			// Si es un Bombero puede ver las publicaciones que son para Todos, Publico y Mi
			// Brigada.
			// Filtra que sea solo de la brigada del Bombero logueado.
			if (role.equals("ROLE_USER")) {
				// Se obtiene el id del usuario logueado.
				final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication()
						.getCredentials();
				// Se busca al Bombero para obtener el id de la brigada en la que esta.
				final FiremanDetailDTO firemanDetailDTO = firemanService.findByUserId(userId);
				// Se realiza la peticion a la BD, <La publicacion no debe estar eliminada> <El
				// usuario debe estar activo> <Id de la brirgada del usuario> <Paginacion>.
				return this.publicationRepository.findAllByDeletedAndEnabledAndDestination(false, true,
						firemanDetailDTO.getBrigadeId(), pageable).map(this.publicationMapper::toDTO);
			}
			// Si es una Brigada puede ver las publicaciones que son para Todos, Publico y
			// Mi Brigada.
			// Filtra que sea solo de la brigada de la Brigada logueada.
			else if (role.equals("ROLE_BRIGADE")) {
				// Se obtiene el id del usuario logueado.
				final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication()
						.getCredentials();
				// Utilizando el id del usuario se busca a la brigada del usuario.
				final BrigadeDetailDTO brigadeDetailDTO = brigadeService.findByUserId(userId);
				// Se realiza la peticion a la BD, <La publicacion no debe estar eliminada> <El
				// usuario debe estar activo> <Id de la brirgada del usuario> <Paginacion>.
				return this.publicationRepository
						.findAllByDeletedAndEnabledAndDestination(false, true, brigadeDetailDTO.getId(), pageable)
						.map(this.publicationMapper::toDTO);
			}
			// Si es Admin puede ver todas las publicaciones.
			else if (role.equals("ROLE_SUPERUSER")) {
				// Se realiza la peticion a la BD, <La publicacion no debe estar eliminada> <El
				// usuario debe estar activo> <Paginacion>.
				return this.publicationRepository.findAllByDeletedAndEnabled(false, true, pageable)
						.map(this.publicationMapper::toDTO);
			}
		} catch (Exception ex) {
			// Si se produjo un fallo no retorna nada.
			return null;
		}
		// Si no tiene ningun rol no retorna nada.
		return null;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<PublicationDTO> findAllByUserId(final long id, Pageable pageable) throws Exception {
		try {
			// Obtiene los roles del usuario logueado del contexto.
			String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0]
					.toString();
			// Si es un Bombero puede ver las publicaciones que son para Todos, Publico y Mi
			// Brigada.
			// Filtra que sea solo de la brigada del Bombero logueado.
			if (role.equals("ROLE_USER")) {
				// Se obtiene el id del usuario logueado.
				final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication()
						.getCredentials();
				// Se busca al Bombero para obtener el id de la brigada en la que esta.
				final FiremanDetailDTO firemanDetailDTO = firemanService.findByUserId(userId);
				// Se realiza la peticion a la BD, <Id del usuario del que se quiere obtener las
				// publicaciones><La publicacion no debe estar eliminada> <El
				// usuario debe estar activo> <Id de la brirgada del usuario logueado>
				// <Paginacion>.
				return this.publicationRepository.findAllByUserIdAndDeletedAndEnabledAndDestination(id, false, true,
						firemanDetailDTO.getBrigadeId(), pageable).map(this.publicationMapper::toDTO);
			}
			// Si es una Brigada puede ver las publicaciones que son para Todos, Publico y
			// Mi Brigada.
			// Filtra que sea solo de la brigada de la Brigada logueada.
			else if (role.equals("ROLE_BRIGADE")) {
				// Se obtiene el id del usuario logueado.
				final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication()
						.getCredentials();
				// Utilizando el id del usuario se busca a la brigada del usuario.
				final BrigadeDetailDTO brigadeDetailDTO = brigadeService.findByUserId(userId);
				// Se realiza la peticion a la BD, <Id del usuario del que se quiere obtener las
				// publicaciones><La publicacion no debe estar eliminada> <El
				// usuario debe estar activo> <Id de la brirgada del usuario logueado>
				// <Paginacion>.
				return this.publicationRepository.findAllByUserIdAndDeletedAndEnabledAndDestination(id, false, true,
						brigadeDetailDTO.getId(), pageable).map(this.publicationMapper::toDTO);
			}
			// Si es Admin puede ver todas las publicaciones.
			else if (role.equals("ROLE_SUPERUSER")) {
				// Se realiza la peticion a la BD, <Id del usuario del que se quiere obtener las
				// publicaciones><La publicacion no debe estar eliminada> <El
				// usuario debe estar activo> <Paginacion>.
				return this.publicationRepository.findAllByUserIdAndDeletedAndEnabled(id, false, true, pageable)
						.map(this.publicationMapper::toDTO);
			}
		} catch (Exception ex) {
			// Si se produjo un fallo no retorna nada.
			return null;
		}
		// Si no tiene ningun rol no retorna nada.
		return null;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PublicationDetailDTO findById(long id) throws Exception {
		try {
			PublicationDetailDTO dto = new PublicationDetailDTO();
			// Obtiene los roles del usuario logueado del contexto.
			String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0]
					.toString();
			// Si es un Bombero puede ver las publicaciones que son para Todos, Publico y Mi
			// Brigada.
			// Filtra que sea solo de la brigada del Bombero logueado.
			if (role.equals("ROLE_USER")) {
				// Se obtiene el id del usuario logueado.
				final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication()
						.getCredentials();
				// Se busca al Bombero para obtener el id de la brigada en la que esta.
				final FiremanDetailDTO firemanDetailDTO = firemanService.findByUserId(userId);
				// Se realiza la peticion a la BD, <Id de la publicacion> <La publicacion no
				// debe estar eliminada> <El usuario debe estar activo> <Id de la brirgada del
				// usuario logueado>.
				dto = this.publicationRepository
						.findByIdAndDeletedAndEnabledAndDestination(id, false, true, firemanDetailDTO.getBrigadeId())
						.map(this.publicationMapper::toDetailDTO)
						.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
			}
			// Si es una Brigada puede ver las publicaciones que son para Todos, Publico y
			// Mi Brigada.
			// Filtra que sea solo de la brigada de la Brigada logueada.
			else if (role.equals("ROLE_BRIGADE")) {
				// Se obtiene el id del usuario logueado.
				final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication()
						.getCredentials();
				// Utilizando el id del usuario se busca a la brigada del usuario.
				final BrigadeDetailDTO brigadeDetailDTO = brigadeService.findByUserId(userId);
				// Se realiza la peticion a la BD, <Id de la publicacion> <La publicacion no
				// debe estar eliminada> <El usuario debe estar activo> <Id de la brirgada del
				// usuario logueado>.
				dto = this.publicationRepository
						.findByIdAndDeletedAndEnabledAndDestination(id, false, true, brigadeDetailDTO.getId())
						.map(this.publicationMapper::toDetailDTO)
						.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
			}
			// Si es Admin puede ver todas las publicaciones.
			else if (role.equals("ROLE_SUPERUSER")) {
				// Se realiza la peticion a la BD, <Id de la publicacion> <La publicacion no
				// debe estar eliminada> <El usuario debe estar activo>.
				dto = this.publicationRepository.findByIdAndDeletedAndEnabled(id, false, true)
						.map(this.publicationMapper::toDetailDTO)
						.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
			} else {
				return null;
			}
			// Carga la lista de id de los archivos de la publicacion.
			dto.setFiles(this.fileService.findByPublicationId(dto.getId()));
			return dto;
		} catch (Exception ex) {
			// Si se produjo un fallo no retorna nada.
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PublicationDetailDTO findMyPublicationById(long id) throws Exception {
		// Se obtiene el id del usuario logueado.
		final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		return this.publicationRepository.findByIdAndUserIdAndDeletedAndEnabled(id, userId, false, true)
				.map(this.publicationMapper::toDetailDTO)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public PublicationDetailDTO save(PublicationCreateDTO dto) throws Exception {

		// Obtiene datos del usuario logueado.
		UserEntity userEntity = new UserEntity();
		userEntity.setId((long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials());

		// Creación de la publicación.
		PublicationEntity entity = this.publicationMapper.toCreateEntity(dto);
		entity.setUser(userEntity);
		entity.setDeleted(false);
		entity.setCreated_at(new Date());

		// definicion de destino.
		entity.setDestination(destination(dto.getDestination(), userEntity.getId(), dto.getBrigadeId()));

		// Guardando la publicacion.
		return this.publicationMapper.toDetailDTO(this.publicationRepository.save(entity));
	}

	@Override
	public PublicationDetailDTO update(long id, PublicationUpdateDTO dto) throws Exception {
		// Se obtiene el id del usuario logueado.
		final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		// Se busca en la base de datos.
		// Solo puede editar el usuario su propia publicaciones.
		PublicationEntity entity = this.publicationRepository
				.findByIdAndUserIdAndDeletedAndEnabled(id, userId, false, true)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
		// Se setean los datos
		entity.setBody(dto.getBody());
		entity.setDestination(destination(dto.getDestination(), userId, dto.getBrigadeId()));
		entity.setUpdated_at(new Date());
		// Se guardan los cambios
		return this.publicationMapper.toDetailDTO(this.publicationRepository.save(entity));
	}

	@Override
	public void delete(long id) throws Exception {
		PublicationEntity entity = null;
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
		// Los Bomberos solo pueden eliminar sus publicaciones.
		if (role.equals("ROLE_USER")) {
			final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			entity = this.publicationRepository.findByIdAndUserIdAndDeletedAndEnabled(id, userId, false, true)
					.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
		}
		// Las Brigadas pueden eliminar sus publicaciones y las de los bomberos de su
		// brigada.
		else if (role.equals("ROLE_BRIGADE")) {
			final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			final BrigadeDetailDTO brigadeDetailDTO = brigadeService.findByUserId(userId);
			entity = this.publicationRepository.findByIdAndUserIdAndDeletedAndEnabledAndDestination(id, userId,
					brigadeDetailDTO.getId(), false, true)
					.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
		}
		// El Admin puede eliminar cualquier publicacion.
		else if (role.equals("ROLE_SUPERUSER")) {
			entity = this.publicationRepository.findByIdAndDeletedAndEnabled(id, false, true)
					.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
		}
		// Se setea y se guarda.
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
