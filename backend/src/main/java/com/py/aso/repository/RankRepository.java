package com.py.aso.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.RankEntity;

@Repository
public interface RankRepository extends JpaRepository<RankEntity, Long> {

	public Page<RankEntity> findAllByDeleted(final boolean deleted, final Pageable pageable);

	public Optional<RankEntity> findByIdAndDeleted(final Long id, final boolean deleted);

	public boolean existsByIdAndDeleted(final Long id, final boolean deleted);

}
