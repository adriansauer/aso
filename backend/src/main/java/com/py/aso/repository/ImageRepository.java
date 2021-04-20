package com.py.aso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.ImageEntity;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

}
