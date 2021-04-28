package com.py.aso.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.BrigadeEntity;

@Repository
public interface BrigadeRepository extends JpaRepository<BrigadeEntity, Long> {

	@Query(value = "SELECT * FROM BRIGADE_DETAILS INNER JOIN USERS ON BRIGADE_DETAILS.user_id = USERS.id WHERE USERS.enabled = ?1", nativeQuery = true)
	public Page<BrigadeEntity> findAllByEnabled(final boolean enabled, final Pageable pageable);

	@Query(value = "SELECT * FROM BRIGADE_DETAILS INNER JOIN USERS ON BRIGADE_DETAILS.user_id = USERS.id WHERE BRIGADE_DETAILS.id = ?1 AND USERS.enabled = ?2", nativeQuery = true)
	public Optional<BrigadeEntity> findByIdAndEnabled(final Long id, final boolean enabled);

	@Query(value = "SELECT * FROM BRIGADE_DETAILS INNER JOIN USERS ON BRIGADE_DETAILS.user_id = USERS.id WHERE BRIGADE_DETAILS.user_id = ?1 AND USERS.enabled = ?2", nativeQuery = true)
	public Optional<BrigadeEntity> findByUserIdAndEnabled(final Long userId, final boolean enabled);

	public boolean existsByUserId(final Long userId);
}
