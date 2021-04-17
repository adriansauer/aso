package com.py.aso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.DepartamentEntity;

@Repository
public interface DepartamentRepository extends JpaRepository<DepartamentEntity, Long> {

}
