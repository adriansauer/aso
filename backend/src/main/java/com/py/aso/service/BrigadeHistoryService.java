package com.py.aso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.py.aso.dto.BrigadeHistoryDTO;
import com.py.aso.dto.create.BrigadeHistoryCreateDTO;
import com.py.aso.dto.detail.BrigadeDetailDTO;
import com.py.aso.dto.detail.BrigadeHistoryDetailDTO;
import com.py.aso.dto.update.BrigadeHistoryUpdateDTO;
import com.py.aso.entity.BrigadeEntity;
import com.py.aso.entity.BrigadeHistoryEntity;
import com.py.aso.exception.ResourceExistsException;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.BrigadeHistoryRepository;
import com.py.aso.service.mapper.BrigadeHistoryMapper;

@Service
public class BrigadeHistoryService implements
		BaseService<BrigadeHistoryDTO, BrigadeHistoryDetailDTO, BrigadeHistoryCreateDTO, BrigadeHistoryUpdateDTO> {

	@Autowired
	private BrigadeHistoryRepository brigadeHistoryRepository;

	@Autowired
	private BrigadeHistoryMapper bigadeHistoryMapper;

	@Autowired
	private BrigadeService brigadeService;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<BrigadeHistoryDTO> findAll(final Pageable pageable) {
		return this.brigadeHistoryRepository.findAllByEnabled(true, pageable)//
				.map(this.bigadeHistoryMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public BrigadeHistoryDetailDTO findById(final long id) throws Exception {
		return this.brigadeHistoryRepository.findByIdAndEnabled(id, true)//
				.map(this.bigadeHistoryMapper::toDetailDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("Brigade History", "id", id));
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public BrigadeHistoryDetailDTO findByBrigadeId(final long id) throws Exception {
		return this.brigadeHistoryRepository.findByBrigadeIdAndEnabled(id, true)//
				.map(this.bigadeHistoryMapper::toDetailDTO)//
				.orElseThrow(() -> new ResourceNotFoundException("Brigade History", "id", id));
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public BrigadeHistoryDetailDTO save(final BrigadeHistoryCreateDTO dto) throws Exception {
		// Se obtiene los roles.
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
		// La brigada solo puede guardar su propia historia.
		if (role.equals("ROLE_BRIGADE")) {
			// Se obtiene el id del usuario
			final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			// Se busca a la brigada que es el usuario.
			final BrigadeDetailDTO brigadeDetailDTO = this.brigadeService.findByUserId(userId);
			// Se verifica que no exista una historia para la brigada.
			if (exitsByBrigadeId(brigadeDetailDTO.getCityId())) {
				throw new ResourceExistsException(" historia para la brigada " + brigadeDetailDTO.getName());
			}
			// Se crea la brigada para relacionar.
			BrigadeEntity brigadeEntity = new BrigadeEntity();
			brigadeEntity.setId(brigadeDetailDTO.getId());
			// Se crea la historia y se guarda
			BrigadeHistoryEntity entity = this.bigadeHistoryMapper.toCreateEntity(dto);
			entity.setBrigade(brigadeEntity);
			return this.bigadeHistoryMapper.toDetailDTO(this.brigadeHistoryRepository.save(entity));
			// El administrador puede cuardar historia para cualquier brigada.
		} else if (role.equals("ROLE_SUPERUSER")) {
			// Se verifica que exista la brigada
			final BrigadeDetailDTO brigadeDetailDTO = this.brigadeService.findById(dto.getBrigadeId());
			// Se verifica que no exista ya una historia para esa brigada.
			if (exitsByBrigadeId(brigadeDetailDTO.getCityId())) {
				throw new ResourceExistsException(" historia para la brigada " + brigadeDetailDTO.getName());
			}
			// Se crea la brigada para relacionar.
			BrigadeEntity brigadeEntity = new BrigadeEntity();
			brigadeEntity.setId(brigadeDetailDTO.getId());
			// Se crea la historia.
			BrigadeHistoryEntity entity = this.bigadeHistoryMapper.toCreateEntity(dto);
			entity.setBrigade(brigadeEntity);
			return this.bigadeHistoryMapper.toDetailDTO(this.brigadeHistoryRepository.save(entity));
		}
		// Si no posee estos roles se niega el acceso.
		throw new AccessDeniedException("No posee el rol para guardar una historia");
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public BrigadeHistoryDetailDTO update(final long brigadeId, final BrigadeHistoryUpdateDTO dto) throws Exception {
		// Se obtiene el rol el usuario.
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
		// La brigada puede actualizar solo su historia. Si no existe la historia se
		// crea.
		if (role.equals("ROLE_BRIGADE")) {
			// Se obtiene el id del usuario.
			final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			// Se verifica que exista la brigada.
			final BrigadeDetailDTO brigadeDetailDTO = this.brigadeService.findByUserId(userId);
			// Se crea la brigada para relacionar.
			BrigadeEntity brigadeEntity = new BrigadeEntity();
			brigadeEntity.setId(brigadeDetailDTO.getId());
			// Se crea la historia.
			BrigadeHistoryEntity entity = new BrigadeHistoryEntity();
			entity.setBrigade(brigadeEntity);
			entity.setText(dto.getText());
			// Se busca si ya existe la historia para la brigada. Si no existe se creara.
			entity = editId(entity, brigadeDetailDTO.getId());
			return this.bigadeHistoryMapper.toDetailDTO(this.brigadeHistoryRepository.save(entity));
			// El administrador puede actualizar cualquier historia. Si no existe la
			// historia se crea.
		} else if (role.equals("ROLE_SUPERUSER")) {
			// Se verifica que exista la brigada.
			final BrigadeDetailDTO brigadeDetailDTO = this.brigadeService.findById(brigadeId);
			// Se crea la brigada para relacionar.
			BrigadeEntity brigadeEntity = new BrigadeEntity();
			brigadeEntity.setId(brigadeDetailDTO.getId());
			// Se crea la historia.
			BrigadeHistoryEntity entity = new BrigadeHistoryEntity();
			entity.setBrigade(brigadeEntity);
			entity.setText(dto.getText());
			// Se busca si ya existe una historia para la brigada, si creara una.
			entity = editId(entity, brigadeId);
			return this.bigadeHistoryMapper.toDetailDTO(this.brigadeHistoryRepository.save(entity));
		}
		// Si no posee estos roles se niega el acceso.
		throw new AccessDeniedException("No posee el rol para guardar una historia");
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public void delete(final long id) throws Exception {
		this.findById(id);
		this.brigadeHistoryRepository.deleteById(id);
	}

	/**
	 * Metodo exitsByBrigadeId Busca si existe una historia por el id de la brigada.
	 * 
	 * @param brigadeId Id de la brigada
	 * @return True si existe la historia y False si no existe.
	 */
	private boolean exitsByBrigadeId(final long brigadeId) {
		try {
			this.findByBrigadeId(brigadeId);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Metodo editId Busca si existe la historia de una brigada, si existe agrega el
	 * id de la historia a la entidad dada.
	 * 
	 * @param entity Entidad a la que se le agrega el id si existe.
	 * @param id     Id de la brigada,
	 * @return Entidad con el id actualizado si es que existe una historia para la
	 *         brigada dada.
	 */
	private BrigadeHistoryEntity editId(BrigadeHistoryEntity entity, long id) {
		try {
			BrigadeHistoryDetailDTO brigadeHistoryDetailDTO = this.findByBrigadeId(id);
			entity.setId(brigadeHistoryDetailDTO.getId());
		} catch (Exception e) {
			return entity;
		}
		return entity;
	}

}
