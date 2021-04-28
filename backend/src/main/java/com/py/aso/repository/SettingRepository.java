package com.py.aso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.SettingEntity;

@Repository
public interface SettingRepository extends JpaRepository<SettingEntity, Long> {

	public Optional<SettingEntity> findByKey(final String key);

	public boolean existsByKey(final String key);

}
