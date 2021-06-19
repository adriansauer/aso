package com.py.aso.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.IncidenceCodeEntity;

@Repository
public interface IncidenceCodeRepository extends JpaRepository<IncidenceCodeEntity, Long> {
	
	public Page<IncidenceCodeEntity> findAllByDeleted(final boolean deleted, final Pageable pageable);

	public Optional<IncidenceCodeEntity> findByIdAndDeleted(final Long id, final boolean deleted);
	
	public Optional<IncidenceCodeEntity> findByCodeAndDeleted(final String code, final boolean deleted);

	public boolean existsByIdAndDeleted(final Long id, final boolean deleted);
	
	public boolean existsByCodeAndDeleted(final String code, final boolean deleted);

}
