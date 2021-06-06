package com.py.aso.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

	@Query(value = "SELECT * FROM FILES f INNER JOIN PUBLICATIONS p ON p.file_id = f.id WHERE p.deleted = ?1", nativeQuery = true)
	public Page<FileEntity> findAllByDeleted(final boolean deleted, final Pageable pageable);

	@Query(value = "SELECT * FROM FILES f INNER JOIN PUBLICATIONS p ON p.id = f.publication_id WHERE f.publication_id = ?1 AND p.deleted = ?2", nativeQuery = true)
	public Page<FileEntity> findAllByPublicationIdAndDeleted(final Long publicatioId, final boolean deleted, final Pageable pageable);
	
	@Query(value = "SELECT * FROM FILES INNER JOIN PUBLICATIONS ON PUBLICATIONS.id = FILES.publication_id WHERE FILES.publication_id = ?1 AND PUBLICATIONS.deleted = ?2", nativeQuery = true)
	public List<FileEntity> findAllByPublicationIdAndDeleted(final Long publicatioId, final boolean deleted);
	
	@Query(value = "SELECT * FROM FILES INNER JOIN PUBLICATIONS ON PUBLICATIONS.id = FILES.publication_id WHERE FILES.id = ?1 AND PUBLICATIONS.deleted = ?2", nativeQuery = true)
	public Optional<FileEntity> findByIdAndDeleted(final Long id, final boolean deleted);

	@Query(value = "SELECT * FROM FILES INNER JOIN PUBLICATIONS ON PUBLICATIONS.id = FILES.publication_id WHERE FILES.id = ?1 AND PUBLICATIONS.deleted = ?2", nativeQuery = true)
	public boolean existsByIdAndDeleted(final Long fileId, final boolean deleted);
}