package com.py.aso.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.BrigadeHistoryEntity;

@Repository
public interface BrigadeHistoryRepository extends JpaRepository<BrigadeHistoryEntity, Long> {

	@Query(value = "SELECT * FROM brigade_histories bh INNER JOIN brigade_details bd ON bh.brigade_id = bd.id INNER JOIN users u ON bd.user_id = u.id WHERE u.enabled = ?1", nativeQuery = true)
	public Page<BrigadeHistoryEntity> findAllByEnabled(final boolean enabled, final Pageable pageable);
	
	@Query(value = "SELECT * FROM brigade_histories bh INNER JOIN brigade_details bd ON bh.brigade_id = bd.id INNER JOIN users u ON bd.user_id = u.id WHERE bh.id = ?1 AND u.enabled = ?2", nativeQuery = true)
	public Optional<BrigadeHistoryEntity> findByIdAndEnabled(final Long id,	final boolean enabled);
	
	@Query(value = "SELECT * FROM brigade_histories bh INNER JOIN brigade_details bd ON bh.brigade_id = bd.id INNER JOIN users u ON bd.user_id = u.id WHERE bd.id = ?1 AND u.enabled = ?2", nativeQuery = true)
	public Optional<BrigadeHistoryEntity> findByBrigadeIdAndEnabled(final Long brigadeId, final boolean enabled);
	
	@Query(value = "SELECT * FROM brigade_histories bh INNER JOIN brigade_details bd ON bh.brigade_id = bd.id INNER JOIN users u ON bd.user_id = u.id WHERE bd.id = ?1 AND u.enabled = ?2", nativeQuery = true)
	public boolean existsByBrigadeIdAndEnabled(final Long brigadeId, final boolean enabled);
	
}
