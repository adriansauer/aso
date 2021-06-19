package com.py.aso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.BrigadeHistoryEntity;

@Repository
public interface BrigadeHistoryRepository extends JpaRepository<BrigadeHistoryEntity, Long> {

}
