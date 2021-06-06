package com.py.aso.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.py.aso.dto.CountDTO;
import com.py.aso.dto.LikeDTO;
import com.py.aso.dto.create.LikeCreateDTO;
import com.py.aso.dto.detail.LikeDetailDTO;
import com.py.aso.dto.update.LikeUpdateDTO;
import com.py.aso.entity.LikeEntity;
import com.py.aso.entity.PublicationEntity;
import com.py.aso.entity.UserEntity;
import com.py.aso.exception.ResourceNotFoundException;
import com.py.aso.repository.LikeRepository;
import com.py.aso.service.mapper.LikeMapper;

@Service
public class LikeService implements BaseService<LikeDTO, LikeDetailDTO, LikeCreateDTO, LikeUpdateDTO> {

	@Autowired
	private LikeRepository likeRepository;

	@Autowired
	private LikeMapper likeMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<LikeDTO> findAll(final Pageable pageable) {
		return this.likeRepository.findAllByDeleted(false, pageable).map(this.likeMapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public LikeDetailDTO findById(final long id) throws Exception {
		return null;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public LikeDetailDTO findLikeByPublicationIdAndUserId(final long publicationId) throws Exception {
		// Se obtiene el id del usuario logueado.
		final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		return this.likeRepository.findByIdAndUserIdAndDeleted(publicationId, userId, false)
				.map(this.likeMapper::toDetailDTO)
				.orElseThrow(() -> new ResourceNotFoundException("Like", "publication id", publicationId));
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public CountDTO countLikeByPublicationIdAndUserId(final long publicationId) throws Exception {
		CountDTO countDTO = new CountDTO();
		countDTO.setQuantity(this.likeRepository.findByPublicationIdAndDeleted(publicationId, false));
		return countDTO;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public boolean existsLikeByPublicationIdAndUserId(final long publicationId) throws Exception {
		try {
			// Se obtiene el id del usuario logueado.
			final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			this.likeRepository.findByIdAndUserIdAndDeleted(publicationId, userId, false)
					.orElseThrow(() -> new ResourceNotFoundException("Like", "publication id", publicationId));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public LikeDetailDTO save(final LikeCreateDTO dto) throws Exception {
		final long userId = (long) (int) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		boolean liked = existsLikeByPublicationIdAndUserId(dto.getPublicationId());
		if (!liked) {
			LikeEntity entity = this.likeMapper.toCreateEntity(dto);
			entity.setDate(new Date());
			PublicationEntity publicationEntity = new PublicationEntity();
			publicationEntity.setId(dto.getPublicationId());
			entity.setPublication(publicationEntity);
			UserEntity userEntity = new UserEntity();
			userEntity.setId(userId);
			entity.setUser(userEntity);
			return this.likeMapper.toDetailDTO(this.likeRepository.save(entity));
		} else {
			return findLikeByPublicationIdAndUserId(dto.getPublicationId());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public LikeDetailDTO update(final long id, final LikeUpdateDTO dto) throws Exception {
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(final long publicationId) throws Exception {
		LikeDetailDTO dto = findLikeByPublicationIdAndUserId(publicationId);
		this.likeRepository.deleteById(dto.getId());

	}

}
