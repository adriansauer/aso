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

	@Query(value = "SELECT * FROM FILES INNER JOIN PUBLICATIONS ON PUBLICATIONS.file_id = FILES.id WHERE FILES.deleted = ?1 AND PUBLICATIONS.deleted = ?2", nativeQuery = true)
	public Page<FileEntity> findAllByDeleted(final boolean deletedFile, final boolean deletedPublication, final Pageable pageable);

	@Query(value = "SELECT * FROM FILES INNER JOIN PUBLICATIONS ON PUBLICATIONS.file_id = FILES.id WHERE FILES.publication_id = ?1 AND FILES.deleted = ?2 AND PUBLICATIONS.deleted = ?3", nativeQuery = true)
	public Page<FileEntity> findAllByPublicationIdAndDeleted(final Long publicatioId, final boolean deletedFile, final boolean deletedPublication, final Pageable pageable);
	
	@Query(value = "SELECT * FROM FILES INNER JOIN PUBLICATIONS ON PUBLICATIONS.file_id = FILES.id WHERE FILES.id = ?1 AND FILES.deleted = ?2 AND PUBLICATIONS.deleted = ?3", nativeQuery = true)
	public Optional<FileEntity> findByIdAndDeleted(final Long fileId, final boolean deletedFile, final boolean deletedPublication);

	@Query(value = "SELECT * FROM FILES INNER JOIN PUBLICATIONS ON PUBLICATIONS.file_id = FILES.id WHERE FILES.id = ?1 AND FILES.deleted = ?2 AND PUBLICATIONS.deleted = ?3", nativeQuery = true)
	public boolean existsByIdAndDeleted(final Long fileId, final boolean deletedFile, final boolean deletedPublication);
}