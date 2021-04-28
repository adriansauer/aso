package com.py.aso.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.CityEntity;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

	public Page<CityEntity> findAllByDeleted(final boolean deleted, final Pageable pageable);

	public Optional<CityEntity> findByIdAndDeleted(final Long id, final boolean deleted);

	public boolean existsByIdAndDeleted(final Long id, final boolean deleted);

}
