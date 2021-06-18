package com.py.aso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.IncidenceCodeEntity;

@Repository
public interface IncidenceCodeRepository extends JpaRepository<IncidenceCodeEntity, Long> {

}
