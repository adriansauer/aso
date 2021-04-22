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

	@Query(value = "SELECT * FROM USER_DETAILS INNER JOIN USERS ON USER_DETAILS.user_id = USERS.id WHERE USERS.enabled = ?1", nativeQuery = true)
	public Page<FiremanEntity> findAllByEnabled(final boolean enabled, final Pageable pageable);

	@Query(value = "SELECT * FROM USER_DETAILS INNER JOIN USERS ON USER_DETAILS.user_id = USERS.id WHERE USER_DETAILS.id = ?1 AND USERS.enabled = ?2", nativeQuery = true)
	public Optional<FiremanEntity> findByIdAndEnabled(final Long id, final boolean enabled);

}
