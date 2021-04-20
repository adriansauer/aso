package com.py.aso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.BrigadeEntity;

@Repository
public interface BrigadeRepository extends JpaRepository<BrigadeEntity, Long> {

}
