package com.py.aso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.CityEntity;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

}
