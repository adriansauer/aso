package com.py.aso.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.PublicationEntity;

@Repository
public interface PublicationRepository extends JpaRepository<PublicationEntity, Long> {

	@Query(value = "SELECT * FROM PUBLICATIONS INNER JOIN USERS ON PUBLICATIONS.user_id = USERS.id WHERE PUBLICATIONS.deleted = ?1 AND USERS.enabled = ?2", nativeQuery = true)
	public Page<PublicationEntity> findAllByDeleted(final boolean deleted, final boolean enabled, final Pageable pageable);

	@Query(value = "SELECT * FROM PUBLICATIONS INNER JOIN USERS ON PUBLICATIONS.user_id = USERS.id WHERE PUBLICATIONS.user_id = ?1 AND PUBLICATIONS.deleted = ?2 AND USERS.enabled = ?3", nativeQuery = true)
	public Page<PublicationEntity> findAllByUserIdAndDeleted (final Long userId, final boolean deleted, final boolean enabled, final Pageable pageable);
	
	@Query(value = "SELECT * FROM PUBLICATIONS INNER JOIN USERS ON PUBLICATIONS.user_id = USERS.id WHERE PUBLICATIONS.id = ?1 AND PUBLICATIONS.deleted = ?2 AND USERS.enabled = ?3", nativeQuery = true)
	public Optional<PublicationEntity> findByIdAndDeleted  (final Long id, final boolean deleted, final boolean enabled);

	@Query(value = "SELECT * FROM PUBLICATIONS INNER JOIN USERS ON PUBLICATIONS.user_id = USERS.id WHERE PUBLICATIONS.id = ?1 AND PUBLICATIONS.deleted = ?2 AND USERS.enabled = ?3", nativeQuery = true)
	public boolean existsByIdAndDeleted (final Long id, final boolean deleted, final boolean enabled);

}