package com.py.aso.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.DepartamentEntity;

@Repository
public interface DepartamentRepository extends JpaRepository<DepartamentEntity, Long> {

	public Page<DepartamentEntity> findAllByDeleted(final boolean deleted, final Pageable pageable);

	public Optional<DepartamentEntity> findByIdAndDeleted(final Long id, final boolean deleted);

	public boolean existsByIdAndDeleted(final Long id, final boolean deleted);

}
