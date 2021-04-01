package com.py.aso.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.SettingEntity;

@Repository
public interface SettingRepository extends JpaRepository<SettingEntity, Long> {

	@Override
	public Page<SettingEntity> findAll(final Pageable pageable);

	@Override
	public Optional<SettingEntity> findById(final Long id);

	public Optional<SettingEntity> findByKey(final String key);

	@Override
	public boolean existsById(final Long id);

	public boolean existsByKey(final String key);

}
