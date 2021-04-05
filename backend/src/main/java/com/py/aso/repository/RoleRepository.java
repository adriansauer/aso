package com.py.aso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.py.aso.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
