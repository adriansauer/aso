package com.py.aso.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.LikeEntity;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

	@Query(value = "SELECT * FROM LIKES l INNER JOIN PUBLICATIONS p ON l.publication_id = p.id WHERE p.deleted = ?1", nativeQuery = true)
	public Page<LikeEntity> findAllByDeleted(final boolean deleted, final Pageable pageable);

	@Query(value = "SELECT * FROM LIKES l INNER JOIN PUBLICATIONS p ON p.id = l.publication_id WHERE l.publication_id = ?1 AND p.deleted = ?2", nativeQuery = true)
	public Page<LikeEntity> findAllByPublicationIdAndDeleted(final Long publicatioId, final boolean deleted,
			final Pageable pageable);

	@Query(value = "SELECT * FROM LIKES INNER JOIN PUBLICATIONS ON PUBLICATIONS.id = LIKES.publication_id WHERE LIKES.publication_id = ?1 AND PUBLICATIONS.deleted = ?2", nativeQuery = true)
	public List<LikeEntity> findAllByPublicationIdAndDeleted(final Long publicatioId, final boolean deleted);

	@Query(value = "SELECT * FROM LIKES INNER JOIN PUBLICATIONS ON PUBLICATIONS.id = LIKES.publication_id WHERE LIKES.id = ?1 AND PUBLICATIONS.deleted = ?2", nativeQuery = true)
	public Optional<LikeEntity> findByIdAndDeleted(final Long id, final boolean deleted);
	
	@Query(value = "SELECT * FROM LIKES INNER JOIN PUBLICATIONS ON PUBLICATIONS.id = LIKES.publication_id WHERE LIKES.publication_id = ?1 AND LIKES.user_id = ?2  AND PUBLICATIONS.deleted = ?3", nativeQuery = true)
	public Optional<LikeEntity> findByIdAndUserIdAndDeleted(final Long id, final Long userId, final boolean deleted);
	
	@Query(value = "SELECT COUNT(LIKES.id) FROM LIKES INNER JOIN PUBLICATIONS ON PUBLICATIONS.id = LIKES.publication_id WHERE LIKES.publication_id = ?1 AND PUBLICATIONS.deleted = ?2", nativeQuery = true)
	public long findByPublicationIdAndDeleted(final Long publicationId, final boolean deleted);

	@Query(value = "SELECT * FROM LIKES INNER JOIN PUBLICATIONS ON PUBLICATIONS.id = LIKES.publication_id WHERE LIKES.id = ?1 AND PUBLICATIONS.deleted = ?2", nativeQuery = true)
	public boolean existsByIdAndDeleted(final Long fileId, final boolean deleted);

}
