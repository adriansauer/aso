package com.py.aso.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public Page<UserEntity> findAllByEnabled(final boolean enabled, final Pageable pageable);

	public Optional<UserEntity> findByIdAndEnabled(final Long id, final boolean enabled);

	public boolean existsByIdAndEnabled(final Long id, final boolean enabled);

	public Optional<UserEntity> findByUsercodeAndEnabled(final String usercode, final boolean enabled);
}
