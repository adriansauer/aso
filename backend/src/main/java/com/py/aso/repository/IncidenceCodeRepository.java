package com.py.aso.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.py.aso.entity.IncidenceCodeEntity;

@Repository
public interface IncidenceCodeRepository extends JpaRepository<IncidenceCodeEntity, Long> {
	
	public Page<IncidenceCodeEntity> findAllByDeleted(final boolean deleted, final Pageable pageable);
	
	@Query(value = "SELECT * FROM INCIDENCE_CODES i WHERE i.code LIKE ?1% AND deleted = ?2", nativeQuery = true)
	public Page<IncidenceCodeEntity> searchByCodeAndDeletedStartsWith (final String code, final boolean deleted, final Pageable pageable);
	
	@Query(value = "SELECT * FROM INCIDENCE_CODES i WHERE i.description LIKE ?1% AND deleted = ?2", nativeQuery = true)
	public Page<IncidenceCodeEntity> searchByDescriptionAndDeletedStartsWith(final String description, final boolean deleted, final Pageable pageable);

	@Query(value = "SELECT * FROM INCIDENCE_CODES i WHERE i.description LIKE ?1% OR i.code LIKE ?2% AND i.deleted = ?3", nativeQuery = true)
	public Page<IncidenceCodeEntity> searchByDescriptionAndCodeAndDeletedStartsWith(final String description, final String code, final boolean deleted, final Pageable pageable);
	
	public Optional<IncidenceCodeEntity> findByIdAndDeleted(final Long id, final boolean deleted);
	
	public Optional<IncidenceCodeEntity> findByCodeAndDeleted(final String code, final boolean deleted);

	public boolean existsByIdAndDeleted(final Long id, final boolean deleted);
	
	public boolean existsByCodeAndDeleted(final String code, final boolean deleted);

}
