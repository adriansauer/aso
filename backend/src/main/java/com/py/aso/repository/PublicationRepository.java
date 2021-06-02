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
	public Page<PublicationEntity> findAllByDeletedAndEnabled(final boolean deleted, final boolean enabled,
			final Pageable pageable);

	@Query(value = "SELECT * FROM PUBLICATIONS INNER JOIN USERS ON PUBLICATIONS.user_id = USERS.id WHERE PUBLICATIONS.deleted = ?1 AND USERS.enabled = ?2 AND (PUBLICATIONS.destination = -1 OR PUBLICATIONS.destination = 0 OR PUBLICATIONS.destination =?3)", nativeQuery = true)
	public Page<PublicationEntity> findAllByDeletedAndEnabledAndDestination(final boolean deleted,
			final boolean enabled, final Long destination, final Pageable pageable);

	@Query(value = "SELECT * FROM PUBLICATIONS INNER JOIN USERS ON PUBLICATIONS.user_id = USERS.id WHERE PUBLICATIONS.user_id = ?1 AND PUBLICATIONS.deleted = ?2 AND USERS.enabled = ?3 AND (PUBLICATIONS.destination = -1 OR PUBLICATIONS.destination = 0 OR PUBLICATIONS.destination =?4)", nativeQuery = true)
	public Page<PublicationEntity> findAllByUserIdAndDeletedAndEnabledAndDestination(final Long userId,
			final boolean deleted, final boolean enabled, final Long destination, final Pageable pageable);

	@Query(value = "SELECT * FROM PUBLICATIONS INNER JOIN USERS ON PUBLICATIONS.user_id = USERS.id WHERE PUBLICATIONS.user_id = ?1 AND PUBLICATIONS.deleted = ?2 AND USERS.enabled = ?3", nativeQuery = true)
	public Page<PublicationEntity> findAllByUserIdAndDeletedAndEnabled(final Long userId, final boolean deleted,
			final boolean enabled, final Pageable pageable);

	@Query(value = "SELECT * FROM PUBLICATIONS INNER JOIN USERS ON PUBLICATIONS.user_id = USERS.id WHERE PUBLICATIONS.id = ?1 AND PUBLICATIONS.deleted = ?2 AND USERS.enabled = ?3", nativeQuery = true)
	public Optional<PublicationEntity> findByIdAndDeletedAndEnabled(final Long id, final boolean deleted,
			final boolean enabled);
	
	@Query(value = "SELECT * FROM PUBLICATIONS INNER JOIN USERS ON PUBLICATIONS.user_id = USERS.id WHERE PUBLICATIONS.id = ?1 AND PUBLICATIONS.user_id = ?2 AND PUBLICATIONS.deleted = ?3 AND USERS.enabled = ?4", nativeQuery = true)
	public Optional<PublicationEntity> findByIdAndUserIdAndDeletedAndEnabled(final Long id, final Long userId, final boolean deleted,
			final boolean enabled);
	
	@Query(value = "SELECT * FROM PUBLICATIONS INNER JOIN USERS ON PUBLICATIONS.user_id = USERS.id WHERE PUBLICATIONS.id = ?1 AND PUBLICATIONS.deleted = ?2 AND USERS.enabled = ?3 AND (PUBLICATIONS.destination = -1 OR PUBLICATIONS.destination = 0 OR PUBLICATIONS.destination =?3)", nativeQuery = true)
	public Optional<PublicationEntity> findByIdAndDeletedAndEnabledAndDestination(final Long id, final boolean deleted,
			final boolean enabled, final Long destination);
	
	@Query(value = "SELECT * FROM PUBLICATIONS INNER JOIN USERS ON PUBLICATIONS.user_id = USERS.id WHERE PUBLICATIONS.id = ?1 AND (PUBLICATIONS.user_id = ?2 OR PUBLICATIONS.destination = ?3) AND PUBLICATIONS.deleted = ?4 AND USERS.enabled = ?5", nativeQuery = true)
	public Optional<PublicationEntity> findByIdAndUserIdAndDeletedAndEnabledAndDestination(final Long id, final Long userId,  final Long destination, final boolean deleted,
			final boolean enabled);

	@Query(value = "SELECT * FROM PUBLICATIONS INNER JOIN USERS ON PUBLICATIONS.user_id = USERS.id WHERE PUBLICATIONS.id = ?1 AND PUBLICATIONS.deleted = ?2 AND USERS.enabled = ?3", nativeQuery = true)
	public boolean existsByIdAndDeletedAndEnabled(final Long id, final boolean deleted, final boolean enabled);

	@Query(value = "SELECT * FROM PUBLICATIONS INNER JOIN USERS ON PUBLICATIONS.user_id = USERS.id WHERE FALSE", nativeQuery = true)
	public Page<PublicationEntity> findAll(final Pageable pageable);

}