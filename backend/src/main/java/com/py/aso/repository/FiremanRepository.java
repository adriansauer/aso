package com.py.aso.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.FiremanEntity;

@Repository
public interface FiremanRepository extends JpaRepository<FiremanEntity, Long> {

	@Query(value = "SELECT * FROM USER_DETAILS d INNER JOIN USERS u ON d.user_id = u.id WHERE u.enabled = ?1", nativeQuery = true)
	public Page<FiremanEntity> findAllByEnabled(final boolean enabled, final Pageable pageable);

	@Query(value = "SELECT * FROM USER_DETAILS INNER JOIN USERS ON USER_DETAILS.user_id = USERS.id WHERE USER_DETAILS.id = ?1 AND USERS.enabled = ?2", nativeQuery = true)
	public Optional<FiremanEntity> findByIdAndEnabled(final Long id, final boolean enabled);

	@Query(value = "SELECT * FROM USER_DETAILS d INNER JOIN USERS u ON d.user_id = u.id WHERE d.brigade_id = ?1 AND u.enabled = ?2", nativeQuery = true)
	public Page<FiremanEntity> findAllByBrigadeIdAndEnabled(final Long BrigadeId, final boolean enabled,
			final Pageable pageable);

	@Query(value = "SELECT * FROM USER_DETAILS INNER JOIN USERS ON USER_DETAILS.user_id = USERS.id WHERE USER_DETAILS.user_id = ?1 AND USERS.enabled = ?2", nativeQuery = true)
	public Optional<FiremanEntity> findByUserIdAndEnabled(final Long userId, final boolean enabled);
	
	public boolean existsByUserId(final Long userId);

}
