package com.py.aso.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

	public Page<FileEntity> findAllByDeleted(final boolean deletedFile, final boolean deletedPublication, final Pageable pageable);

	public Page<FileEntity> findAllByPublicationIdAndDeleted(final Long publicatioId, final boolean deletedFile, final boolean deletedPublication, final Pageable pageable);
	
	public Optional<FileEntity> findByIdAndDeleted(final Long fileId, final boolean deletedFile, final boolean deletedPublication);

	public boolean existsByIdAndDeleted(final Long fileId, final boolean deletedFile, final boolean deletedPublication);
}